package com.twovet.catalog.services;

import java.util.List;

import com.twovet.base.common.ResultDto;
import com.twovet.catalog.dto.SuperSonicDTO;
import com.twovet.catalog.model.SuperSonic;

public interface ISuperSonicService {
	public Long insert(SuperSonic superSonic);

	public Long edit(SuperSonic superSonic);

	public SuperSonicDTO getDetailSuperSonic(String superSonicCode);

	public <T> ResultDto<T> searchAdvance(SuperSonic superSonic, int pageNum, int maxResult);

	public List<SuperSonicDTO> getAll();
}
