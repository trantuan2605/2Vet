package com.twovet.catalog.services.implement;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twovet.base.common.BaseDao;
import com.twovet.base.common.FunctionCommon;
import com.twovet.catalog.dao.implement.ExamClinicalDao;
import com.twovet.catalog.dto.ExamClinicalCommonDTO;
import com.twovet.catalog.dto.ExamClinicalDTO;
import com.twovet.catalog.model.ExamClinical;
import com.twovet.catalog.services.IExamClinicalService;

@Service
public class ExamClinicalService extends BaseDao implements IExamClinicalService {
	public ExamClinicalService(EntityManagerFactory emf) {
		super(emf);
	}

	@Autowired
	ExamClinicalDao examclinicalDao;

	@Override
	public List<ExamClinicalDTO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public  List<ExamClinicalCommonDTO> getExamClinical(ExamClinical exam, int firstResult, int maxResult) {
		return examclinicalDao.getExamClinical(exam, firstResult, maxResult);
	}

	@Override
	public ExamClinical insert(ExamClinical exam, List<ExamClinicalCommonDTO> lstDTO) {
		String examCode = FunctionCommon.getCodeNextVal(lstDTO, "2PK", "%09d");
		exam.setExamClinicalCode(StringUtils.isNoneBlank(examCode) ? examCode: "2PK000000001");
		exam.setProcessNum(1);
		exam.setCreateTime(new Date());
		return examclinicalDao.insert(exam);
	}

	@Override
	public Long edit(ExamClinical exam) {
		exam.setProcessNum(2);
		return examclinicalDao.edit(exam);
	}

	@Override
	public ExamClinical getDetailExam(String examCode) {
		return examclinicalDao.getDetailExam(examCode);
	}

	@Override
	public ExamClinicalDTO getViewDetailExam(String examCode) {
		return examclinicalDao.getExamViewDetail(examCode);
	}
}
