package com.ridonit.alm.client;

import com.ridonit.alm.payload.BoredDto;
import com.ridonit.alm.payload.TestResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient {

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
}
