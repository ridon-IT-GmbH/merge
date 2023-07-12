package com.ridonit.alm.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridonit.alm.payload.BoredDto;
import com.ridonit.alm.payload.SolmanTransferDto;
import com.ridonit.alm.payload.TestResponseDto;
import com.ridonit.alm.service.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collections;

@Component
@Slf4j
@RequiredArgsConstructor
public class RestClient {
    final String AUTH_URL = "https://development-ridon.authentication.eu10.hana.ondemand.com/oauth/token";
    final String CLIENT_ID = "sb-cloneb996458f76f14375ad3073044efa0479!b115789|destination-xsappname!b404";
    final String CLIENT_SECRET = "ed4f835a-4383-4def-bfc8-3953e217d474$LHZn4r_a_RjHFtG2sKl9RpDP_V1PIEXKUGx7blmIWB8=";
    final String SOLMAN_URL = "https://ridon-it-gmbh-development-ridon-ridon-development-alm-p361f7ea6.cfapps.eu10.hana.ondemand.com/sap/opu/odata/RIDONIT/CALM_API_SRV/Cloud_ALM_APISet/";

    private final ObjectMapper objectMapper;

    public BoredDto getBoringInformation() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "https://www.boredapi.com/api/activity";

        ResponseEntity<BoredDto> response = restTemplate.getForEntity(fooResourceUrl, BoredDto.class);
        BoredDto result = response.getBody();
        return result;
    }

    public TestResponseDto getFromUrl(String url) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        TestResponseDto result = new TestResponseDto();
        result.setStatus(response.getStatusCode());
        result.setStatusCode(response.getStatusCodeValue());
        result.setHeader(response.getHeaders());
        result.setBody(response.getBody());

        return result;
    }

    public String sendJson() throws Exception {
        String url = "https://ridon-it-gmbh-development-ridon-ridon-development-alm-p361f7ea6.cfapps.eu10.hana.ondemand.com/sap/opu/odata/RIDONIT/CALM_API_SRV/Cloud_ALM_APISet/";
        String json = "{\"AlmId\":\"123456\",\"Json\":\"{'header': {'priority': '1','description': 'second json test is running','process_type': 'ZRIR'},'partners': [{'function': '00000001','partner_no': '201'},{'function': 'SMIR0001','partner_no': '192'}],'customers': [{'fieldname': 'ZZ_SNOW_NUMBER','value': 'inc12345'},{'fieldname': 'ZZ_ALM_ID','value': '883323'}],'rich_texts': [{'text_type': 'ZIR4','content': 'ein langer und formatierter text'},{'text_type': 'ZIR4','content': 'noch ein langer und formatierter text'}],'texts': [{'text_type': 'IR03','content': 'ein langer und nicht formatierter text'},{'text_type': 'IR03','content': 'noch ein langer und nicht formatierter text'}]}\"}";
        return sendJsonBerndsWay(url, json);
    }

    public String sendJson(String url, String json) throws Exception {
        String token = TokenProvider.getToken(AUTH_URL, CLIENT_ID, CLIENT_SECRET);
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> request = new HttpEntity<String>(json, headers);

        try {
            return restTemplate.postForObject(url, request, String.class);
        }
        catch(Exception e) {
            log.error("URI: " + url);
            e.printStackTrace();
        }
        return null;
    }

    public String sendToSolutionManager(SolmanTransferDto requestDto) throws Exception {
        String token = TokenProvider.getToken(AUTH_URL, CLIENT_ID, CLIENT_SECRET);
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Bearer " + token);

        String jsonString = objectMapper.writeValueAsString(requestDto);
        HttpEntity<String> request = new HttpEntity<String>(jsonString, headers);

        try {
            return restTemplate.postForObject(SOLMAN_URL, request, String.class);
        }
        catch(Exception e) {
            log.error("URI: " + SOLMAN_URL);
            e.printStackTrace();
        }
        return null;
    }

    public String sendJsonBerndsWay(String url, String json) throws Exception {
        String token = TokenProvider.getToken(AUTH_URL, CLIENT_ID, CLIENT_SECRET);


        return token;
    }
}
