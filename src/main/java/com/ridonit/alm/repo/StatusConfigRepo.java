package com.ridonit.alm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ridonit.alm.model.StatusConfig;

public interface StatusConfigRepo extends JpaRepository<StatusConfig, Integer> {
}
