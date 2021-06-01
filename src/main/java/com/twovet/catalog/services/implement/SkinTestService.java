package com.twovet.catalog.services.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twovet.base.common.ResultDto;
import com.twovet.catalog.dao.implement.SkinTestDao;
import com.twovet.catalog.dto.SkinTestDTO;
import com.twovet.catalog.model.SkinTest;
import com.twovet.catalog.services.ISkinTestService;

@Service
public class SkinTestService implements ISkinTestService {

	@Autowired
	SkinTestDao skinTestDao;

	@Override
	public Long insert(SkinTest skinTest) {
		return skinTestDao.insert(skinTest);
	}

	@Override
	public Long edit(SkinTest skinTest) {
		return skinTestDao.edit(skinTest);
	}

	@Override
	public SkinTestDTO getDetailSkinTest(String skinTestCode) {
		SkinTest skinTest = skinTestDao.getDetailSkinTest(skinTestCode);
		ModelMapper mm = new ModelMapper();
		SkinTestDTO skinTestDTO = mm.map(skinTest, SkinTestDTO.class);
		return skinTestDTO;
	}

	@Override
	public <T> ResultDto<T> searchAdvance(SkinTest skinTest, int pageNum, int maxResult) {
		List<SkinTestDTO> lst = skinTestDao.searchAdvance(skinTest, (pageNum - 1) * maxResult, maxResult);

		ResultDto<SkinTestDTO> result = new ResultDto<>();
		result.setDatas((List<SkinTestDTO>) lst);
		Long total = skinTestDao.getTotalSearchAd(skinTest);
		int lastPageNumber = (int) Math.ceil(total.doubleValue() / maxResult);
		result.setTotalRecord(total.intValue());
		result.setLastPage(lastPageNumber);

		return (ResultDto<T>) result;
	}

	@Override
	public List<SkinTestDTO> getAll() {
		List<SkinTest> skinTests = skinTestDao.getAll();
		ModelMapper mm = new ModelMapper();
		List<SkinTestDTO> result = skinTests.stream().map(skinTest -> mm.map(skinTest, SkinTestDTO.class))
				.collect(Collectors.toList());
		return result;
	}

}
