package com.twovet.catalog.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.twovet.base.common.ResultDto;
import com.twovet.catalog.dto.PetDTO;
import com.twovet.catalog.model.Pet;

public interface IPetService {
	public List<Pet> getAllPets();
	public <T> ResultDto<T> searchAdvance(Pet pet, int pageNum, int maxResult);
	public PetDTO getDetailPet(String petCode);
	public Long edit(Pet pet, HttpServletRequest request);
	public Long insert(Pet pet, List<PetDTO> lstPetDto, HttpServletRequest request);
	public List<PetDTO> getPetByCusCode (String cusCode);
	public List<PetDTO> getLstSequenceCode();
}
