package com.twovet.catalog.dao;

import java.util.List;

import com.twovet.catalog.dto.SpecDTO;
import com.twovet.catalog.model.Spec;

public interface ISpecDao {
	
	Long insert(Spec spec);

	public List<Spec> getAllSpec();

	List<SpecDTO> searchAdvance(Spec spec, int firstResult, int maxResult);

	Spec getDetailSpec(String specCode);

	Long edit(Spec spec);
	
	List<Spec> getLstSpecByCode(String breedCode);
}
