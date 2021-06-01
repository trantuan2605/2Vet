package com.twovet.catalog.dao;

import java.util.List;

import com.twovet.catalog.dto.UrineDTO;
import com.twovet.catalog.model.Urine;

public interface IUrineDao {

	public List<Urine> getAllUrines();

	List<UrineDTO> searchAdvance(Urine urine, int firstResult, int maxResult);

	Urine getDetailUrine(String urineCode);

	Long edit(Urine urine);

	Long insert(Urine urine);
	
	Long getTotalSearchAd(Urine urine);

}
