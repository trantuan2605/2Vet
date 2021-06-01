package com.twovet.catalog.services;

import java.util.List;

import com.twovet.base.common.ResultDto;
import com.twovet.catalog.dto.SkinTestDTO;
import com.twovet.catalog.model.SkinTest;

public interface ISkinTestService {
	public Long insert(SkinTest skinTest);

	public Long edit(SkinTest skinTest);

	public SkinTestDTO getDetailSkinTest(String skinTestCode);

	public <T> ResultDto<T> searchAdvance(SkinTest skinTest, int pageNum, int maxResult);

	public List<SkinTestDTO> getAll();
}
