package com.ridonit.alm.repo;

import com.ridonit.alm.model.CloudRequirement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CloudRequirementRepo extends JpaRepository<CloudRequirement, Integer> {
    Optional<CloudRequirement> findById(String calmId);
}
