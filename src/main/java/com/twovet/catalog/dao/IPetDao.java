package com.twovet.catalog.dao;

import java.util.List;

import com.twovet.catalog.dto.PetDTO;
import com.twovet.catalog.model.Pet;

public interface IPetDao {

	public List<Pet> getAllPets();
	List<PetDTO> searchAdvance(Pet customer, int firstResult, int maxResult);
	PetDTO getDetailPet(String petCode);
	Long edit(Pet pet);
	Long insert(Pet pet);
	List<PetDTO> getLstDetailPet(String cusCode);
	List<PetDTO> getLstSequenceCode();
}
