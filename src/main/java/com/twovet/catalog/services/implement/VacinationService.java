package com.twovet.catalog.services.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twovet.catalog.dao.implement.VacinationDao;
import com.twovet.catalog.dto.VacinationDTO;
import com.twovet.catalog.model.Vacination;
import com.twovet.catalog.services.IVacinationService;

@Service
public class VacinationService implements IVacinationService{
	
	@Autowired
	private VacinationDao vacineDao;

	@Override
	public List<VacinationDTO> getAllVacinations() {
		List<Vacination> lst = vacineDao.getAllVacinations();
		ModelMapper mm = new ModelMapper();
		List<VacinationDTO> result = lst.stream()
				.map(vacine -> mm.map(vacine, VacinationDTO.class))
				.collect(Collectors.toList());
		return result;
	}

	@Override
	public VacinationDTO getDetaiVacinaton(String vacineCode) {
		VacinationDTO vacineDto = vacineDao.getDetailVacine(vacineCode);
		return vacineDto;
	}

	@Override
	public Long edit(Vacination vacine) {
		return vacineDao.edit(vacine);
	}

	@Override
	public Long insert(Vacination vacine) {
		return vacineDao.insert(vacine);
	}
}
