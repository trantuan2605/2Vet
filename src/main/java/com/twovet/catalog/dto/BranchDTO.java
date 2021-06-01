package com.twovet.catalog.dto;

import java.math.BigInteger;
import java.util.Date;

public class BranchDTO {
	private Long id;
	private String branchCode;
	private String branchName;
	private String address;
	private String phone;
	private String descript;
	private String wardId;
	private String wardName;
	private String districtId;
	private String districtName;
	private String provinceId;
	private String provinceName;
	private String managerId;
	private String subManagerId;
	private String path;
	private Date doe;
	private String doeStr;
	private BigInteger sequenceCode;
	private String managerName;
	private String subManagerName;
	private String phoneManager;

	public BranchDTO() {
		// TODO Auto-generated constructor stub
	}

	public BranchDTO(Integer id, String branchCode, String branchName, String address, String phone, String descript, String wardId,
			String wardName, String districtId, String districtName, String provinceId, String provinceName,
			String path, String doeStr, BigInteger sequenceCode ) {
		super();
		this.id = Long.valueOf(id);
		this.branchCode = branchCode;
		this.branchName = branchName;
		this.address = address;
		this.phone = phone;
		this.descript = descript;
		this.wardId = wardId;
		this.wardName = wardName;
		this.districtId = districtId;
		this.districtName = districtName;
		this.provinceId = provinceId;
		this.provinceName = provinceName;
		this.path = path;
		this.doeStr = doeStr;
		this.sequenceCode = sequenceCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
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

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getSubManagerId() {
		return subManagerId;
	}

	public void setSubManagerId(String subManagerId) {
		this.subManagerId = subManagerId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getDoe() {
		return doe;
	}

	public void setDoe(Date doe) {
		this.doe = doe;
	}

	public BigInteger getSequenceCode() {
		return sequenceCode;
	}

	public void setSequenceCode(BigInteger sequenceCode) {
		this.sequenceCode = sequenceCode;
	}

	public String getDoeStr() {
		return doeStr;
	}

	public void setDoeStr(String doeStr) {
		this.doeStr = doeStr;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getSubManagerName() {
		return subManagerName;
	}

	public void setSubManagerName(String subManagerName) {
		this.subManagerName = subManagerName;
	}

	public String getPhoneManager() {
		return phoneManager;
	}

	public void setPhoneManager(String phoneManager) {
		this.phoneManager = phoneManager;
	}

}
