package com.ridonit.alm.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
@Slf4j
public class SapCloudClient {

    public String getRequest() {
        final int BLOCK_SIZE = 1024;
        final int BUFFER_SIZE = 8 * BLOCK_SIZE;
        DataOutputStream dataOut = null;
        BufferedReader in = null;

        try {

            //API endpoint for API sandbox
            String url = "https://ridon-it-gmbh-cloudalm.authentication.eu10.hana.ondemand.com.eu10.alm.cloud.sap/api/calm-projects/v1/projects";

            // Vor der Verbindungsherstellung
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            //setting request method
            connection.setRequestMethod("GET");

            //adding headers
            connection.setRequestProperty("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImprdSI6Imh0dHBzOi8vcmlkb24taXQtZ21iaC1jbG91ZGFsbS5hdXRoZW50aWNhdGlvbi5ldTEwLmhhbmEub25kZW1hbmQuY29tL3Rva2VuX2tleXMiLCJraWQiOiJkZWZhdWx0LWp3dC1rZXktLTY3NDkzNDI1MiIsInR5cCI6IkpXVCIsImppZCI6ICJXZkNwRVlKLy9KNnREeEtaS1FkOExuYTMxdEt6Wkg1SnNlYUlpTE9ZN0hJPSJ9.eyJqdGkiOiIzM2IxNGRmNzY3OGI0NzliYmJiYTYzNmIxYzMyYWE3NyIsImV4dF9hdHRyIjp7ImVuaGFuY2VyIjoiWFNVQUEiLCJzdWJhY2NvdW50aWQiOiIxMmFkMWE0ZS03YWY2LTQ4ZTUtOGQxOC05ODRkOWZkOGM4MzEiLCJ6ZG4iOiJyaWRvbi1pdC1nbWJoLWNsb3VkYWxtIiwic2VydmljZWluc3RhbmNlaWQiOiIyYzdhOGZiNy01OTRlLTQ3NjQtYWU1My0zM2FiOTVlYWMxMjEifSwic3ViIjoic2ItQ0FMTUV4dGVuc2lvbkFQSSFiMTU1MDU5fHNhcGNsb3VkYWxtIWIxNjkwNyIsImF1dGhvcml0aWVzIjpbInNhcGNsb3VkYWxtIWIxNjkwNy5jYWxtLWFwaS50YXNrcy53cml0ZSIsInNhcGNsb3VkYWxtIWIxNjkwNy5jYWxtLWFwaS5wcm9qZWN0cy5yZWFkIiwidWFhLnJlc291cmNlIiwic2FwY2xvdWRhbG0hYjE2OTA3LmNhbG0tYXBpLnRhc2tzLnJlYWQiXSwic2NvcGUiOlsic2FwY2xvdWRhbG0hYjE2OTA3LmNhbG0tYXBpLnRhc2tzLndyaXRlIiwidWFhLnJlc291cmNlIiwic2FwY2xvdWRhbG0hYjE2OTA3LmNhbG0tYXBpLnByb2plY3RzLnJlYWQiLCJzYXBjbG91ZGFsbSFiMTY5MDcuY2FsbS1hcGkudGFza3MucmVhZCJdLCJjbGllbnRfaWQiOiJzYi1DQUxNRXh0ZW5zaW9uQVBJIWIxNTUwNTl8c2FwY2xvdWRhbG0hYjE2OTA3IiwiY2lkIjoic2ItQ0FMTUV4dGVuc2lvbkFQSSFiMTU1MDU5fHNhcGNsb3VkYWxtIWIxNjkwNyIsImF6cCI6InNiLUNBTE1FeHRlbnNpb25BUEkhYjE1NTA1OXxzYXBjbG91ZGFsbSFiMTY5MDciLCJncmFudF90eXBlIjoiY2xpZW50X2NyZWRlbnRpYWxzIiwicmV2X3NpZyI6IjRmZTkzOGI5IiwiaWF0IjoxNjg4OTIwMTM1LCJleHAiOjE2ODg5MjM3MzUsImlzcyI6Imh0dHBzOi8vcmlkb24taXQtZ21iaC1jbG91ZGFsbS5hdXRoZW50aWNhdGlvbi5ldTEwLmhhbmEub25kZW1hbmQuY29tL29hdXRoL3Rva2VuIiwiemlkIjoiMTJhZDFhNGUtN2FmNi00OGU1LThkMTgtOTg0ZDlmZDhjODMxIiwiYXVkIjpbInNhcGNsb3VkYWxtIWIxNjkwNy5jYWxtLWFwaS50YXNrcyIsInNiLUNBTE1FeHRlbnNpb25BUEkhYjE1NTA1OXxzYXBjbG91ZGFsbSFiMTY5MDciLCJ1YWEiLCJzYXBjbG91ZGFsbSFiMTY5MDcuY2FsbS1hcGkucHJvamVjdHMiXX0.MFQa2BTvlR3oOuU-t4WmD4hgaf9B2LCo5H_Aj80TmfC7d32Akuete2uVqedryOVsrn2_GQJRHNGfiJPZ-nLKkwIk8KhfqYTh9FcUSwt_B_QNonIHf06cG4xySepcy_hBSaj5sXqIWInYRHMEMQNLQa1zsMqGDr0cUo1PVEorZHdGlB7H3_tslH-7HLYiZ2R89CQ_9EFAcpES8y6Xkj4f5tbuGW4P5y-Vf2mSlwta_mReH8buDI9-iZg3ZuFH_wS9jouYXhJELdrE4kqbpixTBDnN6M43VLipBoz1OOU16xTFP3uOlIaRX1qc8LYlXWBEoDRZ0bmghNR-W7UT3v51PA");
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

            //printing response
            System.out.println(responseCode);
            log.info(response.toString());
            return response.toString();
        } catch (Exception e) {
            //do something with exception
            e.printStackTrace();
        } finally {
            try {
                if (dataOut != null) {
                    dataOut.close();
                    System.out.println("bla");
                }
                if (in != null) {
                    System.out.println("blabla");
                    in.close();
                }
            } catch (IOException e) {
                //do something with exception
                System.out.println("blalalla");
                e.printStackTrace();
            }
        }
        return null;
    }
}
