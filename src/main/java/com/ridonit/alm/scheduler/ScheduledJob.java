package com.ridonit.alm.scheduler;

import com.ridonit.alm.client.SapCloudClient;
import com.ridonit.alm.mapper.C2AMapper;
import com.ridonit.alm.model.Language;
import com.ridonit.alm.service.LanguageService;
import com.ridonit.alm.service.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ScheduledJob {


    private final LanguageService service;
    private final SapCloudClient cloudClient;
    private final C2AMapper mapper;

    @Scheduled(fixedDelay = 10000)
    public void getCloudRequirements() throws Exception {
        String cloudJsonString = cloudClient.getJsonStringFromCloud();
        mapper.getModifiedProcesses(cloudJsonString);
        log.info(cloudJsonString);
    }

}
