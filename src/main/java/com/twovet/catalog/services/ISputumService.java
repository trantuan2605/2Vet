package com.twovet.catalog.services;

import java.util.List;

import com.twovet.base.common.ResultDto;
import com.twovet.catalog.dto.SputumDTO;
import com.twovet.catalog.model.Sputum;

public interface ISputumService {
	public List<SputumDTO> getAllSputums();

	public <T> ResultDto<T> searchAdvance(Sputum sputum, int pageNum, int maxResult);

	public SputumDTO getDetailSputum(String sputumCode);

	public Long edit(Sputum sputum);

	public Long insert(Sputum sputum);
}
