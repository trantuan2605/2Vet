package com.twovet.catalog.dao;

import java.util.List;

import com.twovet.catalog.dto.ParasiticDTO;
import com.twovet.catalog.model.Parasitic;

public interface IParasiticTestDao {
	public List<ParasiticDTO> searchAdvance(Parasitic parasitic, int firstResult, int maxResult);

	Long insert(Parasitic parasitic);

	Long edit(Parasitic parasitic);

	Parasitic getDetailParasitic(String parasiticCode);

	Long getTotalSearchAd(Parasitic parasitic);

	List<Parasitic> getAll();

}
