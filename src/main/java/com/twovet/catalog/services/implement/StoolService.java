package com.twovet.catalog.services.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twovet.base.common.ResultDto;
import com.twovet.catalog.dao.implement.StoolDao;
import com.twovet.catalog.dto.StoolDTO;
import com.twovet.catalog.model.Stool;
import com.twovet.catalog.services.IStoolService;

@Service
public class StoolService implements IStoolService {

	@Autowired
	private StoolDao stoolDao;

	@Override
	public List<StoolDTO> getAllStools() {
		List<Stool> stools = stoolDao.getAllStools();
		ModelMapper mm = new ModelMapper();
		List<StoolDTO> result = stools.stream().map(stool -> mm.map(stool, StoolDTO.class))
				.collect(Collectors.toList());
		return result;
	}

	@Override
	public <T> ResultDto<T> searchAdvance(Stool stool, int pageNum, int maxResult) {
		List<StoolDTO> lst = stoolDao.searchAdvance(stool, (pageNum - 1) * maxResult, maxResult);

		ResultDto<StoolDTO> result = new ResultDto<>();
		result.setDatas((List<StoolDTO>) lst);
		Long total = stoolDao.getTotalSearchAd(stool);
		int lastPageNumber = (int) Math.ceil(total.doubleValue() / maxResult);
		result.setTotalRecord(total.intValue());
		result.setLastPage(lastPageNumber);

		return (ResultDto<T>) result;
	}

	@Override
	public StoolDTO getDetailStool(String stoolCode) {
		Stool stool = stoolDao.getDetailStool(stoolCode);
		ModelMapper mm = new ModelMapper();
		StoolDTO stoolDTO = mm.map(stool, StoolDTO.class);
		return stoolDTO;
	}

	@Override
	public Long edit(Stool stool) {
		return stoolDao.edit(stool);
	}

	@Override
	public Long insert(Stool stool) {
		return stoolDao.insert(stool);
	}

}
