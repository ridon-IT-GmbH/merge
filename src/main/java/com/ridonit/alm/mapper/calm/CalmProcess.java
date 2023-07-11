package com.ridonit.alm.mapper.calm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class CalmProcess {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String calmId;
	private String title;
	private String description;
	private String scopeName;
	private String subStatus;
	private String priorityId;
	private String workstream;
	private String assigneeId;
	private String involvedParties;
	private Date startDate;
	private Date dueDate;
	
}
