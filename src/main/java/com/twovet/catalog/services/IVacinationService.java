package com.twovet.catalog.services;

import java.util.List;

import com.twovet.catalog.dto.VacinationDTO;
import com.twovet.catalog.model.Vacination;

public interface IVacinationService {
	public List<VacinationDTO> getAllVacinations();
	public VacinationDTO getDetaiVacinaton(String vacineCode);
	public Long edit(Vacination vacine);
	public Long insert(Vacination vacine);
}
