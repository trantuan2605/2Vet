package com.twovet.catalog.dto;

public class UrineDTO {

	private Long id;
	private String urineCode;
	private String urineName;
	private String descript;
	private Boolean isDelete;

	public UrineDTO() {
		super();
	}
	
	

	public UrineDTO(String urineCode, String urineName, String descript) {
		super();
		this.urineCode = urineCode;
		this.urineName = urineName;
		this.descript = descript;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrineCode() {
		return urineCode;
	}

	public void setUrineCode(String urineCode) {
		this.urineCode = urineCode;
	}

	public String getUrineName() {
		return urineName;
	}

	public void setUrineName(String urineName) {
		this.urineName = urineName;
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
