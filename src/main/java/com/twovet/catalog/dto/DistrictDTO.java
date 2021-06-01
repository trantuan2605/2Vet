package com.twovet.catalog.dto;

public class DistrictDTO {

	private String districtId;
	private String districtName;
	private String provinceId;

	public DistrictDTO() {
		
	}
	
	public DistrictDTO(String districtId, String districtName, String provinceId) {
		super();
		this.districtId = districtId;
		this.districtName = districtName;
		this.provinceId = provinceId;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

}
