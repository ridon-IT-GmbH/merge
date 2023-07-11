package com.ridonit.alm.mapper;

import com.ridonit.alm.mapper.calm.CalmProcess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class C2AMapper {

	public List<CalmProcess> getModifiedProcesses() {
		List<CalmProcess> procList = new ArrayList<>();
		procList.addAll(callApi());
		return procList;
	}

	private Collection<? extends CalmProcess> callApi() {
		// TODO Auto-generated method stub
		return null;
	}
}
