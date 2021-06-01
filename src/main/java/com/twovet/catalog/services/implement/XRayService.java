/**
 * 
 */
package com.twovet.catalog.services.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twovet.base.common.ResultDto;
import com.twovet.catalog.dao.implement.XRayDao;
import com.twovet.catalog.dto.XRayDTO;
import com.twovet.catalog.model.XRay;
import com.twovet.catalog.services.IXRayService;

/**
 * @author Tuantv
 *
 */
@Service
public class XRayService implements IXRayService {
	@Autowired
	XRayDao xRayDao;

	@Override
	public Long insert(XRay xRay) {
		return xRayDao.insert(xRay);
	}

	@Override
	public Long edit(XRay xRay) {
		return xRayDao.edit(xRay);
	}

	@Override
	public XRayDTO getDetailXRay(String xQCode) {
		XRay xRay = xRayDao.getDetailXRay(xQCode);
		ModelMapper mm = new ModelMapper();
		XRayDTO xRayDTO = mm.map(xRay, XRayDTO.class);
		return xRayDTO;
	}

	@Override
	public <T> ResultDto<T> searchAdvance(XRay xRay, int pageNum, int maxResult) {
		List<XRayDTO> lst = xRayDao.searchAdvance(xRay, (pageNum - 1) * maxResult, maxResult);

		ResultDto<XRayDTO> result = new ResultDto<>();
		result.setDatas((List<XRayDTO>) lst);
		Long total = xRayDao.getTotalSearchAd(xRay);
		int lastPageNumber = (int) Math.ceil(total.doubleValue() / maxResult);
		result.setTotalRecord(total.intValue());
		result.setLastPage(lastPageNumber);

		return (ResultDto<T>) result;
	}

	@Override
	public List<XRayDTO> getAll() {
		List<XRay> xRay = xRayDao.getAll();
		ModelMapper mm = new ModelMapper();
		List<XRayDTO> result = xRay.stream().map(superSonic -> mm.map(xRay, XRayDTO.class))
				.collect(Collectors.toList());
		return result;
	}

}
