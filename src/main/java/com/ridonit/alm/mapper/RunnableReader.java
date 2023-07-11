package com.ridonit.alm.mapper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Base64;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

import com.ridonit.alm.payload.CloudRequirementDto;

public class RunnableReader implements Runnable {
    private Thread t;
    private String threadName;
    private long sleepTime;
    private boolean finish = false;
    private boolean running = false;

    private static ObjectMapper objectMapper = new ObjectMapper();

    protected RunnableReader(String threadName, long refreshTime) {
        this.threadName = threadName;
        this.sleepTime = refreshTime;
    }

    public void run() {
        running = true;
        while (!finish) {
            try {
                // TODO @HAUKE/BERND hier muss der Api Call rein, der die CalmProcesses pullt.
                List<CloudRequirementDto> taskList = getTasks();
                for (CloudRequirementDto task : taskList) {
                    boolean keep = filterTask(task);
                }
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (Exception e) {

            } finally {

            }
        }
        running = false;
    }

    private boolean filterTask(CloudRequirementDto task) {
        boolean updateAbap = false;
        CloudRequirementDto stored = getStoredCalmProcess(task.getId());
        if (startStatusPassed(task.getStatus()) || stored != null) {

            boolean statusChanged = stored.getStatus().equals(task.getStatus());
            boolean titleChanged = true;
            boolean textChanged = true;
            updateAbap = (statusChanged && isStatusC2A(task.getStatus())) || titleChanged || textChanged;
        }
        // TODO Auto-generated method stub
        return updateAbap;
    }

    private boolean startStatusPassed(String status) {
        // TODO Auto-generated method stub
        return false;
    }

    private boolean isStatusC2A(String status) {
        // TODO Auto-generated method stub
        return false;
    }

    private CloudRequirementDto getStoredCalmProcess(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    public void start() {
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public boolean isRunning() {
        return running;
    }

    public static List<CloudRequirementDto> getTasks() {

        final int BLOCK_SIZE = 1024;
        final int BUFFER_SIZE = 8 * BLOCK_SIZE;
        DataOutputStream dataOut = null;
        BufferedReader in = null;
        List<CloudRequirementDto> resultList = Lists.newArrayList();

        try {

            // API endpoint for API sandbox
            String url = "https://ridon-it-gmbh-cloudalm.authentication.eu10.hana.ondemand.com.eu10.alm.cloud.sap/api/calm-tasks/v1/tasks?type=CALMREQU&projectId=39333c82-86e9-420a-83d9-606336f2f6ce";

            // Vor der Verbindungsherstellung
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            // setting request method
            connection.setRequestMethod("GET");

            // adding headers
            connection.setRequestProperty("Authorization", "Bearer" + " " + getToken());

            connection.setRequestProperty("DataServiceVersion", "2.0");
            connection.setRequestProperty("Accept", "application/json");

            connection.setDoInput(true);

            int responseCode = connection.getResponseCode();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder response = new StringBuilder();
            char[] charArray = new char[BUFFER_SIZE];
            int charsCount = 0;
            while ((charsCount = in.read(charArray)) != -1) {
                response.append(String.valueOf(charArray, 0, charsCount));
            }
            String jsonString = response.toString();
            ObjectMapper objectMapper = new ObjectMapper();
            resultList = objectMapper.readValue(jsonString, new TypeReference<List<CloudRequirementDto>>() {
            });

            for (CloudRequirementDto dto : resultList) {
                System.out.println(dto.getId() + " " + dto.getAssigneeId());
            }

        } catch (Exception e) {
            // do something with exception
            e.printStackTrace();
        } finally {
            try {
                if (dataOut != null) {
                    dataOut.close();
                }
                if (in != null) {
                    in.close();
                }

            } catch (IOException e) {
                // do something with exception
                e.printStackTrace();
            }
        }
        return resultList;
    }

    public static String getToken() throws Exception {

        final String AUTH_URL = "https://ridon-it-gmbh-cloudalm.authentication.eu10.hana.ondemand.com/oauth/token";
        final String CLIENT_ID = "sb-CALMExtensionAPI!b155059|sapcloudalm!b16907";
        final String CLIENT_SECRET = "iNA2jK89j+anYdw0hCCiKKca5bw=";
        // HTTP-Verbindung herstellen
        URL url = new URL(AUTH_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Request-Parameter und Header setzen
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Authorization", "Basic " + getBasicAuthHeader(CLIENT_ID, CLIENT_SECRET));
        connection.setDoOutput(true);

        // Request-Body senden
        String requestBody = "grant_type=client_credentials";
        try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
            byte[] bodyBytes = requestBody.getBytes(StandardCharsets.UTF_8);
            outputStream.write(bodyBytes);
            outputStream.flush();
        }

        // Response-Code überprüfen und Token extrahieren
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                String responseString = response.toString();
                String accessToken = extractAccessToken(responseString);
                return accessToken;
            }
        } else {
            throw new RuntimeException(
                    "Fehler beim Abrufen des Access Tokens. Statuscode: " + connection.getResponseCode());
        }
    }

    // Helper method to encode the client ID and client secret in Basic Auth format
    private static String getBasicAuthHeader(String clientId, String clientSecret) {
        String authString = clientId + ":" + clientSecret;
        byte[] authBytes = authString.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(authBytes);
    }

    // Helper method to extract the access token from the OAuth response
    private static String extractAccessToken(String responseString)
            throws ParseException, org.json.simple.parser.ParseException {
        JSONParser parser = new JSONParser();
        JSONObject responseJson = (JSONObject) parser.parse(responseString);
        String accessToken = (String) responseJson.get("access_token");
        return accessToken;
    }
}