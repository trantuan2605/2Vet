package com.twovet.catalog.dto;

import java.math.BigInteger;
import java.util.Date;

public class ExamClinicalDTO {

	private Long id;
	private String examClinicalCode;
	private String petCode;
	private Date createTime;
	private String createTimeStr;
	private String serviceCodeList;
	private String bodyTemp;
	private String vacineCode;
	private String symptom;
	private String diagnose;
	private Date hospitalizeDate;
	private Date dischargeDate;
	private String hospitalizeDateStr;
	private String dischargeDateStr;
	private int deposit;
	private String branchCode;
	private String remark;
	private int isStatus;
	private String doctorCode;
	private String branchFirstCode;
	private BigInteger sequenceCode;
	private String symptomExtra;
	private String symptomCodeList;
	private int processNum;
	private int petAge;
	
	private String petName;
	private String cusCode;
	private String cusName;
	private String cusTypeName;
	private String address;
	private String phone;
	private String breedName;
	private String sexStr;
	private String color;
	private String sterilizedStr;
	private String vacineName;
	private String branchFirstName;
	private String branchName;
	private String doctorName;

	public ExamClinicalDTO() {
		super();
	}

	public ExamClinicalDTO(String examClinicalCode, String petCode, Date createTime, String createTimeStr,
			String serviceCodeList, String bodyTemp, String vacineCode, String symptom, String diagnose,
			Date hospitalizeDate, Date dischargeDate, String hospitalizeDateStr, String dischargeDateStr, int deposit,
			String branchCode, String remark, int isStatus, String doctorCode, String branchFirstCode, String symptomExtra, String symptomCodeList, int petAge,
			BigInteger sequenceCode) {
		super();
		this.examClinicalCode = examClinicalCode;
		this.petCode = petCode;
		this.createTime = createTime;
		this.createTimeStr = createTimeStr;
		this.serviceCodeList = serviceCodeList;
		this.bodyTemp = bodyTemp;
		this.vacineCode = vacineCode;
		this.symptom = symptom;
		this.diagnose = diagnose;
		this.hospitalizeDate = hospitalizeDate;
		this.dischargeDate = dischargeDate;
		this.hospitalizeDateStr = hospitalizeDateStr;
		this.dischargeDateStr = dischargeDateStr;
		this.deposit = deposit;
		this.branchCode = branchCode;
		this.remark = remark;
		this.isStatus = isStatus;
		this.doctorCode = doctorCode;
		this.branchFirstCode = branchFirstCode;
		this.symptomExtra = symptomExtra;
		this.symptomCodeList = symptomCodeList;
		this.petAge = petAge;
		this.sequenceCode = sequenceCode;
	}
	

	public ExamClinicalDTO(String petCode, int petAge, String petName,
			String cusCode, String cusName, String cusTypeName, String address, String phone, String breedName,
			String sexStr, String color, String sterilizedStr, String createTimeStr, String hospitalizeDateStr, String examClinicalCode, 
			String serviceCodeList, String bodyTemp,   String vacineName, String branchFirstName, String branchName, 
			String doctorName, Integer deposit,String symptomCodeList, String symptomExtra,   String diagnose, String symptom, String remark) {
		super();
		this.petCode = petCode;
		this.petAge = petAge;
		this.petName = petName;
		this.cusCode = cusCode;
		this.cusName = cusName;
		this.cusTypeName = cusTypeName;
		this.address = address;
		this.phone = phone;
		this.breedName = breedName;
		this.sexStr = sexStr;
		this.color = color;
		this.sterilizedStr = sterilizedStr;
		this.createTimeStr = createTimeStr;
		this.hospitalizeDateStr = hospitalizeDateStr;
		this.examClinicalCode = examClinicalCode;
		this.serviceCodeList = serviceCodeList;
		this.bodyTemp = bodyTemp;
		this.vacineName = vacineName;
		this.branchFirstName = branchFirstName;
		this.branchName = branchName;
		this.doctorName = doctorName;
		this.deposit = deposit;
		this.symptomCodeList = symptomCodeList;
		this.symptomExtra = symptomExtra;
		this.diagnose = diagnose;
		this.symptom = symptom;
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

	public String getHospitalizeDateStr() {
		return hospitalizeDateStr;
	}

	public void setHospitalizeDateStr(String hospitalizeDateStr) {
		this.hospitalizeDateStr = hospitalizeDateStr;
	}

	public String getDischargeDateStr() {
		return dischargeDateStr;
	}

	public void setDischargeDateStr(String dischargeDateStr) {
		this.dischargeDateStr = dischargeDateStr;
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

	public BigInteger getSequenceCode() {
		return sequenceCode;
	}

	public void setSequenceCode(BigInteger sequenceCode) {
		this.sequenceCode = sequenceCode;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
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

	public int getPetAge() {
		return petAge;
	}

	public void setPetAge(int petAge) {
		this.petAge = petAge;
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

	public String getCusTypeName() {
		return cusTypeName;
	}

	public void setCusTypeName(String cusTypeName) {
		this.cusTypeName = cusTypeName;
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

	public String getBreedName() {
		return breedName;
	}

	public void setBreedName(String breedName) {
		this.breedName = breedName;
	}

	public String getSexStr() {
		return sexStr;
	}

	public void setSexStr(String sexStr) {
		this.sexStr = sexStr;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSterilizedStr() {
		return sterilizedStr;
	}

	public void setSterilizedStr(String sterilizedStr) {
		this.sterilizedStr = sterilizedStr;
	}

	public String getVacineName() {
		return vacineName;
	}

	public void setVacineName(String vacineName) {
		this.vacineName = vacineName;
	}

	public String getBranchFirstName() {
		return branchFirstName;
	}

	public void setBranchFirstName(String branchFirstName) {
		this.branchFirstName = branchFirstName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	
	
}
