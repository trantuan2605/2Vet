package com.twovet.catalog.services;

import java.util.List;

import com.twovet.catalog.dto.QuickTestDTO;
import com.twovet.catalog.model.QuickTest;

public interface IQuickTestService {
	public List<QuickTestDTO> getAllQuickTest();
	public QuickTestDTO getDetaiQuickTest(String quickCode);
	public Long edit(QuickTest quickTest);
	public Long insert(QuickTest quickTest);
}
