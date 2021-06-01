package com.twovet.catalog.dto;

import java.math.BigInteger;
import java.util.Date;

public class ExamClinicalCommonDTO {
	private Long id;
	private int sex;
	private String petCode;
	private String petName;
	private String cusCode;
	private String cusName;
	private String phone;
	private String address;
	private String createdTime;
	private BigInteger sequenceCode;
	private int processNum;
	private String processStr;
	private String examCode;
	private String hospitalizeDate;
	private int petAge;
	private String createTime;

	public ExamClinicalCommonDTO() {
	}

	public ExamClinicalCommonDTO(Integer id, Integer sex, String petCode, String petName, String cusCode, String cusName,
			String phone, String address, Integer processNum, String processStr,BigInteger sequenceCode, String examCode, String hospitalizeDate, Integer petAge, String createTime) {
		super();
		this.id = id.longValue();
		this.sex = sex;
		this.petCode = petCode;
		this.petName = petName;
		this.cusCode = cusCode;
		this.cusName = cusName;
		this.phone = phone;
		this.address = address;
		this.processNum = processNum;
		this.processStr = processStr;
		this.sequenceCode = sequenceCode;
		this.examCode = examCode;
		this.hospitalizeDate = hospitalizeDate;
		this.petAge = petAge;
		this.createTime = createTime;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getPetCode() {
		return petCode;
	}

	public void setPetCode(String petCode) {
		this.petCode = petCode;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getCusCode() {
		return cusCode;
	}

	public void setCusCode(String cusCode) {
		this.cusCode = cusCode;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public int getProcessNum() {
		return processNum;
	}

	public void setProcessNum(int processNum) {
		this.processNum = processNum;
	}

	public String getProcessStr() {
		return processStr;
	}

	public void setProcessStr(String processStr) {
		this.processStr = processStr;
	}
	

	public BigInteger getSequenceCode() {
		return sequenceCode;
	}

	public void setSequenceCode(BigInteger sequenceCode) {
		this.sequenceCode = sequenceCode;
	}

	public String getExamCode() {
		return examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public String getHospitalizeDate() {
		return hospitalizeDate;
	}

	public void setHospitalizeDate(String hospitalizeDate) {
		this.hospitalizeDate = hospitalizeDate;
	}

	public int getPetAge() {
		return petAge;
	}

	public void setPetAge(int petAge) {
		this.petAge = petAge;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	

}
