package com.ridonit.alm.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridonit.alm.client.BackendClient;
import com.ridonit.alm.client.RestClient;
import com.ridonit.alm.mapper.calm.CalmProcess;
import com.ridonit.alm.model.CloudRequirement;
import com.ridonit.alm.payload.CloudRequirementDto;
import com.ridonit.alm.payload.SolmanTransferDto;
import com.ridonit.alm.service.CloudRequirementService;
import com.ridonit.alm.service.SolmanTransferDtoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class C2AMapper {
	private final String URL = "https://ridon-it-gmbh-development-ridon-ridon-development-alm-p361f7ea6.cfapps.eu10.hana.ondemand.com/sap/opu/odata/RIDONIT/CALM_API_SRV/Cloud_ALM_APISet/";


	private final ObjectMapper objectMapper;
	private final BackendClient backendClient;
	private final CloudRequirementService requirementService;
	private final SolmanTransferDtoService solmanDtoService;

	public List<CalmProcess> getModifiedProcesses(String jsonString) throws Exception {
		List<CloudRequirementDto> reqDtoList = objectMapper.readValue(jsonString, new TypeReference<List<CloudRequirementDto>>() {});
		List<CloudRequirement> cloudRequirements = objectMapper.convertValue(reqDtoList, new TypeReference<List<CloudRequirement>>(){});



		for(CloudRequirement req : cloudRequirements) {
			if(req.getSubStatus().equals("IN_REALIZATION")) {
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
					SolmanTransferDto request = solmanDtoService.getByAlmId(req.getId(), req);
					String solmanJsonString = objectMapper.writeValueAsString(request);
					//String solmanJsonString = "{    \"AlmId\": \"11334354\",    \"Json\":    \"{    \\\"header\\\":{    \\\"priority\\\":\\\"1\\\",    \\\"description\\\":\\\"a description\\\",    \\\"process_type\\\":\\\"ZRIR\\\"    },    \\\"partners\\\":[{    \\\"function\\\":\\\"SMCD0006\\\",    \\\"partner_no\\\":374    },{    \\\"function\\\":\\\"SMIR0001\\\",    \\\"partner_no\\\":375    }    ],    \\\"customers\\\":[{    \\\"fieldname\\\":\\\"ZZ_SNOW_NUMBER\\\",    \\\"value\\\":\\\"inc12345\\\"    },{    \\\"fieldname\\\":\\\"ZZFLD000012\\\",    \\\"value\\\":\\\"883323\\\"    },{    \\\"fieldname\\\":\\\"ZZ_ALM_ID\\\",    \\\"value\\\":\\\"74272\\\"    }    ],    \\\"rich_texts\\\":[{    \\\"text_type\\\":\\\"ZIR4\\\",    \\\"content\\\":\\\"ein langer und formatierter text\\\"    }    ]    }\"}";
					backendClient.poster(solmanJsonString, URL);
				}
			}
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
		existing.setStartdate(merge.getStartdate());
		existing.setDuedate(merge.getDuedate());
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
