package com.twovet.catalog.dto;

import java.math.BigInteger;
import java.util.Date;

public class ExamSubClinicalDTO {

	private Long id;
	private String examClinicalCode;
	private Date executionDate;
	private String executionDateStr;
	private Date createTime;
	private String createTimeStr;
	private String serviceCode;
	private String serviceName;
	private String path;
	private String branchCode;
	private String branchName;
	private String doctorCode;
	private String doctorName;
	private String remark;
	private String fileName;
	private String extItemCode;
	private int isStatus;
	private BigInteger sequenceCode;
	private int processNum;

	public ExamSubClinicalDTO() {
		super();
	}

	public ExamSubClinicalDTO(String examClinicalCode, Date executionDate, Date createTime, String createTimeStr,
			String serviceCode, String path, String branchCode, String doctorCode, String remark, int isStatus,
			BigInteger sequenceCode) {
		super();
		this.examClinicalCode = examClinicalCode;
		this.executionDate = executionDate;
		this.createTime = createTime;
		this.createTimeStr = createTimeStr;
		this.serviceCode = serviceCode;
		this.path = path;
		this.branchCode = branchCode;
		this.doctorCode = doctorCode;
		this.remark = remark;
		this.isStatus = isStatus;
		this.sequenceCode = sequenceCode;
	}
	
	public ExamSubClinicalDTO(Integer id, String examClinicalCode, String executionDateStr, String branchCode,
			String doctorCode, String remark, String fileName, String path, String serviceCode, String extItemCode) {
		super();
		this.id = Long.valueOf(id);
		this.examClinicalCode = examClinicalCode;
		this.executionDateStr = executionDateStr;
		this.branchCode = branchCode;
		this.doctorCode = doctorCode;
		this.remark = remark;
		this.fileName = fileName;
		this.path = path;
		this.serviceCode = serviceCode;
		this.extItemCode = extItemCode;
	}
	
	// constructor for get list date executed
	public ExamSubClinicalDTO (String examClinicalCode, String executionDateStr) {
		this.examClinicalCode = examClinicalCode;
		this.executionDateStr = executionDateStr;
	}
	
	// constructor for get show summary detail
	public ExamSubClinicalDTO(String examClinicalCode, String executionDateStr, String serviceName,
			String path, String branchName, String doctorName, String remark, String createTimeStr, String fileName, String extItemCode) {
		this.examClinicalCode = examClinicalCode;
		this.executionDateStr = executionDateStr;
		this.serviceName = serviceName;
		this.path = path;
		this.branchName = branchName;
		this.doctorName = doctorName;
		this.remark = remark;
		this.createTimeStr = createTimeStr;
		this.fileName = fileName;
		this.extItemCode = extItemCode;
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

	public Date getExecutionDate() {
		return executionDate;
	}

	public void setExecutionDate(Date executionDate) {
		this.executionDate = executionDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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

	public int getIsStatus() {
		return isStatus;
	}

	public void setIsStatus(int isStatus) {
		this.isStatus = isStatus;
	}

	public int getProcessNum() {
		return processNum;
	}

	public void setProcessNum(int processNum) {
		this.processNum = processNum;
	}
	
	public final String getExecutionDateStr() {
		return executionDateStr;
	}

	public final void setExecutionDateStr(String executionDateStr) {
		this.executionDateStr = executionDateStr;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getExtItemCode() {
		return extItemCode;
	}

	public void setExtItemCode(String extItemCode) {
		this.extItemCode = extItemCode;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
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
