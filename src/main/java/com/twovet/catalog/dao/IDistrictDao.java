package com.twovet.catalog.dao;

import java.util.List;

import com.twovet.catalog.model.District;

public interface IDistrictDao {

	List<District> getLstDistrict(String provinceId);
}
