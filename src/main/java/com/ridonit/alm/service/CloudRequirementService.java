package com.ridonit.alm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridonit.alm.model.CloudRequirement;
import com.ridonit.alm.payload.CloudRequirementDto;
import com.ridonit.alm.repo.CloudRequirementRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class CloudRequirementService {

    private final CloudRequirementRepo requirementRepo;
    private final ModelMapper modelMapper;

    public List<CloudRequirement> findAll() {
        return requirementRepo.findAll();
    }

    public CloudRequirement findByCalmId(String calmId) {
        try {
            Optional<CloudRequirement> optional = requirementRepo.findById(calmId);
            CloudRequirement requirement = optional.get();
            return requirement;
        } catch(Exception e) {
            return null;
        }
    }

    public CloudRequirement saveRequirement(CloudRequirement cloudRequirement) {
        cloudRequirement = requirementRepo.save(cloudRequirement);
        return cloudRequirement;
    }

    public CloudRequirement saveRequirementDto(CloudRequirementDto cloudRequirementDto) {
        CloudRequirement cloudRequirement = modelMapper.map(cloudRequirementDto, CloudRequirement.class);
        return this.saveRequirement(cloudRequirement);
    }
}
