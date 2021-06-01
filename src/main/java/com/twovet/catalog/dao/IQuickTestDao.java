package com.twovet.catalog.dao;

import java.util.List;

import com.twovet.catalog.dto.QuickTestDTO;
import com.twovet.catalog.model.QuickTest;

public interface IQuickTestDao {

	public List<QuickTest> getAllQuickTest();
	QuickTestDTO getDetailQuickTest(String quickCode);
	Long edit(QuickTest quickTest);
	Long insert(QuickTest quickTest);
}
