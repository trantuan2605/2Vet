package com.twovet.catalog.dto;

import org.springframework.web.multipart.MultipartFile;

public class ExamSubClinicalRequestDTO {
	
	private String lstSubClinicalStr;
	
	private MultipartFile[] fileImage;
	
	private String lstSubClinDelStr;
	
	private String examClinicalCode;

	public final String getLstSubClinicalStr() {
		return lstSubClinicalStr;
	}

	public final void setLstSubClinicalStr(String lstSubClinicalStr) {
		this.lstSubClinicalStr = lstSubClinicalStr;
	}

	public final MultipartFile[] getFileImage() {
		return fileImage;
	}

	public final void setFileImage(MultipartFile[] fileImage) {
		this.fileImage = fileImage;
	}

	public String getLstSubClinDelStr() {
		return lstSubClinDelStr;
	}

	public void setLstSubClinDelStr(String lstSubClinDelStr) {
		this.lstSubClinDelStr = lstSubClinDelStr;
	}

	public String getExamClinicalCode() {
		return examClinicalCode;
	}

	public void setExamClinicalCode(String examClinicalCode) {
		this.examClinicalCode = examClinicalCode;
	}
	
}
