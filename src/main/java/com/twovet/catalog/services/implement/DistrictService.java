package com.twovet.catalog.services.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twovet.catalog.dao.implement.DistrictDao;
import com.twovet.catalog.dto.DistrictDTO;
import com.twovet.catalog.model.District;
import com.twovet.catalog.services.IDistrictService;

@Service
public class DistrictService implements IDistrictService {
	@Autowired
	DistrictDao districtDao;

	@Override
	public List<DistrictDTO> getLstDistrict(String provinceId) {
		List<District> districts = districtDao.getLstDistrict(provinceId);
		ModelMapper mm = new ModelMapper();
		List<DistrictDTO> result = districts.stream().map(district -> mm.map(district, DistrictDTO.class)).collect(Collectors.toList());
		return result;
	}

}
