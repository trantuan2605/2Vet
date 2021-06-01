/**
 * 
 */
package com.twovet.catalog.services.implement;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.twovet.base.common.FunctionCommon;
import com.twovet.base.common.ResultDto;
import com.twovet.catalog.dao.implement.BreedDao;
import com.twovet.catalog.dto.BreedDTO;
import com.twovet.catalog.model.Breed;
import com.twovet.catalog.services.IBreedService;

/**
 * @author Tuantv
 *
 */
@Service
public class BreedService implements IBreedService {

	@Autowired
	BreedDao breedDao;

	@Override
	public Long insert(Breed breed, List<BreedDTO> lstDTO, HttpServletRequest request) {
		String breedCode = FunctionCommon.getCodeNextVal(lstDTO, "2VL", "%03d");
		breed.setBreedCode(breedCode);
		MultipartFile multipartFile = breed.getFileImage();
		String path = FunctionCommon.uploadAndGetPath(multipartFile, request);
		breed.setPath(path);
		return breedDao.insert(breed);
	}

	@Override
	public Long edit(Breed breed, HttpServletRequest request) {
		if (breed.getFileImage().getSize() > 0) {
			String path = FunctionCommon.uploadAndGetPath(breed.getFileImage(), request);
			breed.setPath(path);
		} else {
			breed.setPath(StringUtils.isNotBlank(breed.getPathHidden()) ? breed.getPathHidden() : null);
		}
		return breedDao.edit(breed);

	}

	@Override
	public BreedDTO getDetailBreed(String breedCode) {
		Breed breed = breedDao.getDetailBreed(breedCode);
		ModelMapper mm = new ModelMapper();
		BreedDTO breedDto = mm.map(breed, BreedDTO.class);
		return breedDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> ResultDto<T> searchAdvance(Breed breed, int pageNum, int maxResult) {
		List<BreedDTO> lst = breedDao.searchAdvance(breed, (pageNum - 1) * maxResult, maxResult);

		ResultDto<BreedDTO> result = new ResultDto<>();
		result.setDatas((List<BreedDTO>) lst);
		Long total = breedDao.getTotalSearchAd(breed);
		int lastPageNumber = (int) Math.ceil(total.doubleValue() / maxResult);
		result.setTotalRecord(total.intValue());
		result.setLastPage(lastPageNumber);
		return (ResultDto<T>) result;
	}

	@Override
	public List<BreedDTO> getAll() {
		List<Breed> breeds = breedDao.getAll();
		ModelMapper mm = new ModelMapper();
		List<BreedDTO> result = breeds.stream().map(breed -> mm.map(breed, BreedDTO.class))
				.collect(Collectors.toList());
		return result;
	}

}
