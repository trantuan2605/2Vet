package com.twovet.catalog.dto;

import java.math.BigInteger;
import java.util.Date;

public class ConclusionDTO {

	private Long id;
	private String examClinicalCode;
	private String diagnose;
	private String treatments;
	private String prognosis;
	private String diagnosePath;
	private String treatmentsPath;
	private String prognosisPath;
	private String branchCode;
	private String doctorCode;
	private String remark;
	private String reExamDateStr;
	private int isStatus;
	private BigInteger sequenceCode;
	private int processNum;
	private String hospitalizeDateStr;
	private String dischargeDateStr;

	public ConclusionDTO() {
		super();
	}

	public ConclusionDTO(String examClinicalCode, String diagnose, String treatments, String prognosis,
			String diagnosePath, String treatmentsPath, String prognosisPath, String branchCode, String doctorCode,
			String remark, int isStatus, BigInteger sequenceCode, String hospitalizeDateStr, String dischargeDateStr) {
		super();
		this.examClinicalCode = examClinicalCode;
		this.diagnose = diagnose;
		this.treatments = treatments;
		this.prognosis = prognosis;
		this.diagnosePath = diagnosePath;
		this.treatmentsPath = treatmentsPath;
		this.prognosisPath = prognosisPath;
		this.branchCode = branchCode;
		this.doctorCode = doctorCode;
		this.remark = remark;
		this.isStatus = isStatus;
		this.sequenceCode = sequenceCode;
		this.hospitalizeDateStr = hospitalizeDateStr;
		this.dischargeDateStr = dischargeDateStr;
	}

	public ConclusionDTO(String examClinicalCode, String diagnose, String treatments, String prognosis,
			String diagnosePath, String treatmentsPath, String prognosisPath, String branchCode, String doctorCode,
			String remark, String reExamDateStr, int isStatus, BigInteger sequenceCode, int processNum,
			String hospitalizeDateStr, String dischargeDateStr) {
		super();
		this.examClinicalCode = examClinicalCode;
		this.diagnose = diagnose;
		this.treatments = treatments;
		this.prognosis = prognosis;
		this.diagnosePath = diagnosePath;
		this.treatmentsPath = treatmentsPath;
		this.prognosisPath = prognosisPath;
		this.branchCode = branchCode;
		this.doctorCode = doctorCode;
		this.remark = remark;
		this.reExamDateStr = reExamDateStr;
		this.isStatus = isStatus;
		this.sequenceCode = sequenceCode;
		this.processNum = processNum;
		this.hospitalizeDateStr = hospitalizeDateStr;
		this.dischargeDateStr = dischargeDateStr;
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

	public int getIsStatus() {
		return isStatus;
	}

	public void setIsStatus(int isStatus) {
		this.isStatus = isStatus;
	}

	public BigInteger getSequenceCode() {
		return sequenceCode;
	}

	public void setSequenceCode(BigInteger sequenceCode) {
		this.sequenceCode = sequenceCode;
	}

	public int getProcessNum() {
		return processNum;
	}

	public void setProcessNum(int processNum) {
		this.processNum = processNum;
	}

	public String getReExamDateStr() {
		return reExamDateStr;
	}

	public void setReExamDateStr(String reExamDateStr) {
		this.reExamDateStr = reExamDateStr;
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

}
