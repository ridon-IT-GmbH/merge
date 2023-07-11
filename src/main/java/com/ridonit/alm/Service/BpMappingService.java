package com.ridonit.alm.Service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ridonit.alm.model.BpMapping;
import com.ridonit.alm.repo.BpMappingRepo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BpMappingService {

    private final BpMappingRepo bpMappingRepo;
    
    public List<BpMapping> findAll() {
    	return bpMappingRepo.findAll();
    }
}
