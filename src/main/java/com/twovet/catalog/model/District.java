package com.twovet.catalog.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "district")
@NamedQueries({
		@NamedQuery(name = "District.findByProvinceId", query = "SELECT d FROM District d WHERE 1=1 and d.provinceId = :provinceId ORDER BY d.districtId desc") })
public class District implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "districtid")
	private String districtId;
	
	@Column(name = "name")
	private String districtName;
	
	@Column(name = "provinceid")
	private String provinceId;

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
