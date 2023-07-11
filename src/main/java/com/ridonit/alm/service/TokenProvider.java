package com.ridonit.alm.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Base64;

public class TokenProvider {

    public static String getToken(String authUrl, String clientId, String clientSecret) throws Exception {
        //final String AUTH_URL = "https://ridon-it-gmbh-cloudalm.authentication.eu10.hana.ondemand.com/oauth/token";
        //final String CLIENT_ID = "sb-CALMExtensionAPI!b155059|sapcloudalm!b16907";
        //final String CLIENT_SECRET = "iNA2jK89j+anYdw0hCCiKKca5bw=";
        // HTTP-Verbindung herstellen
        URL url = new URL(authUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Request-Parameter und Header setzen
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Authorization", "Basic " + getBasicAuthHeader(clientId, clientSecret));
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
