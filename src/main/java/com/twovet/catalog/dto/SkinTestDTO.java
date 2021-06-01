package com.twovet.catalog.dto;

public class SkinTestDTO {

	private Long id;
	private String skinTestCode;
	private String skinTestName;
	private String descript;
	private Boolean isDelete;

	public SkinTestDTO() {
		super();
	}
	

	public SkinTestDTO(String skinTestCode, String skinTestName, String descript) {
		super();
		this.skinTestCode = skinTestCode;
		this.skinTestName = skinTestName;
		this.descript = descript;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSkinTestCode() {
		return skinTestCode;
	}

	public void setSkinTestCode(String skinTestCode) {
		this.skinTestCode = skinTestCode;
	}

	public String getSkinTestName() {
		return skinTestName;
	}

	public void setSkinTestName(String skinTestName) {
		this.skinTestName = skinTestName;
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
