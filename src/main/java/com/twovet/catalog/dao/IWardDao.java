package com.twovet.catalog.dao;

import java.util.List;

import com.twovet.catalog.model.Ward;

public interface IWardDao {

	List<Ward> getLstWard(String districtId);
}
