package com.twovet.catalog.services;

import java.util.List;

import com.twovet.base.common.ResultDto;
import com.twovet.catalog.dto.StoolDTO;
import com.twovet.catalog.model.Stool;

public interface IStoolService {
	public List<StoolDTO> getAllStools();

	public <T> ResultDto<T> searchAdvance(Stool stool, int pageNum, int maxResult);

	public StoolDTO getDetailStool(String stoolCode);

	public Long edit(Stool stool);

	public Long insert(Stool stool);
}
