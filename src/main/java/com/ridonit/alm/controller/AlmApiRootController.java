package com.ridonit.alm.controller;

import com.ridonit.alm.client.RestClient;
import com.ridonit.alm.payload.BoredDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping(value="root")
@Slf4j
@RequiredArgsConstructor
public class AlmApiRootController {

    private final RestClient restClient;

    @GetMapping(value="/isAlive")
    public ResponseEntity<String> isAlive() throws Exception {
        String aliveMessage = "alm-mapper is alive";
        log.info(aliveMessage);

        log.trace("TRACE Message");
        log.debug("DEBUG Message");
        log.info("INFO  Message");
        log.warn("WARN  Message");
        log.error("ERROR Message");

        return new ResponseEntity<>(aliveMessage, HttpStatus.OK);
    }

    @GetMapping(value="/boring")
    public ResponseEntity<BoredDto> beBoring() throws Exception {
        BoredDto result = restClient.getBoringInformation();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
