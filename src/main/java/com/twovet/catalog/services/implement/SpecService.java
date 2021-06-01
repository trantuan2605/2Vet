package com.twovet.catalog.services.implement;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.twovet.base.bean.TwoVetProperties;
import com.twovet.base.common.FunctionCommon;
import com.twovet.base.common.ResultDto;
import com.twovet.catalog.dao.implement.SpecDao;
import com.twovet.catalog.dto.SpecDTO;
import com.twovet.catalog.model.Spec;
import com.twovet.catalog.services.ISpecService;

@Service
public class SpecService implements ISpecService {
	@Autowired
	SpecDao specDao;
	
	@Autowired
	private TwoVetProperties prop;

	@Override
	public List<Spec> getAllSpecs() {
		return specDao.getAllSpec();
	}

	@Override
	public <T> ResultDto<T> searchAdvance(Spec spec, int pageNum, int maxResult) {
		List<SpecDTO> lst = specDao.searchAdvance(spec, (pageNum - 1) * maxResult, maxResult);
		ResultDto<SpecDTO> result = new ResultDto<>();
		result.setDatas((List<SpecDTO>) lst);
		Long total = specDao.getTotalSearchAd(spec);
		int lastPageNumber = (int) Math.ceil(total.doubleValue() / maxResult);
		result.setTotalRecord(total.intValue());
		result.setLastPage(lastPageNumber);
		return (ResultDto<T>) result;
	}

	@Override
	public SpecDTO getDetailSpec(String specCode) {
		Spec spec = specDao.getDetailSpec(specCode);
		ModelMapper mm = new ModelMapper();
		SpecDTO specDto = mm.map(spec, SpecDTO.class);
		if (spec.getBreed() != null) {
			specDto.setBreedCode(spec.getBreed().getBreedCode());
			specDto.setBreedName(spec.getBreed().getBreedName());
		}
		return specDto;
	}

	@Override
	public Long edit(Spec spec, HttpServletRequest request) {
		if (spec.getFileImage().getSize() > 0) {
			String path = FunctionCommon.uploadAndGetPath(spec.getFileImage(), request);
			spec.setPath(path);
		} else {
			spec.setPath(StringUtils.isNotBlank(spec.getPathHidden()) ? spec.getPathHidden() : null);
		}
		return specDao.edit(spec);
	}

	@Override
	public Long insert(Spec spec, List<SpecDTO> lstDTO, HttpServletRequest request) {
		String specCode = FunctionCommon.getCodeNextVal(lstDTO, "2VG", "%04d");
		spec.setSpecCode(specCode);
		MultipartFile multipartFile = spec.getFileImage();
		String path = FunctionCommon.uploadAndGetPath(multipartFile, request);
		spec.setPath(path);
		return specDao.insert(spec);
	}

	@Override
	public List<SpecDTO> getLstSpecByCode(String breedCode) {
		List<Spec> specs = specDao.getLstSpecByCode(breedCode);
		ModelMapper mm = new ModelMapper();
		List<SpecDTO> result = specs.stream()
		.map(breed -> mm.map(breed, SpecDTO.class))
		.collect(Collectors.toList());
		return result;
	}

}
