package com.twovet.catalog.services;

import java.util.List;

import com.twovet.catalog.dto.RegistrationExaminationDTO;
import com.twovet.catalog.model.RegistrationExamination;

public interface IRegisExaminationService {
	public Long insert(RegistrationExamination registrationExamination);

	public Long edit(RegistrationExamination registrationExamination);

	RegistrationExaminationDTO getDetailRegisExam(Long id);

	List<RegistrationExaminationDTO> searchAdvance(RegistrationExamination registrationExamination, int firstResult, int maxResult);

}
