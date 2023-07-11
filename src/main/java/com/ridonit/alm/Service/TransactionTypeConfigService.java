package com.ridonit.alm.Service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ridonit.alm.model.TransactionTypeConfig;
import com.ridonit.alm.repo.TransactionTypeConfigRepo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TransactionTypeConfigService {

	private final TransactionTypeConfigRepo ttcRepo;
	
	public List<TransactionTypeConfig> findAll(){
		return ttcRepo.findAll();
	}
}
