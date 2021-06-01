package com.twovet.catalog.dto;

public class SymptomDTO {

	private Long id;

	private String symptomCode;

	private String symptomName;

	public SymptomDTO() {
		super();
	}

	public SymptomDTO(String symptomCode, String symptomName) {
		super();
		this.symptomCode = symptomCode;
		this.symptomName = symptomName;
	}

	public SymptomDTO(Long id, String symptomCode, String symptomName) {
		super();
		this.id = id;
		this.symptomCode = symptomCode;
		this.symptomName = symptomName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSymptomCode() {
		return symptomCode;
	}

	public void setSymptomCode(String symptomCode) {
		this.symptomCode = symptomCode;
	}

	public String getSymptomName() {
		return symptomName;
	}

	public void setSymptomName(String symptomName) {
		this.symptomName = symptomName;
	}

}
