package com.twovet.catalog.services.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twovet.catalog.dao.implement.SymptomDao;
import com.twovet.catalog.dto.SymptomDTO;
import com.twovet.catalog.model.Symptom;
import com.twovet.catalog.services.ISymptomService;

@Service
public class SymptomService implements ISymptomService {

	@Autowired
	private SymptomDao symptomDao;
	
	@Override
	public List<SymptomDTO> listAllSymptom() {
		List<Symptom> lstSymp = symptomDao.getAll();
		ModelMapper mm = new ModelMapper();
		List<SymptomDTO> result = lstSymp.stream()
				.map(cus -> mm.map(cus, SymptomDTO.class))
				.collect(Collectors.toList());
		return result;
	}

}
