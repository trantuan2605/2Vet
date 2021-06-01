package com.twovet.catalog.services.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twovet.base.common.ResultDto;
import com.twovet.catalog.dao.implement.UrineDao;
import com.twovet.catalog.dto.UrineDTO;
import com.twovet.catalog.model.Urine;
import com.twovet.catalog.services.IUrineService;

@Service
public class UrineService implements IUrineService {

	@Autowired
	private UrineDao urineDao;

	@Override
	public List<UrineDTO> getAllUrines() {
		List<Urine> urines = urineDao.getAllUrines();
		ModelMapper mm = new ModelMapper();
		List<UrineDTO> result = urines.stream().map(urine -> mm.map(urine, UrineDTO.class))
				.collect(Collectors.toList());
		return result;
	}

	@Override
	public <T> ResultDto<T> searchAdvance(Urine urine, int pageNum, int maxResult) {
		List<UrineDTO> lst = urineDao.searchAdvance(urine, (pageNum - 1) * maxResult, maxResult);

		ResultDto<UrineDTO> result = new ResultDto<>();
		result.setDatas((List<UrineDTO>) lst);
		Long total = urineDao.getTotalSearchAd(urine);
		int lastPageNumber = (int) Math.ceil(total.doubleValue() / maxResult);
		result.setTotalRecord(total.intValue());
		result.setLastPage(lastPageNumber);

		return (ResultDto<T>) result;
	}

	@Override
	public UrineDTO getDetailUrine(String urineCode) {
		Urine urine = urineDao.getDetailUrine(urineCode);
		ModelMapper mm = new ModelMapper();
		UrineDTO urineDTO = mm.map(urine, UrineDTO.class);
		return urineDTO;
	}

	@Override
	public Long edit(Urine urine) {
		return urineDao.edit(urine);
	}

	@Override
	public Long insert(Urine urine) {
		return urineDao.insert(urine);
	}

}
