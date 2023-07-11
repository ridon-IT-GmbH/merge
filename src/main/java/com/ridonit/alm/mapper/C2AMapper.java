package com.ridonit.alm.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.ridonit.alm.client.RestClient;
import com.ridonit.alm.mapper.calm.CalmProcess;
import com.ridonit.alm.model.CloudRequirement;
import com.ridonit.alm.payload.CloudRequirementDto;
import com.ridonit.alm.payload.HeaderDto;
import com.ridonit.alm.payload.SolmanRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class C2AMapper {

	private final ObjectMapper objectMapper;
	private final RestClient restClient;

	public List<CalmProcess> getModifiedProcesses(String jsonString) throws Exception {
		List<CloudRequirementDto> reqDtoList = objectMapper.readValue(jsonString, new TypeReference<List<CloudRequirementDto>>() {});
		List<CloudRequirement> cloudRequirements = objectMapper.convertValue(reqDtoList, new TypeReference<List<CloudRequirement>>(){});

		if(cloudRequirements.isEmpty() == false) {
			restClient.sendJson();
		}

		List<CalmProcess> procList = new ArrayList<>();
		procList.addAll(callApi());
		return procList;
	}

	private Collection<? extends CalmProcess> callApi() {
		// TODO Auto-generated method stub
		return null;
	}
}
