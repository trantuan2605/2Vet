package com.twovet.catalog.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "registration_examination")
@NamedQueries({
//	@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c LEFT JOIN Pet p ON p.isDeleted != 1 WHERE 1=1 ORDER by c.id desc"),
		@NamedQuery(name = "RegistrationExamination.findAll", query = "SELECT c FROM RegistrationExamination c WHERE 1=1 ORDER by c.id desc"),
		@NamedQuery(name = "RegistrationExamination.findByPetCode", query = "SELECT c FROM RegistrationExamination c WHERE 1=1 and c.petCode = :cusCode order by c.id desc"),
		@NamedQuery(name = "RegistrationExamination.count", query = "SELECT count(c.id) FROM RegistrationExamination c WHERE 1=1") })
public class RegistrationExamination implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "pet_code")
	private String petCode;

	@Column(name = "start_time")
	private Date startTime;

	@Column(name = "end_time")
	private Date endTime;

	@Column(name = "address")
	private String address;

	@Column(name = "branch_code")
	private String branchCode;

	@Column(name = "doctor_code")
	private String doctorCode;

	@Column(name = "symptom_descript")
	private String symptomDescript;

	@Column(name = "remark")
	private String remark;
	
	@Column(name = "all_day")
	private int allDay;

	@Column(name = "status", columnDefinition = "int  default 0")
	private int status;
	
	@Column(name = "doctor_assign")
	private String doctorAssign;
	
	@Transient
	private String startTimeStr;

	@Transient
	private String endTimeStr;
	
	@Transient
	private String editType;
	
	@Transient
	private boolean searchByWaiting;
	
	@Transient
	private boolean searchByAssign;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPetCode() {
		return petCode;
	}

	public void setPetCode(String petCode) {
		this.petCode = petCode;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getDoctorCode() {
		return doctorCode;
	}

	public void setDoctorCode(String doctorCode) {
		this.doctorCode = doctorCode;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public final String getStartTimeStr() {
		return startTimeStr;
	}

	public final void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public String getEditType() {
		return editType;
	}

	public void setEditType(String editType) {
		this.editType = editType;
	}

	public int getAllDay() {
		return allDay;
	}

	public void setAllDay(int allDay) {
		this.allDay = allDay;
	}

	public String getDoctorAssign() {
		return doctorAssign;
	}

	public void setDoctorAssign(String doctorAssign) {
		this.doctorAssign = doctorAssign;
	}

	public boolean isSearchByWaiting() {
		return searchByWaiting;
	}

	public void setSearchByWaiting(boolean searchByWaiting) {
		this.searchByWaiting = searchByWaiting;
	}

	public boolean isSearchByAssign() {
		return searchByAssign;
	}

	public void setSearchByAssign(boolean searchByAssign) {
		this.searchByAssign = searchByAssign;
	}

}
