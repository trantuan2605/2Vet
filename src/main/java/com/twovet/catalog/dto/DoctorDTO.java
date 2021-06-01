package com.twovet.catalog.dto;

import java.math.BigInteger;
import java.util.Date;

public class DoctorDTO {
	private Long id;
	private String doctorCode;
	private String doctorName;
	private Integer gender;
	private String genderStr;
	private String cmnd;
	private String phone;
	private String email;
	private String address;
	private Date dob;
	private String dobStr;
	private Date dow;
	private String dowStr;
	private String majors;
	private String experience;
	private String descript;
	private BigInteger sequenceCode;
	private String wardId;
	private String wardName;
	private String districtId;
	private String districtName;
	private String provinceId;
	private String provinceName;
	private String path;
	private String bankNo;
	private String position;
	private String level;
	private String country;
	private String branchCode;
	private String isManagerStr;
	private int isManager;
	private String isSubManagerStr;
	private int isSubManager;

	public DoctorDTO() {
		// TODO Auto-generated constructor stub
	}

	public DoctorDTO(String doctorCode, String doctorName, Integer isManager, Integer isSubManager, String phone) {
		super();
		this.doctorCode = doctorCode;
		this.doctorName = doctorName;
		this.isManager = isManager != null ? isManager : 0;
		this.isSubManager = isSubManager != null ? isSubManager : 0;
		this.phone = phone;
	}

	public DoctorDTO(String doctorCode, String doctorName, String address, String phone, String cmnd, String email,
			String experience, String dobStr, String descript, Integer gender, String majors, String genderStr,
			BigInteger sequenceCode) {
		this.doctorCode = doctorCode;
		this.doctorName = doctorName;
		this.address = address;
		this.gender = gender;
		this.phone = phone;
		this.cmnd = cmnd;
		this.email = email;
		this.experience = experience;
		this.dobStr = dobStr;
		this.descript = descript;
		this.descript = descript;
		this.majors = majors;
		this.genderStr = genderStr;
		this.sequenceCode = sequenceCode;
	}

	public DoctorDTO(String doctorCode, String doctorName, String address, String phone, String cmnd, String email,
			String experience, String dobStr, String descript, String gender, String majors, String genderStr,
			String branchCode, String isManagerStr, String isSubManagerStr) {
		this.doctorCode = doctorCode;
		this.doctorName = doctorName;
		this.address = address;
		this.gender = gender != null ? Integer.valueOf(gender) : new Integer(3);
		this.phone = phone;
		this.cmnd = cmnd;
		this.email = email;
		this.experience = experience;
		this.dobStr = dobStr;
		this.descript = descript;
		this.descript = descript;
		this.majors = majors;
		this.genderStr = genderStr;
		this.branchCode = branchCode;
		this.isManagerStr = isManagerStr;
		this.isSubManagerStr = isSubManagerStr;
	}

	// Create constructor to map customerExt
	public DoctorDTO(String doctorCode, String doctorName, String wardName, String wardId, String districtName,
			String districtId, String provinceName, String provinceId) {
		this.doctorCode = doctorCode;
		this.doctorName = doctorName;
		this.wardName = wardName;
		this.wardId = wardId;
		this.districtName = districtName;
		this.districtId = districtId;
		this.provinceName = provinceName;
		this.provinceId = provinceId;
	}
	
	// Creater constructor to map doctor on progress 3 screen
	public DoctorDTO(String doctorCode, String doctorName) {
		super();
		this.doctorCode = doctorCode;
		this.doctorName = doctorName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDoctorCode() {
		return doctorCode;
	}

	public void setDoctorCode(String doctorCode) {
		this.doctorCode = doctorCode;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getGenderStr() {
		return genderStr;
	}

	public void setGenderStr(String genderStr) {
		this.genderStr = genderStr;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMajors() {
		return majors;
	}

	public void setMajors(String majors) {
		this.majors = majors;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getDobStr() {
		return dobStr;
	}

	public void setDobStr(String dobStr) {
		this.dobStr = dobStr;
	}

	public BigInteger getSequenceCode() {
		return sequenceCode;
	}

	public void setSequenceCode(BigInteger sequenceCode) {
		this.sequenceCode = sequenceCode;
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

	public String getDowStr() {
		return dowStr;
	}

	public void setDowStr(String dowStr) {
		this.dowStr = dowStr;
	}

	public Date getDow() {
		return dow;
	}

	public void setDow(Date dow) {
		this.dow = dow;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public final String getBranchCode() {
		return branchCode;
	}

	public final void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getIsManagerStr() {
		return isManagerStr;
	}

	public void setIsManagerStr(String isManagerStr) {
		this.isManagerStr = isManagerStr;
	}

	public int getIsManager() {
		return isManager;
	}

	public void setIsManager(int isManager) {
		this.isManager = isManager;
	}

	public String getIsSubManagerStr() {
		return isSubManagerStr;
	}

	public void setIsSubManagerStr(String isSubManagerStr) {
		this.isSubManagerStr = isSubManagerStr;
	}

	public int getIsSubManager() {
		return isSubManager;
	}

	public void setIsSubManager(int isSubManager) {
		this.isSubManager = isSubManager;
	}

}
