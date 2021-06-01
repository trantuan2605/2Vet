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

@Entity
@Table(name = "exam_clinical")

@NamedQueries({ @NamedQuery(name = "ExamClinical.findAll", query = "SELECT ec FROM ExamClinical ec"),
		@NamedQuery(name = "ExamClinical.findByCode", query = "SELECT ec FROM ExamClinical ec WHERE 1=1 and ec.examClinicalCode = :examClinicalCode ORDER BY ec.id desc") })
public class ExamClinical implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "exam_clinical_code")
	private String examClinicalCode;

	@Column(name = "pet_code")
	private String petCode;

	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "service_code_list")
	private String serviceCodeList;

	@Column(name = "body_temp")
	private String bodyTemp;

	@Column(name = "vacine_code")
	private String vacineCode;

	@Column(name = "symptom")
	private String symptom;

	@Column(name = "diagnose")
	private String diagnose;

	@Column(name = "hospitalize_date")
	private Date hospitalizeDate;

	@Column(name = "discharge_date")
	private Date dischargeDate;

	@Column(name = "deposit")
	private int deposit;

	@Column(name = "branch_code")
	private String branchCode;

	@Column(name = "remark")
	private String remark;

	@Column(name = "is_status")
	private int isStatus;

	@Column(name = "doctor_code")
	private String doctorCode;

	@Column(name = "branch_first_code")
	private String branchFirstCode;

	@Column(name = "process_num")
	private int processNum;

	@Column(name = "symptom_extra")
	private String symptomExtra;

	@Column(name = "symptom_code_list")
	private String symptomCodeList;
	
	@Column(name = "pet_age")
	private int petAge;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExamClinicalCode() {
		return examClinicalCode;
	}

	public void setExamClinicalCode(String examClinicalCode) {
		this.examClinicalCode = examClinicalCode;
	}

	public String getPetCode() {
		return petCode;
	}

	public void setPetCode(String petCode) {
		this.petCode = petCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getServiceCodeList() {
		return serviceCodeList;
	}

	public void setServiceCodeList(String serviceCodeList) {
		this.serviceCodeList = serviceCodeList;
	}

	public String getBodyTemp() {
		return bodyTemp;
	}

	public void setBodyTemp(String bodyTemp) {
		this.bodyTemp = bodyTemp;
	}

	public String getVacineCode() {
		return vacineCode;
	}

	public void setVacineCode(String vacineCode) {
		this.vacineCode = vacineCode;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public String getDiagnose() {
		return diagnose;
	}

	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}

	public Date getHospitalizeDate() {
		return hospitalizeDate;
	}

	public void setHospitalizeDate(Date hospitalizeDate) {
		this.hospitalizeDate = hospitalizeDate;
	}

	public Date getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getIsStatus() {
		return isStatus;
	}

	public void setIsStatus(int isStatus) {
		this.isStatus = isStatus;
	}

	public String getDoctorCode() {
		return doctorCode;
	}

	public void setDoctorCode(String doctorCode) {
		this.doctorCode = doctorCode;
	}

	public String getBranchFirstCode() {
		return branchFirstCode;
	}

	public void setBranchFirstCode(String branchFirstCode) {
		this.branchFirstCode = branchFirstCode;
	}

	public int getProcessNum() {
		return processNum;
	}

	public void setProcessNum(int processNum) {
		this.processNum = processNum;
	}

	public String getSymptomExtra() {
		return symptomExtra;
	}

	public void setSymptomExtra(String symptomExtra) {
		this.symptomExtra = symptomExtra;
	}

	public String getSymptomCodeList() {
		return symptomCodeList;
	}

	public void setSymptomCodeList(String symptomCodeList) {
		this.symptomCodeList = symptomCodeList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getPetAge() {
		return petAge;
	}

	public void setPetAge(int petAge) {
		this.petAge = petAge;
	}

}
