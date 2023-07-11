package com.ridonit.alm.repo;

import com.ridonit.alm.model.CloudRequirement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CloudRequirementRepo extends JpaRepository<CloudRequirement, Integer> {
}
