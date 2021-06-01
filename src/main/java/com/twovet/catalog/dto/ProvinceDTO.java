package com.twovet.catalog.dto;

public class ProvinceDTO {
	private String provinceId;
	private String provinceName;

	public ProvinceDTO() {
		super();
	}

	public ProvinceDTO(String provinceId, String provinceName) {
		super();
		this.provinceId = provinceId;
		this.provinceName = provinceName;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

}
