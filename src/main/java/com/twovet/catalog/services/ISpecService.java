package com.twovet.catalog.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.twovet.base.common.ResultDto;
import com.twovet.catalog.dto.SpecDTO;
import com.twovet.catalog.model.Spec;

public interface ISpecService {
	public List<Spec> getAllSpecs();

	public <T> ResultDto<T> searchAdvance(Spec spec, int pageNum, int maxResult);

	public SpecDTO getDetailSpec(String specCode);

	public Long edit(Spec spec, HttpServletRequest request);

	public Long insert(Spec spec, List<SpecDTO> lstDTO, HttpServletRequest request);

	List<SpecDTO> getLstSpecByCode(String breedCode);
}
