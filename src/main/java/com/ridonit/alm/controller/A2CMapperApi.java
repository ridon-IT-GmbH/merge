package com.ridonit.alm.controller;

import com.ridonit.alm.mapper.A2CMapper;
import com.ridonit.alm.mapper.calm.CalmProcess;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor 
public class A2CMapperApi {

	private final A2CMapper mapper;
	
	@PostMapping("/updateCloud")
	public void updateCloud(@RequestBody String json) {
		mapper.updateCloudALM(json);
	}

}
