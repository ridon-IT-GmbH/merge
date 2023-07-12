package com.ridonit.alm.client;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

@Component
@NoArgsConstructor
@Slf4j
public class BackendClient {

    private String sessionCookies;
    private String csrfToken;

    public void poster(String jsonBody, String link) throws IOException {
        // this.setDefaultAuthenticator(user, pass);
        try {
            fetchSessionHeaderFields(link);
            postRequest(jsonBody, link);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error(e.getMessage());
        }

    }

    private void fetchSessionHeaderFields(String link) throws IOException {
        URL url = new URL(link);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("X-CSRF-Token", "fetch");
        con.setRequestProperty("set-cookie", "fetch");

        // Reading Response
        int status = con.getResponseCode();

        Reader streamReader = null;

        if (status < 502) {
            StringBuffer sb = new StringBuffer(con.getHeaderFields().get("set-cookie").toString());
            // Delete leading [ and trailing ] character
            sb.deleteCharAt(con.getHeaderFields().get("set-cookie").toString().length() - 1);
            sb.deleteCharAt(0);
            this.sessionCookies = sb.toString();
            this.sessionCookies = this.sessionCookies.replaceAll("Path=/; HttpOnly; Secure", "");
            this.sessionCookies = this.sessionCookies.replaceAll(";", "");
            this.sessionCookies = this.sessionCookies.replaceAll(" ,", ";");
            this.csrfToken = con.getHeaderField("X-CSRF-Token");
            return;
        }
    }

    private void postRequest(String jsonBody, String link) throws IOException {
        // Creating the connection
        URL url = new URL(link);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("X-CSRF-Token", this.csrfToken);
        con.setRequestProperty("Cookie", this.sessionCookies);

        // Setting JSON Body
        con.setDoOutput(true);
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonBody.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Reading response
        int status = con.getResponseCode();

        Reader streamReader = null;

        if (status > 299) {
            streamReader = new InputStreamReader(con.getErrorStream());
        } else {
            streamReader = new InputStreamReader(con.getInputStream());
        }

        BufferedReader in = new BufferedReader(streamReader);
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
    }
}
