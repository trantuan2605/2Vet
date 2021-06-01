package com.twovet.catalog.services.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twovet.catalog.dao.implement.ProvinceDao;
import com.twovet.catalog.dto.ProvinceDTO;
import com.twovet.catalog.model.Province;
import com.twovet.catalog.services.IProvinceService;

@Service
public class ProvinceService implements IProvinceService {
	@Autowired
	ProvinceDao provinceDao;

	@Override
	public List<ProvinceDTO> getAll() {
		List<Province> provinces = provinceDao.getAll();
		ModelMapper mm = new ModelMapper();
		List<ProvinceDTO> result = provinces.stream().map(province -> mm.map(province, ProvinceDTO.class)).collect(Collectors.toList());
		return result;
	}

}
