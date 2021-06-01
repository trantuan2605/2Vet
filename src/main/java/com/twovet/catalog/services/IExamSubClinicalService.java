package com.twovet.catalog.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.twovet.catalog.dto.ExamSubClinicalDTO;
import com.twovet.catalog.model.ExamSubClinical;

public interface IExamSubClinicalService {

	public Long insert(List<ExamSubClinical> examSub,  HttpServletRequest request);
	
	public Long update(List<ExamSubClinical> examSub, List<ExamSubClinical> examSubDel, HttpServletRequest request);
	
	public Long edit(ExamSubClinical examSub, HttpServletRequest request);
	
	public List<ExamSubClinicalDTO> getListProgress3Dto(String examClinicalCode);
	
	public ExamSubClinical getDetailSub(Long id);
	
	public List<ExamSubClinicalDTO> getListExecuteDateByCode (ExamSubClinical examSub, int firstResult, int maxResult);
	
	public List<ExamSubClinicalDTO> getListSeviceByCode (ExamSubClinical examSub, int firstResult, int maxResult);
}
