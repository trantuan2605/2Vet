package com.twovet.catalog.dao;

import java.util.List;

import com.twovet.catalog.dto.ExamClinicalCommonDTO;
import com.twovet.catalog.dto.ExamClinicalDTO;
import com.twovet.catalog.model.ExamClinical;

public interface IExamClinicalDao {
	List<ExamClinical> getAll();
	List<ExamClinicalCommonDTO> getExamClinical(ExamClinical exam, int firstResult, int maxResult);
	public ExamClinical insert(ExamClinical exam);
	public Long edit(ExamClinical exam);
	ExamClinical getDetailExam(String examCode);
	Long updateProcessNum(ExamClinical exam);
	ExamClinicalDTO getExamViewDetail (String examCode);
}
