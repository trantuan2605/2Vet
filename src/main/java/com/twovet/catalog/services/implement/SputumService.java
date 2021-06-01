package com.twovet.catalog.services.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twovet.base.common.ResultDto;
import com.twovet.catalog.dao.implement.SputumDao;
import com.twovet.catalog.dto.SputumDTO;
import com.twovet.catalog.model.Sputum;
import com.twovet.catalog.services.ISputumService;

@Service
public class SputumService implements ISputumService {

	@Autowired
	private SputumDao sputumDao;

	@Override
	public List<SputumDTO> getAllSputums() {
		List<Sputum> sputums = sputumDao.getAllSputums();
		ModelMapper mm = new ModelMapper();
		List<SputumDTO> result = sputums.stream().map(sputum -> mm.map(sputum, SputumDTO.class)).collect(Collectors.toList());
		return result;
	}

	@Override
	public <T> ResultDto<T> searchAdvance(Sputum sputum, int pageNum, int maxResult) {
		List<SputumDTO> lst = sputumDao.searchAdvance(sputum, (pageNum - 1) * maxResult, maxResult);

		ResultDto<SputumDTO> result = new ResultDto<>();
		result.setDatas((List<SputumDTO>) lst);
		Long total = sputumDao.getTotalSearchAd(sputum);
		int lastPageNumber = (int) Math.ceil(total.doubleValue() / maxResult);
		result.setTotalRecord(total.intValue());
		result.setLastPage(lastPageNumber);

		return (ResultDto<T>) result;
	}

	@Override
	public SputumDTO getDetailSputum(String sputumCode) {
		Sputum sputum = sputumDao.getDetailSputum(sputumCode);
		ModelMapper mm = new ModelMapper();
		SputumDTO sputumDTO = mm.map(sputum, SputumDTO.class);
		return sputumDTO;
	}

	@Override
	public Long edit(Sputum sputum) {
		return sputumDao.edit(sputum);
	}

	@Override
	public Long insert(Sputum sputum) {
		return sputumDao.insert(sputum);
	}

}
