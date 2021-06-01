package com.twovet.catalog.dao;

import java.util.List;

import com.twovet.catalog.dto.SuperSonicDTO;
import com.twovet.catalog.model.SuperSonic;

public interface ISuperSonicDao {
	public List<SuperSonicDTO> searchAdvance(SuperSonic superSonic, int firstResult, int maxResult);

	Long insert(SuperSonic superSonic);

	Long edit(SuperSonic superSonic);

	SuperSonic getDetailSuperSonic(String superSonicCode);

	Long getTotalSearchAd(SuperSonic superSonic);
	
	List<SuperSonic> getAll();

}
