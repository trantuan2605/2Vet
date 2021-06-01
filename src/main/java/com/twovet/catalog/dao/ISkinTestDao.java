package com.twovet.catalog.dao;

import java.util.List;

import com.twovet.catalog.dto.SkinTestDTO;
import com.twovet.catalog.model.SkinTest;

public interface ISkinTestDao {
	public List<SkinTestDTO> searchAdvance(SkinTest skinTest, int firstResult, int maxResult);

	Long insert(SkinTest skinTest);

	Long edit(SkinTest skinTest);

	SkinTest getDetailSkinTest(String skinTestCode);

	Long getTotalSearchAd(SkinTest skinTest);

	List<SkinTest> getAll();

}
