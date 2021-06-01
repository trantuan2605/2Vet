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

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "conclusion")
@NamedQueries({ 
				@NamedQuery(name = "Conclusion.findAll", query = "SELECT c FROM Conclusion c"),
				@NamedQuery(name = "Conclusion.findByCode", query = "SELECT c FROM Conclusion c WHERE 1=1 and c.examClinicalCode = :examClinicalCode")
			})
public class Conclusion implements Serializable {

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

	@Column(name = "diagnose")
	private String diagnose;

	@Column(name = "treatments")
	private String treatments;

	@Column(name = "prognosis")
	private String prognosis;

	@Column(name = "diagnose_path")
	private String diagnosePath;

	@Column(name = "treatments_path")
	private String treatmentsPath;

	@Column(name = "prognosis_path")
	private String prognosisPath;

	@Column(name = "branch_code")
	private String branchCode;

	@Column(name = "doctor_code")
	private String doctorCode;

	@Column(name = "remark")
	private String remark;

	@Column(name = "is_status")
	private int isStatus;
	
	@Column(name = "process_num")
	private int processNum;
	
	@Column(name = "re_exam_date")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date reExamDate;
	
	@Column(name = "hospitalize_date")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date hospitalizeDate;

	@Column(name = "discharge_date")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dischargeDate;


	@Transient
	private MultipartFile fileImageDiagnose;
	@Transient
	private String pathDiagnoseHidden;
	@Transient
	private MultipartFile fileImageTreatments;
	@Transient
	private String pathTreatmentsHidden;
	@Transient
	private MultipartFile fileImagePrognosis;
	@Transient
	private String pathPrognosisHidden;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExamClinicalCode() {
		return examClinicalCode;
	}

	public String getDiagnose() {
		return diagnose;
	}

	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}

	public String getTreatments() {
		return treatments;
	}

	public void setTreatments(String treatments) {
		this.treatments = treatments;
	}

	public String getPrognosis() {
		return prognosis;
	}

	public void setPrognosis(String prognosis) {
		this.prognosis = prognosis;
	}

	public String getDiagnosePath() {
		return diagnosePath;
	}

	public void setDiagnosePath(String diagnosePath) {
		this.diagnosePath = diagnosePath;
	}

	public String getTreatmentsPath() {
		return treatmentsPath;
	}

	public void setTreatmentsPath(String treatmentsPath) {
		this.treatmentsPath = treatmentsPath;
	}

	public String getPrognosisPath() {
		return prognosisPath;
	}

	public void setPrognosisPath(String prognosisPath) {
		this.prognosisPath = prognosisPath;
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

	public void setExamClinicalCode(String examClinicalCode) {
		this.examClinicalCode = examClinicalCode;
	}

	public int getIsStatus() {
		return isStatus;
	}

	public void setIsStatus(int isStatus) {
		this.isStatus = isStatus;
	}

	public MultipartFile getFileImageDiagnose() {
		return fileImageDiagnose;
	}

	public void setFileImageDiagnose(MultipartFile fileImageDiagnose) {
		this.fileImageDiagnose = fileImageDiagnose;
	}

	public String getPathDiagnoseHidden() {
		return pathDiagnoseHidden;
	}

	public void setPathDiagnoseHidden(String pathDiagnoseHidden) {
		this.pathDiagnoseHidden = pathDiagnoseHidden;
	}

	public MultipartFile getFileImageTreatments() {
		return fileImageTreatments;
	}

	public void setFileImageTreatments(MultipartFile fileImageTreatments) {
		this.fileImageTreatments = fileImageTreatments;
	}

	public String getPathTreatmentsHidden() {
		return pathTreatmentsHidden;
	}

	public void setPathTreatmentsHidden(String pathTreatmentsHidden) {
		this.pathTreatmentsHidden = pathTreatmentsHidden;
	}

	public MultipartFile getFileImagePrognosis() {
		return fileImagePrognosis;
	}

	public void setFileImagePrognosis(MultipartFile fileImagePrognosis) {
		this.fileImagePrognosis = fileImagePrognosis;
	}

	public String getPathPrognosisHidden() {
		return pathPrognosisHidden;
	}

	public void setPathPrognosisHidden(String pathPrognosisHidden) {
		this.pathPrognosisHidden = pathPrognosisHidden;
	}

	public int getProcessNum() {
		return processNum;
	}

	public void setProcessNum(int processNum) {
		this.processNum = processNum;
	}

	public Date getReExamDate() {
		return reExamDate;
	}

	public void setReExamDate(Date reExamDate) {
		this.reExamDate = reExamDate;
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
	
}
