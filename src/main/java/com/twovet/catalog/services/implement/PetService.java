package com.twovet.catalog.services.implement;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.twovet.base.common.FunctionCommon;
import com.twovet.base.common.ResultDto;
import com.twovet.catalog.dao.implement.PetDao;
import com.twovet.catalog.dto.PetDTO;
import com.twovet.catalog.model.Pet;
import com.twovet.catalog.services.IPetService;

@Service
public class PetService implements IPetService{
	
	@Autowired
	private PetDao petDao;

	@Override
	public List<Pet> getAllPets() {
		// TODO Auto-generated method stub
		return petDao.getAllPets();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> ResultDto<T> searchAdvance(Pet pet, int pageNum, int maxResult) {
		List<PetDTO> lst = petDao.searchAdvance(pet, (pageNum -1)*maxResult, maxResult);
		ResultDto<PetDTO> result = new ResultDto<>();
		result.setDatas((List<PetDTO>) lst);
		Long total = petDao.getTotalSearchAd(pet);
		int lastPageNumber = (int) Math.ceil(total.doubleValue()/maxResult);
		result.setTotalRecord(total.intValue());
		result.setLastPage(lastPageNumber);
		return (ResultDto<T>) result;
	}

	@Override
	public PetDTO getDetailPet(String petCode) {
		PetDTO petDto = petDao.getDetailPet(petCode);
		return petDto;
	}

	@Override
	public Long edit(Pet pet, HttpServletRequest request) {
		MultipartFile multipartFile = pet.getFileImage();
		if (multipartFile.getSize() > 0) {
			String path = FunctionCommon.uploadAndGetPath(multipartFile, request);
			pet.setPath(path);
		}
		return petDao.edit(pet);
	}

	@Override
	public Long insert(Pet pet, List<PetDTO> lstPetDto, HttpServletRequest request) {
		String petCode = FunctionCommon.getCodeNextVal(lstPetDto, "2VT", "%09d");
		pet.setPetCode(petCode);
		
		//upload image and get path
		if (pet.getFileImage().getSize() > 0) {
			String path = FunctionCommon.uploadAndGetPath(pet.getFileImage(), request);
			pet.setPath(path);
		}
		return petDao.insert(pet);
	}

	@Override
	public List<PetDTO> getPetByCusCode(String cusCode) {
		return petDao.getLstDetailPet(cusCode);
	}

	@Override
	public List<PetDTO> getLstSequenceCode() {
		// TODO Auto-generated method stub
		return petDao.getLstSequenceCode();
	}
}
