package com.ridonit.alm.mapper.abap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Map;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class AbapProcess {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String calmId;
	private String processType;
	private String keyField;
	private String keyValue;
	
	@Transient
	private Map<String, String> jsonMap;
}
