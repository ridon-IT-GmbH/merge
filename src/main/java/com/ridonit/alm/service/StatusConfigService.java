package com.ridonit.alm.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ridonit.alm.model.StatusConfig;
import com.ridonit.alm.repo.StatusConfigRepo;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class StatusConfigService {
	
    private final StatusConfigRepo statusRepo;
    
    public List<StatusConfig> findAll() {
    	return statusRepo.findAll();
    }
    
}
