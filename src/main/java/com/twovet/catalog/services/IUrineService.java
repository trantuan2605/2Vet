package com.twovet.catalog.services;

import java.util.List;

import com.twovet.base.common.ResultDto;
import com.twovet.catalog.dto.UrineDTO;
import com.twovet.catalog.model.Urine;

public interface IUrineService {
	public List<UrineDTO> getAllUrines();

	public <T> ResultDto<T> searchAdvance(Urine urine, int pageNum, int maxResult);

	public UrineDTO getDetailUrine(String urineCode);

	public Long edit(Urine urine);

	public Long insert(Urine urine);
}
