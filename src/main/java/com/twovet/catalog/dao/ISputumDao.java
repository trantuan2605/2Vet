package com.twovet.catalog.dao;

import java.util.List;

import com.twovet.catalog.dto.SputumDTO;
import com.twovet.catalog.model.Sputum;

public interface ISputumDao {

	public List<Sputum> getAllSputums();

	List<SputumDTO> searchAdvance(Sputum sputum, int firstResult, int maxResult);

	Sputum getDetailSputum(String sputumCode);

	Long edit(Sputum sputum);

	Long insert(Sputum sputum);

	Long getTotalSearchAd(Sputum sputum);

}
