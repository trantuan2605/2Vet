package com.twovet.catalog.dao;

import java.util.List;

import com.twovet.catalog.dto.XRayDTO;
import com.twovet.catalog.model.XRay;

public interface IXRayDao {
	public List<XRayDTO> searchAdvance(XRay xRay, int firstResult, int maxResult);

	Long insert(XRay xRay);

	Long edit(XRay xRay);

	XRay getDetailXRay(String xQCode);

	Long getTotalSearchAd(XRay xRay);

	List<XRay> getAll();

}
