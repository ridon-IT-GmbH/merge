package com.ridonit.alm.mapper.calm;

public enum CalmField {
	
	CALM_ID("id"),
	DESCRIPTION("title"),
	TEXT("description"),
	TRANSACTION_TYP("scopeName"),
	STATUS("substatus"),
	PRIORITY("priorityID"),
	START_DATE("startDate"),
	DUE_DATE("dueDate"),
	WORKSTREAM("workstream"),
	RESPONSIBLE_ID("asigneeId"),
	INVOLVED_PARTIES("involvedParties");
	
	private String technicalName;
	
	CalmField(String technicalName) {
		this.technicalName = technicalName;
	}


	public String getTechnicalName(){
		return this.technicalName;
	}
	

}
