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
import com.ridonit.alm.payload.SolmanTransferDto;
import com.ridonit.alm.service.CloudRequirementService;
import com.ridonit.alm.service.SolmanTransferDtoService;
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
	private final CloudRequirementService requirementService;
	private final SolmanTransferDtoService solmanDtoService;

	public List<CalmProcess> getModifiedProcesses(String jsonString) throws Exception {
		List<CloudRequirementDto> reqDtoList = objectMapper.readValue(jsonString, new TypeReference<List<CloudRequirementDto>>() {});
		List<CloudRequirement> cloudRequirements = objectMapper.convertValue(reqDtoList, new TypeReference<List<CloudRequirement>>(){});

		for(CloudRequirement req : cloudRequirements) {
			/*
			CloudRequirement existing = requirementService.findByCalmId(req.getId());
			if(existing != null) {
				if(existing.equals(req) == false) {
					existing = merge(existing, req);
					//requirementService.saveRequirement(existing);
					//send change request
				}
			}
			else {
				requirementService.saveRequirement(req);
				SolmanTransferDto request = solmanDtoService.getByAlmId(req.getId(), req.toString());
				restClient.sendToSolutionManager(request);
			}

			 */


		}


		List<CalmProcess> procList = new ArrayList<>();
		//procList.addAll(callApi());
		return procList;
	}

	private Collection<? extends CalmProcess> callApi() {
		// TODO Auto-generated method stub
		return null;
	}

	private CloudRequirement merge(CloudRequirement existing, CloudRequirement merge) {
		existing.setProjectId(merge.getProjectId());
		existing.setTitle(merge.getTitle());
		existing.setType(merge.getType());
		existing.setStatus(merge.getStatus());
		existing.setExternalId(merge.getExternalId());
		existing.setStartDate(merge.getStartDate());
		existing.setDueDate(merge.getDueDate());
		existing.setPriorityId(merge.getPriorityId());
		existing.setAssigneeId(merge.getAssigneeId());
		existing.setAssigneeName(merge.getAssigneeName());
		existing.setTimeboxName(merge.getTimeboxName());
		existing.setTimeboxStartDate(merge.getTimeboxStartDate());
		existing.setTimeboxEndDate(merge.getTimeboxEndDate());
		existing.setParentId(merge.getParentId());
		existing.setSubStatus(merge.getSubStatus());
		existing.setApprovalState(merge.getApprovalState());
		existing.setDescription(merge.getDescription());
		existing.setScopeId(merge.getScopeId());
		existing.setScopeName(merge.getScopeName());
		existing.setAssignedRoleId(merge.getAssignedRoleId());
		existing.setAssignedRoleName(merge.getAssignedRoleName());
		existing.setStoryPoints(merge.getStoryPoints());
		existing.setObsolete(merge.getObsolete());
		existing.setWorkstream(merge.getWorkstream());
		existing.setDeliverableId(merge.getDeliverableId());
		existing.setCreatedTimestamp(merge.getCreatedTimestamp());
		existing.setLastChangedTimestamp(merge.getLastChangedTimestamp());
		existing.setEffort(merge.getEffort());
		existing.setInvolvedParties(merge.getInvolvedParties());
		return existing;
	}
}
