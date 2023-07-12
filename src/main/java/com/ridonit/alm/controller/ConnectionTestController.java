package com.ridonit.alm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.ridonit.alm.client.BackendClient;
import com.ridonit.alm.client.RestClient;
import com.ridonit.alm.client.SapCloudClient;
import com.ridonit.alm.payload.*;
import com.ridonit.alm.service.SolmanTransferDtoService;
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
    private final SolmanTransferDtoService jsonService;
    private final BackendClient backendClient;
    private final ObjectMapper objectMapper;

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

    @GetMapping(value="/testSolmanJson/{almId}")
    public ResponseEntity<SolmanTransferDto> testSolmanJsonSend(@PathVariable("almId") String almId) throws Exception {
        SolmanTransferDto result = jsonService.getByAlmId(almId, "from testing by swagger without any ticket");
        String jsonString2 = objectMapper.writeValueAsString(result);
        //String jsonString = "{    \"AlmId\": \"11334354\",    \"Json\":    \"{    header:{    priority:1,    description:a description,    process_type:ZRIR    },    partners:[{    function:SMCD0006,    partner_no:374    },{    function:SMIR0001,    partner_no:375    }    ],    customers:[{    fieldname:ZZ_SNOW_NUMBER,    value:inc12345    },{    fieldname:ZZFLD000012,    value:883323    },{    fieldname:ZZ_ALM_ID,    value:74272    }    ],    rich_texts:[{    text_type:ZIR4,    content:ein langer und formatierter text    }    ]    }\"}";
        String jsonString = "{    \"AlmId\": \"11334354\",    \"Json\":    \"{    \\\"header\\\":{    \\\"priority\\\":\\\"1\\\",    \\\"description\\\":\\\"a description\\\",    \\\"process_type\\\":\\\"ZRIR\\\"    },    \\\"partners\\\":[{    \\\"function\\\":\\\"SMCD0006\\\",    \\\"partner_no\\\":374    },{    \\\"function\\\":\\\"SMIR0001\\\",    \\\"partner_no\\\":375    }    ],    \\\"customers\\\":[{    \\\"fieldname\\\":\\\"ZZ_SNOW_NUMBER\\\",    \\\"value\\\":\\\"inc12345\\\"    },{    \\\"fieldname\\\":\\\"ZZFLD000012\\\",    \\\"value\\\":\\\"883323\\\"    },{    \\\"fieldname\\\":\\\"ZZ_ALM_ID\\\",    \\\"value\\\":\\\"74272\\\"    }    ],    \\\"rich_texts\\\":[{    \\\"text_type\\\":\\\"ZIR4\\\",    \\\"content\\\":\\\"ein langer und formatierter text\\\"    }    ]    }\"}";

        String link = "https://ridon-it-gmbh-development-ridon-ridon-development-alm-p361f7ea6.cfapps.eu10.hana.ondemand.com/sap/opu/odata/RIDONIT/CALM_API_SRV/Cloud_ALM_APISet/";
        backendClient.poster(jsonString, link);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping(value="/solmanjson/{almId}")
    public ResponseEntity<SolmanTransferDto> testSolmanJson(@PathVariable("almId") String almId) throws Exception {
        SolmanTransferDto result = jsonService.getByAlmId(almId, "from testing by swagger without any ticket");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
