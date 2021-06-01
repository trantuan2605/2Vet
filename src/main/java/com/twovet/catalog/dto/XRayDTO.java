package com.twovet.catalog.dto;

public class XRayDTO {

	private Long id;
	private String xQCode;
	private String xQName;
	private String descript;
	private Boolean isDelete;

	public XRayDTO() {
		super();
	}

	public XRayDTO(String xQCode, String xQName, String descript) {
		super();
		this.xQCode = xQCode;
		this.xQName = xQName;
		this.descript = descript;
	}

	public String getxQCode() {
		return xQCode;
	}

	public void setxQCode(String xQCode) {
		this.xQCode = xQCode;
	}

	public String getxQName() {
		return xQName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public void setxQName(String xQName) {
		this.xQName = xQName;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

}
