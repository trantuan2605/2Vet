package com.twovet.catalog.services.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twovet.catalog.dao.implement.QuickTestDao;
import com.twovet.catalog.dto.QuickTestDTO;
import com.twovet.catalog.model.QuickTest;
import com.twovet.catalog.services.IQuickTestService;

@Service
public class QuickTestService implements IQuickTestService{
	
	@Autowired
	private QuickTestDao quickDao;

	@Override
	public List<QuickTestDTO> getAllQuickTest() {
		List<QuickTest> lst = quickDao.getAllQuickTest();
		ModelMapper mm = new ModelMapper();
		List<QuickTestDTO> result = lst.stream()
				.map(quick -> mm.map(quick, QuickTestDTO.class))
				.collect(Collectors.toList());
		return result;
	}

	@Override
	public QuickTestDTO getDetaiQuickTest(String quickCode) {
		QuickTestDTO quickDto = quickDao.getDetailQuickTest(quickCode);
		return quickDto;
	}

	@Override
	public Long edit(QuickTest quick) {
		return quickDao.edit(quick);
	}

	@Override
	public Long insert(QuickTest quick) {
		return quickDao.insert(quick);
	}
}
