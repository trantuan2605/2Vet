package com.twovet.catalog.dto;

public class ParasiticDTO {

	private Long id;
	private String parasiticTestCode;
	private String parasiticTestName;
	private String descript;
	private Boolean isDelete;

	public ParasiticDTO() {
		super();
	}

	public ParasiticDTO(String parasiticTestCode, String parasiticTestName, String descript) {
		super();
		this.parasiticTestCode = parasiticTestCode;
		this.parasiticTestName = parasiticTestName;
		this.descript = descript;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParasiticTestCode() {
		return parasiticTestCode;
	}

	public void setParasiticTestCode(String parasiticTestCode) {
		this.parasiticTestCode = parasiticTestCode;
	}

	public String getParasiticTestName() {
		return parasiticTestName;
	}

	public void setParasiticTestName(String parasiticTestName) {
		this.parasiticTestName = parasiticTestName;
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
