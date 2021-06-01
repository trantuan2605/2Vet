package com.twovet.catalog.dto;

public class WardDTO {

	private String wardId;
	private String wardName;
	private String districtId;

	public WardDTO() {
		
	}
	
	public WardDTO(String wardId, String wardName, String districtId) {
		super();
		this.wardId = wardId;
		this.wardName = wardName;
		this.districtId = districtId;
	}

	public String getWardId() {
		return wardId;
	}

	public void setWardId(String wardId) {
		this.wardId = wardId;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

}
