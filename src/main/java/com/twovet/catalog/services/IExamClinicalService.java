package com.twovet.catalog.services;

import java.util.List;

import com.twovet.catalog.dto.ExamClinicalCommonDTO;
import com.twovet.catalog.dto.ExamClinicalDTO;
import com.twovet.catalog.model.ExamClinical;

public interface IExamClinicalService {
	public List<ExamClinicalDTO> getAll();

	public List<ExamClinicalCommonDTO> getExamClinical(ExamClinical exam, int pageNum, int maxResult);

	public ExamClinical insert(ExamClinical exam, List<ExamClinicalCommonDTO> lstDTO);
	
	public Long edit(ExamClinical exam);
	
	public ExamClinical getDetailExam(String examCode);
	
	public ExamClinicalDTO getViewDetailExam(String examCode);
}
