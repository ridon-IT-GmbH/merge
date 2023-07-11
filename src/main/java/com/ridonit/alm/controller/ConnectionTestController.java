package com.ridonit.alm.controller;

import com.google.common.collect.Lists;
import com.ridonit.alm.client.RestClient;
import com.ridonit.alm.client.SapCloudClient;
import com.ridonit.alm.payload.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.internal.inject.Custom;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        String result = cloudClient.getJsonStringFromCloud();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value="/testUrl")
    public ResponseEntity<TestResponseDto> testUrl(@RequestBody String url) throws Exception {
        TestResponseDto result = restClient.getFromUrl(url);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value="/solman")
    public ResponseEntity<String> testSolman() throws Exception {
        String result = restClient.sendJson();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value="/solmanjson")
    public ResponseEntity<SolmanRequestDto> testSolmanJson() throws Exception {
        SolmanRequestDto request = new SolmanRequestDto();

        HeaderDto head = new HeaderDto();
        head.setPriority("1");
        head.setDescription("first json from java");
        head.setProcessType("ZRIR");
        request.setHeader(head);

        List<PartnerDto> partners = Lists.newArrayList();
        PartnerDto partner1 = new PartnerDto();
        partner1.setFunction("00000001");
        partner1.setPartnerNo("201");
        partners.add(partner1);

        PartnerDto partner2 = new PartnerDto();
        partner2.setFunction("SMIR0001");
        partner2.setPartnerNo("192");
        partners.add(partner2);
        request.setPartners(partners);


        List<CustomerDto> cust = Lists.newArrayList();
        CustomerDto cus1 = new CustomerDto();
        cus1.setFieldname("ZZ_SNOW_NUMBER");
        cus1.setValue("inc12345");
        cust.add(cus1);

        CustomerDto cus2 = new CustomerDto();
        cus2.setFieldname("ZZ_ALM_ID");
        cus2.setValue("883323");
        cust.add(cus2);
        request.setCustomers(cust);


        List<TextDto> texts = Lists.newArrayList();
        TextDto t1 = new TextDto();
        t1.setTextType("ZIR4");
        t1.setContent("ein langer und formatierter text");
        texts.add(t1);

        TextDto t2 = new TextDto();
        t2.setTextType("ZIR4");
        t2.setContent("ein langer und formatierter text");
        texts.add(t2);
        request.setRichTexts(texts);

        List<TextDto> ntexts = Lists.newArrayList();
        TextDto nt1 = new TextDto();
        nt1.setTextType("ZIR4");
        nt1.setContent("ein langer und formatierter text");
        ntexts.add(nt1);

        TextDto nt2 = new TextDto();
        nt2.setTextType("ZIR4");
        nt2.setContent("ein langer und formatierter text");
        ntexts.add(nt2);
        request.setTexts(ntexts);

        return new ResponseEntity<>(request, HttpStatus.OK);
    }
}
