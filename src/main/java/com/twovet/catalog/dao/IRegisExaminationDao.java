package com.twovet.catalog.dao;

import java.util.List;

import com.twovet.catalog.dto.RegistrationExaminationDTO;
import com.twovet.catalog.model.RegistrationExamination;

public interface IRegisExaminationDao {

	Long insert(RegistrationExamination registrationExamination);

	Long edit(RegistrationExamination registrationExamination);

	RegistrationExaminationDTO getDetailRegisExam(Long id);

	List<RegistrationExaminationDTO> searchAdvance(RegistrationExamination registrationExamination, int firstResult, int maxResult);
	
//	List<RegistrationExaminationDTO> getListStartEnd(RegistrationExamination registrationExamination);
}
