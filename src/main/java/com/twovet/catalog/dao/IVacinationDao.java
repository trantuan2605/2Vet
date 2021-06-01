package com.twovet.catalog.dao;

import java.util.List;

import com.twovet.catalog.dto.VacinationDTO;
import com.twovet.catalog.model.Vacination;

public interface IVacinationDao {

	public List<Vacination> getAllVacinations();
	VacinationDTO getDetailVacine(String vacineCode);
	Long edit(Vacination vacine);
	Long insert(Vacination vacine);
}
