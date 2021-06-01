package com.twovet.catalog.services;

import java.util.List;

import com.twovet.base.common.ResultDto;
import com.twovet.catalog.dto.ParasiticDTO;
import com.twovet.catalog.model.Parasitic;

public interface IParasiticTestService {
	public Long insert(Parasitic parasitic);

	public Long edit(Parasitic parasitic);

	public ParasiticDTO getDetailParasiticTest(String parasiticTestCode);

	public <T> ResultDto<T> searchAdvance(Parasitic parasitic, int pageNum, int maxResult);

	public List<ParasiticDTO> getAll();
}
