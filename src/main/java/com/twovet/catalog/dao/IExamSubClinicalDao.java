package com.twovet.catalog.dao;

import java.util.List;

import com.twovet.catalog.dto.ExamSubClinicalDTO;
import com.twovet.catalog.model.ExamSubClinical;

public interface IExamSubClinicalDao {
	public Long insert(List<ExamSubClinical> examSub);
	
	public Long save(List<ExamSubClinical> examSub, List<ExamSubClinical> examSubDel);

	public Long edit(ExamSubClinical examSub);
	
	public List<ExamSubClinicalDTO> getListProgress3Dto(String examClinicalCode);
	
	public ExamSubClinical getDetail(Long id);
	
	public List<ExamSubClinicalDTO> getListExecuteDateByCode (ExamSubClinical examSub, int firstResult, int maxResult);
	
	public List<ExamSubClinicalDTO> getListSeviceByCode (ExamSubClinical examSub, int firstResult, int maxResult);
}
