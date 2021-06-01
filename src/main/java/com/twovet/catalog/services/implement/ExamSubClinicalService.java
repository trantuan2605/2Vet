package com.twovet.catalog.services.implement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.twovet.base.common.BaseDao;
import com.twovet.base.common.FunctionCommon;
import com.twovet.catalog.dao.implement.ExamSubClinicalDao;
import com.twovet.catalog.dto.ExamSubClinicalDTO;
import com.twovet.catalog.model.ExamSubClinical;
import com.twovet.catalog.services.IExamSubClinicalService;

@Service
public class ExamSubClinicalService extends BaseDao implements IExamSubClinicalService {
	public ExamSubClinicalService(EntityManagerFactory emf) {
		super(emf);
	}

	@Autowired
	ExamSubClinicalDao examSubClinicalDao;

	@Override
	public Long insert(List<ExamSubClinical> examSubs, HttpServletRequest request) {
		for (ExamSubClinical examSubClinical : examSubs) {
			MultipartFile multipartFile = examSubClinical.getFileImage();
			String path = FunctionCommon.uploadAndGetPath(multipartFile, request);
			examSubClinical.setPath(path);
			examSubClinical.setCreateTime(new Date());
			examSubClinical.setFileName(multipartFile.getOriginalFilename());
		}
		
		return examSubClinicalDao.insert(examSubs);
	}

	@Override
	public Long edit(ExamSubClinical examSub, HttpServletRequest request) {
		if (examSub.getFileImage().getSize() > 0) {
			String path = FunctionCommon.uploadAndGetPath(examSub.getFileImage(), request);
			examSub.setPath(path);
		} else {
			examSub.setPath(StringUtils.isNotBlank(examSub.getPathHidden()) ? examSub.getPathHidden() : null);
		}
		return examSubClinicalDao.edit(examSub);
	}

	@Override
	public List<ExamSubClinicalDTO> getListProgress3Dto(String examClinicalCode) {
		// TODO Auto-generated method stub
		return examSubClinicalDao.getListProgress3Dto(examClinicalCode);
	}

	@Override
	public Long update(List<ExamSubClinical> examSubs, List<ExamSubClinical> examSubDel, HttpServletRequest request) {
		for (ExamSubClinical examSubClinical : examSubs) {
//			if (examSubClinical.getId() == null) {
				MultipartFile multipartFile = examSubClinical.getFileImage();
				if (multipartFile.getSize() > 0) {
					String path = FunctionCommon.uploadAndGetPath(multipartFile, request);
					examSubClinical.setPath(path);
					examSubClinical.setFileName(multipartFile.getOriginalFilename());
				} else {
					examSubClinical.setPath(StringUtils.isNotBlank(examSubClinical.getPathHidden()) ? examSubClinical.getPathHidden() : null);
				}
				examSubClinical.setCreateTime(new Date());
			}
//		}
		return examSubClinicalDao.save(examSubs, examSubDel);
	}

	@Override
	public ExamSubClinical getDetailSub(Long id) {
		return examSubClinicalDao.getDetail(id);
	}

	@Override
	public List<ExamSubClinicalDTO> getListExecuteDateByCode(ExamSubClinical examSub, int firstResult, int maxResult) {
		return examSubClinicalDao.getListExecuteDateByCode(examSub, firstResult, maxResult);
	}

	@Override
	public List<ExamSubClinicalDTO> getListSeviceByCode(ExamSubClinical examSub, int firstResult, int maxResult) {
		return examSubClinicalDao.getListSeviceByCode(examSub, firstResult, maxResult);
	}

	
}
