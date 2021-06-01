package com.twovet.catalog.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.twovet.base.common.ResultDto;
import com.twovet.catalog.dto.BreedDTO;
import com.twovet.catalog.model.Breed;

public interface IBreedService {
	public Long insert(Breed breed, List<BreedDTO> lstDTO, HttpServletRequest request);

	public Long edit(Breed breed, HttpServletRequest request);

	public BreedDTO getDetailBreed(String breedCode);

	public <T> ResultDto<T> searchAdvance(Breed breed, int pageNum, int maxResult);

	public List<BreedDTO> getAll();
}
