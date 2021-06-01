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

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;

@Entity
@Table(name = "exam_sub_clinical")
@NamedQueries({ @NamedQuery(name = "ExamSubClinical.findAll", query = "SELECT esc FROM ExamSubClinical esc"),
	 			@NamedQuery(name = "ExamSubClinical.findById", query = "SELECT esc FROM ExamSubClinical esc where 1=1 and esc.id = :id")})
public class ExamSubClinical implements Serializable {

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

	@Column(name = "execution_date")
	private Date executionDate;

	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "service_code")
	private String serviceCode;

	@Column(name = "path")
	private String path;

	@Column(name = "branch_code")
	private String branchCode;

	@Column(name = "doctor_code")
	private String doctorCode;

	@Column(name = "remark")
	private String remark;

	@Column(name = "is_status")
	private int isStatus;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "ext_item_code")
	private String extItemCode;
	
	@Column(name = "is_deleted")
	private int isDeleted;
	
	@Transient
	private String executionDateStr;
	
	@Transient
	private transient MultipartFile fileImage;
	
	/*
	 * @Transient private String extPro;
	 */
	@Transient
	private String pathHidden;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getIsStatus() {
		return isStatus;
	}

	public void setIsStatus(int isStatus) {
		this.isStatus = isStatus;
	}

	public MultipartFile getFileImage() {
		return fileImage;
	}

	public void setFileImage(MultipartFile fileImage) {
		this.fileImage = fileImage;
	}

	public String getPathHidden() {
		return pathHidden;
	}

	public void setPathHidden(String pathHidden) {
		this.pathHidden = pathHidden;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getExtItemCode() {
		return extItemCode;
	}

	public void setExtItemCode(String extItemCode) {
		this.extItemCode = extItemCode;
	}

	public String getExecutionDateStr() {
		return executionDateStr;
	}

	public void setExecutionDateStr(String executionDateStr) {
		this.executionDateStr = executionDateStr;
	}
	
}
