package com.twovet.catalog.dto;

public class VacinationDTO {
	private Long id;
	private String vacineCode;
	private String vacineName;
	private String descript;

	public VacinationDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public VacinationDTO(Integer id, String vacineCode, String vacineName, String descript ) {
		this.id = Long.valueOf(id);
		this.vacineCode = vacineCode;
		this.vacineName = vacineName;
		this.descript = descript;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVacineCode() {
		return vacineCode;
	}

	public void setVacineCode(String vacineCode) {
		this.vacineCode = vacineCode;
	}

	public String getVacineName() {
		return vacineName;
	}

	public void setVacineName(String vacineName) {
		this.vacineName = vacineName;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}
}
