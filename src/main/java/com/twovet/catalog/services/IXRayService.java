package com.twovet.catalog.services;

import java.util.List;

import com.twovet.base.common.ResultDto;
import com.twovet.catalog.dto.XRayDTO;
import com.twovet.catalog.model.XRay;

public interface IXRayService {
	public Long insert(XRay xRay);

	public Long edit(XRay xRay);

	public XRayDTO getDetailXRay(String xQCode);

	public <T> ResultDto<T> searchAdvance(XRay xRay, int pageNum, int maxResult);

	public List<XRayDTO> getAll();
}
