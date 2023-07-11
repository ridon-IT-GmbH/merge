package com.ridonit.alm.client;

import com.ridonit.alm.service.TokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
@Slf4j
public class SapCloudClient {
    final String AUTH_URL = "https://ridon-it-gmbh-cloudalm.authentication.eu10.hana.ondemand.com/oauth/token";
    final String CLIENT_ID = "sb-CALMExtensionAPI!b155059|sapcloudalm!b16907";
    final String CLIENT_SECRET = "iNA2jK89j+anYdw0hCCiKKca5bw=";
    final String URI = "https://ridon-it-gmbh-cloudalm.authentication.eu10.hana.ondemand.com.eu10.alm.cloud.sap/api/calm-tasks/v1/tasks?type=CALMREQU&projectId=39333c82-86e9-420a-83d9-606336f2f6ce";
    final int BLOCK_SIZE = 1024;
    final int BUFFER_SIZE = 8 * BLOCK_SIZE;
    public String getJsonStringFromCloud() throws Exception {
        String token = TokenProvider.getToken(AUTH_URL, CLIENT_ID, CLIENT_SECRET);
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        URL urlObj = new URL(URI);
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer" + " " + token);
        connection.setRequestProperty("DataServiceVersion", "2.0");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoInput(true);
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        char[] charArray = new char[BUFFER_SIZE];
        int charsCount = 0;
        while ((charsCount = in.read(charArray)) != -1) {
            response.append(String.valueOf(charArray, 0, charsCount));
        }
        String jsonString = response.toString();
        return jsonString;
    }
}
