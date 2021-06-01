package com.twovet.catalog.dao;

import java.util.List;

import com.twovet.catalog.dto.StoolDTO;
import com.twovet.catalog.model.Stool;

public interface IStoolDao {

	public List<Stool> getAllStools();

	List<StoolDTO> searchAdvance(Stool stool, int firstResult, int maxResult);

	Stool getDetailStool(String stoolCode);

	Long edit(Stool stool);

	Long insert(Stool stool);
	
	Long getTotalSearchAd(Stool stool);

}
