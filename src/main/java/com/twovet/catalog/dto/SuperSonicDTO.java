package com.twovet.catalog.dto;

public class SuperSonicDTO {

	private Long id;
	private String superSonicCode;
	private String superSonicName;
	private String descript;
	private Boolean isDelete;

	public SuperSonicDTO() {
		super();
	}
	

	public SuperSonicDTO(String superSonicCode, String superSonicName, String descript) {
		super();
		this.superSonicCode = superSonicCode;
		this.superSonicName = superSonicName;
		this.descript = descript;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSuperSonicCode() {
		return superSonicCode;
	}

	public void setSuperSonicCode(String superSonicCode) {
		this.superSonicCode = superSonicCode;
	}

	public String getSuperSonicName() {
		return superSonicName;
	}

	public void setSuperSonicName(String superSonicName) {
		this.superSonicName = superSonicName;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	

}
