package com.ridonit.alm.controller;

import com.ridonit.alm.client.RestClient;
import com.ridonit.alm.client.SapCloudClient;
import com.ridonit.alm.payload.TestResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping(value="con-test")
@Slf4j
@RequiredArgsConstructor
public class ConnectionTestController {

    private final RestClient restClient;
    private final SapCloudClient cloudClient;

    @GetMapping(value="/quick/{url}")
    public ResponseEntity<TestResponseDto> testing(@PathVariable("url") String url) throws Exception {
        TestResponseDto result = restClient.getFromUrl(url);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value="cloudAlm")
    public ResponseEntity<String> testing() throws Exception {
        String result = cloudClient.getRequest();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value="/testUrl")
    public ResponseEntity<TestResponseDto> testUrl(@RequestBody String url) throws Exception {
        TestResponseDto result = restClient.getFromUrl(url);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
