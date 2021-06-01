package com.twovet.catalog.services;

import java.util.List;

import com.twovet.catalog.dto.DistrictDTO;

public interface IDistrictService {
	public List<DistrictDTO> getLstDistrict(String provinceId);
}
