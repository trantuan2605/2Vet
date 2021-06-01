package com.twovet.catalog.dao;

import java.util.List;

import com.twovet.catalog.dto.BreedDTO;
import com.twovet.catalog.model.Breed;

public interface IBreedDao {
	public List<BreedDTO> searchAdvance(Breed breed, int firstResult, int maxResult);

	Long insert(Breed breed);

	Long edit(Breed breed);

	Breed getDetailBreed(String breedCode);

	Long getTotalSearchAd(Breed breed);
	
	List<Breed> getAll();

}
