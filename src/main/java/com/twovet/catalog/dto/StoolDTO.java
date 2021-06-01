package com.twovet.catalog.dto;

public class StoolDTO {

	private Long id;
	private String stoolCode;
	private String stoolName;
	private String descript;
	private Boolean isDelete;

	public StoolDTO() {
		super();
	}
	

	public StoolDTO(String stoolCode, String stoolName, String descript) {
		super();
		this.stoolCode = stoolCode;
		this.stoolName = stoolName;
		this.descript = descript;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStoolCode() {
		return stoolCode;
	}

	public void setStoolCode(String stoolCode) {
		this.stoolCode = stoolCode;
	}

	public String getStoolName() {
		return stoolName;
	}

	public void setStoolName(String stoolName) {
		this.stoolName = stoolName;
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
