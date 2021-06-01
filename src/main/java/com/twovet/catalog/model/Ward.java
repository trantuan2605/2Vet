package com.twovet.catalog.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "ward")
@NamedQueries({
		@NamedQuery(name = "Ward.findByDistrictId", query = "SELECT w FROM Ward w WHERE 1=1 and w.districtId = :districtId ORDER BY w.districtId desc") })
public class Ward implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "wardid")
	private String wardId;

	@Column(name = "name")
	private String wardName;

	@Column(name = "districtid")
	private String districtId;

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
