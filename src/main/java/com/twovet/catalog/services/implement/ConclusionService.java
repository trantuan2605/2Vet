package com.twovet.catalog.services.implement;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.twovet.base.common.BaseDao;
import com.twovet.base.common.FunctionCommon;
import com.twovet.catalog.dao.implement.ConclusionDao;
import com.twovet.catalog.dao.implement.ExamClinicalDao;
import com.twovet.catalog.dto.ConclusionDTO;
import com.twovet.catalog.dto.CustomerDTO;
import com.twovet.catalog.model.Conclusion;
import com.twovet.catalog.model.ExamClinical;
import com.twovet.catalog.services.IConclusionService;

@Service
public class ConclusionService implements IConclusionService {

	@Autowired
	ConclusionDao conclusionDao;
	
	@Autowired
	ExamClinicalDao examClinicalDao;

	@Override
	public Long insert(Conclusion conclusion, HttpServletRequest request) {
		
		//upload image and get path
		String pathDiagnose = "";
		String pathPrognosis  = "";
		String pathTreatments  = "";
		Long countInsert = 0L;
		if (conclusion.getFileImageDiagnose().getSize() > 0) {
			MultipartFile multipartFileDiagnose = conclusion.getFileImageDiagnose();
			pathDiagnose = FunctionCommon.uploadAndGetPath(multipartFileDiagnose, request);
			conclusion.setDiagnosePath(pathDiagnose);
		}
		
		if (conclusion.getFileImagePrognosis().getSize() > 0) {
			MultipartFile multipartFilePrognosis = conclusion.getFileImagePrognosis();
			pathPrognosis = FunctionCommon.uploadAndGetPath(multipartFilePrognosis, request);
			conclusion.setDiagnosePath(pathPrognosis);
		}
		
		if (conclusion.getFileImageTreatments().getSize() > 0) {
			MultipartFile multipartFileTreatments = conclusion.getFileImageTreatments();
			pathTreatments = FunctionCommon.uploadAndGetPath(multipartFileTreatments, request);
			conclusion.setTreatmentsPath(pathTreatments);
		}
		
		try {
			countInsert = conclusionDao.insert(conclusion);
			ExamClinical exam = new ExamClinical();
			exam.setExamClinicalCode(conclusion.getExamClinicalCode());
			examClinicalDao.updateProcessNum(exam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countInsert;
	}

	@Override
	public Long edit(Conclusion conclusion, HttpServletRequest request) {
		if(conclusion.getFileImageDiagnose().getSize()>0) {
			String pathDiagnose = FunctionCommon.uploadAndGetPath(conclusion.getFileImageDiagnose(), request);
			conclusion.setDiagnosePath(pathDiagnose);
		} else {
			conclusion.setDiagnosePath(StringUtils.isNotBlank(conclusion.getPathDiagnoseHidden()) ? conclusion.getPathDiagnoseHidden() : null);
		}
		
		if(conclusion.getFileImageTreatments().getSize()>0) {
			String pathTreatments = FunctionCommon.uploadAndGetPath(conclusion.getFileImageTreatments(), request);
			conclusion.setDiagnosePath(pathTreatments);
		} else {
			conclusion.setTreatmentsPath(StringUtils.isNotBlank(conclusion.getPathTreatmentsHidden()) ? conclusion.getPathTreatmentsHidden() : null);
		}
		
		if(conclusion.getFileImagePrognosis().getSize()>0) {
			String pathPrognosis = FunctionCommon.uploadAndGetPath(conclusion.getFileImagePrognosis(), request);
			conclusion.setDiagnosePath(pathPrognosis);
		} else {
			conclusion.setPrognosisPath(StringUtils.isNotBlank(conclusion.getPathPrognosisHidden()) ? conclusion.getPathPrognosisHidden() : null);
		}
		return conclusionDao.edit(conclusion);
	}

	@Override
	public ConclusionDTO getDetailConclusion(String examCode) {
		Conclusion conclusion = conclusionDao.getDetailConclusion(examCode);
		ModelMapper mm = new ModelMapper();
		ConclusionDTO conclusionDTO = mm.map(conclusion, ConclusionDTO.class);
		String reExamDateStr = FunctionCommon.getDate2String(conclusion.getReExamDate());
		String hospitalizeDateStr = FunctionCommon.getDate2String(conclusion.getHospitalizeDate());
		String dischargeDateStr = FunctionCommon.getDate2String(conclusion.getDischargeDate());
		conclusionDTO.setReExamDateStr(reExamDateStr);
		conclusionDTO.setHospitalizeDateStr(hospitalizeDateStr);
		conclusionDTO.setDischargeDateStr(dischargeDateStr);
		return conclusionDTO;
	}
}
