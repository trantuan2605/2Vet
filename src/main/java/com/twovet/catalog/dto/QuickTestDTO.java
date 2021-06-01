package com.twovet.catalog.dto;

public class QuickTestDTO {
	private Long id;
	private String quickCode;
	private String quickName;
	private String descript;

	public QuickTestDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public QuickTestDTO(Integer id, String quickCode, String quickName, String descript ) {
		this.id = Long.valueOf(id);
		this.quickCode = quickCode;
		this.quickName = quickName;
		this.descript = descript;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuickCode() {
		return quickCode;
	}

	public void setQuickCode(String quickCode) {
		this.quickCode = quickCode;
	}

	public String getQuickName() {
		return quickName;
	}

	public void setQuickName(String quickName) {
		this.quickName = quickName;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}
}
