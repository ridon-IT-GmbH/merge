package com.ridonit.alm.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ridonit.alm.model.MapperConfg;
import com.ridonit.alm.repo.MapperConfigRepo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MapperConfigService {
	private final MapperConfigRepo mapperConfigRepo;
	
	public List<MapperConfg> findAll(){
		return mapperConfigRepo.findAll();
	}

}
