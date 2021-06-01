package com.twovet.catalog.services.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twovet.base.common.ResultDto;
import com.twovet.catalog.dao.implement.ParasiticTestDao;
import com.twovet.catalog.dto.ParasiticDTO;
import com.twovet.catalog.model.Parasitic;
import com.twovet.catalog.services.IParasiticTestService;

@Service
public class ParasiticTestService implements IParasiticTestService {

	@Autowired
	ParasiticTestDao parasiticTestDao;

	@Override
	public Long insert(Parasitic parasitic) {
		return parasiticTestDao.insert(parasitic);
	}

	@Override
	public Long edit(Parasitic parasitic) {
		return parasiticTestDao.edit(parasitic);
	}

	@Override
	public ParasiticDTO getDetailParasiticTest(String parasiticTestCode) {
		Parasitic parasitic = parasiticTestDao.getDetailParasitic(parasiticTestCode);
		ModelMapper mm = new ModelMapper();
		ParasiticDTO parasiticDTO = mm.map(parasitic, ParasiticDTO.class);
		return parasiticDTO;
	}

	@Override
	public <T> ResultDto<T> searchAdvance(Parasitic parasitic, int pageNum, int maxResult) {
		List<ParasiticDTO> lst = parasiticTestDao.searchAdvance(parasitic, (pageNum - 1) * maxResult, maxResult);

		ResultDto<ParasiticDTO> result = new ResultDto<>();
		result.setDatas((List<ParasiticDTO>) lst);
		Long total = parasiticTestDao.getTotalSearchAd(parasitic);
		int lastPageNumber = (int) Math.ceil(total.doubleValue() / maxResult);
		result.setTotalRecord(total.intValue());
		result.setLastPage(lastPageNumber);

		return (ResultDto<T>) result;
	}

	@Override
	public List<ParasiticDTO> getAll() {
		List<Parasitic> parasitics = parasiticTestDao.getAll();
		ModelMapper mm = new ModelMapper();
		List<ParasiticDTO> result = parasitics.stream().map(parasitic -> mm.map(parasitic, ParasiticDTO.class))
				.collect(Collectors.toList());
		return result;
	}

}
