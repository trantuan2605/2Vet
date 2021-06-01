package com.twovet.catalog.services.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twovet.catalog.dao.implement.WardDao;
import com.twovet.catalog.dto.WardDTO;
import com.twovet.catalog.model.Ward;
import com.twovet.catalog.services.IWardService;

@Service
public class WardService implements IWardService {
	@Autowired
	WardDao wardDao;

	@Override
	public List<WardDTO> getLstWard(String districtId) {
		List<Ward> wards = wardDao.getLstWard(districtId);
		ModelMapper mm = new ModelMapper();
		List<WardDTO> result = wards.stream().map(ward -> mm.map(ward, WardDTO.class)).collect(Collectors.toList());
		return result;
	}

}
