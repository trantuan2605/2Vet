package com.twovet.catalog.dto;

import java.sql.Timestamp;
import java.util.Date;

public class RegistrationExaminationDTO {
	private Long id;
	private String cusCode;
	private String cusName;
	private String petCode;
	private String petName;
	private Date startTime;
	private Date endTime;
	private String branchCode;
	private String branchName;
	private String doctorCode;
	private String doctorName;
	private String symptomDescript;
	private String remark;
	private Boolean isDelete;
	private String title;
	private String phone;
	private String major;
	private String experience;
	private String genderPet;
	private boolean allDay;
	private String doctorAssign;
	private String doctorAssignName;

	public RegistrationExaminationDTO() {
	}

	public RegistrationExaminationDTO(Long id, String cusCode, String cusName, String petCode, String petName,
			Date startTime, Date endTime, String branchCode, String branchName, String doctorCode, String doctorName,
			String symptomDescript, String remark, Boolean isDelete) {
		super();
		this.id = id;
		this.cusCode = cusCode;
		this.cusName = cusName;
		this.petCode = petCode;
		this.petName = petName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.branchCode = branchCode;
		this.branchName = branchName;
		this.doctorCode = doctorCode;
		this.doctorName = doctorName;
		this.symptomDescript = symptomDescript;
		this.remark = remark;
		this.isDelete = isDelete;
	}
	
	public RegistrationExaminationDTO(Integer id, String cusCode, String cusName, String petCode, String petName,
			Timestamp startTime, Timestamp endTime, 
			String branchCode, String branchName, String doctorCode, String doctorName,
			String symptomDescript, String title, String remark, Integer allDay, String doctorAssign) {
		super();
		this.id = Long.valueOf(id);
		this.cusCode = cusCode;
		this.cusName = cusName;
		this.petCode = petCode;
		this.petName = petName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.branchCode = branchCode;
		this.branchName = branchName;
		this.doctorCode = doctorCode;
		this.doctorName = doctorName;
		this.symptomDescript = symptomDescript;
		this.title = title;
		this.remark = remark;
		this.allDay = allDay.intValue() >=1;
		this.doctorAssign = doctorAssign;
	}
	
	public RegistrationExaminationDTO(Integer id, String cusCode, String cusName, String petCode, String petName,
			Timestamp startTime, Timestamp endTime, 
			String branchCode, String branchName, String doctorCode, String doctorName,
			String symptomDescript, String title, String remark, String phone, String major, 
			String experience, String genderPet, String doctorAssign, String doctorAssignName) {
		super();
		this.id = Long.valueOf(id);
		this.cusCode = cusCode;
		this.cusName = cusName;
		this.petCode = petCode;
		this.petName = petName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.branchCode = branchCode;
		this.branchName = branchName;
		this.doctorCode = doctorCode;
		this.doctorName = doctorName;
		this.symptomDescript = symptomDescript;
		this.title = title;
		this.remark = remark;
		this.phone = phone;
		this.major = major;
		this.experience = experience;
		this.genderPet = genderPet;
		this.doctorAssign = doctorAssign;
		this.doctorAssignName = doctorAssignName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	public String getSymptomDescript() {
		return symptomDescript;
	}

	public void setSymptomDescript(String symptomDescript) {
		this.symptomDescript = symptomDescript;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getGenderPet() {
		return genderPet;
	}

	public void setGenderPet(String genderPet) {
		this.genderPet = genderPet;
	}

	public boolean isAllDay() {
		return allDay;
	}

	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}

	public String getDoctorAssign() {
		return doctorAssign;
	}

	public void setDoctorAssign(String doctorAssign) {
		this.doctorAssign = doctorAssign;
	}

	public final String getDoctorAssignName() {
		return doctorAssignName;
	}

	public final void setDoctorAssignName(String doctorAssignName) {
		this.doctorAssignName = doctorAssignName;
	}

}
