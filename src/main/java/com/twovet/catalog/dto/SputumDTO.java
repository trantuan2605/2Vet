package com.twovet.catalog.dto;

public class SputumDTO {

	private Long id;
	private String sputumCode;
	private String sputumName;
	private String descript;
	private Boolean isDelete;

	public SputumDTO() {
		super();
	}

	public SputumDTO(String sputumCode, String sputumName, String descript) {
		super();
		this.sputumCode = sputumCode;
		this.sputumName = sputumName;
		this.descript = descript;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSputumCode() {
		return sputumCode;
	}

	public void setSputumCode(String sputumCode) {
		this.sputumCode = sputumCode;
	}

	public String getSputumName() {
		return sputumName;
	}

	public void setSputumName(String sputumName) {
		this.sputumName = sputumName;
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
