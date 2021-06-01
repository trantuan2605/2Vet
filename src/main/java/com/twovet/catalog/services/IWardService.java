package com.twovet.catalog.services;

import java.util.List;

import com.twovet.catalog.dto.WardDTO;

public interface IWardService {
	public List<WardDTO> getLstWard(String districtId);
}
