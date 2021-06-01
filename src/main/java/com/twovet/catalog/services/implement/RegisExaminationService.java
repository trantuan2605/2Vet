package com.twovet.catalog.services.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twovet.catalog.dao.implement.RegisExaminationDao;
import com.twovet.catalog.dto.RegistrationExaminationDTO;
import com.twovet.catalog.model.RegistrationExamination;
import com.twovet.catalog.services.IRegisExaminationService;

@Service
public class RegisExaminationService implements IRegisExaminationService {

	@Autowired
	RegisExaminationDao regisExamDao;

	@Override
	public Long insert(RegistrationExamination registrationExamination) {
		return regisExamDao.insert(registrationExamination);
	}

	@Override
	public Long edit(RegistrationExamination registrationExamination) {
		return regisExamDao.edit(registrationExamination);
	}

	@Override
	public RegistrationExaminationDTO getDetailRegisExam(Long id) {

		return regisExamDao.getDetailRegisExam(id);
	}

	@Override
	public List<RegistrationExaminationDTO> searchAdvance(RegistrationExamination registrationExamination, int firstResult, int maxResult) {
		return regisExamDao.searchAdvance(registrationExamination, firstResult, maxResult);
	}

}
