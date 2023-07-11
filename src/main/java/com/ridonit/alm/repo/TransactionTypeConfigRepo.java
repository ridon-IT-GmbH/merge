package com.ridonit.alm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ridonit.alm.model.TransactionTypeConfig;

public interface TransactionTypeConfigRepo extends JpaRepository<TransactionTypeConfig, Integer> {

}
