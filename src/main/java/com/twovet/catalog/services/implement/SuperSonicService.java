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
import com.twovet.catalog.dao.implement.SuperSonicDao;
import com.twovet.catalog.dto.SuperSonicDTO;
import com.twovet.catalog.model.SuperSonic;
import com.twovet.catalog.services.ISuperSonicService;

/**
 * @author Tuantv
 *
 */
@Service
public class SuperSonicService implements ISuperSonicService {
	@Autowired
	SuperSonicDao superSonicDao;

	@Override
	public Long insert(SuperSonic superSonic) {
		return superSonicDao.insert(superSonic);
	}

	@Override
	public Long edit(SuperSonic superSonic) {
		return superSonicDao.edit(superSonic);
	}

	@Override
	public SuperSonicDTO getDetailSuperSonic(String superSonicCode) {
		SuperSonic superSonic = superSonicDao.getDetailSuperSonic(superSonicCode);
		ModelMapper mm = new ModelMapper();
		SuperSonicDTO superSonicDTO = mm.map(superSonic, SuperSonicDTO.class);
		return superSonicDTO;
	}

	@Override
	public <T> ResultDto<T> searchAdvance(SuperSonic superSonic, int pageNum, int maxResult) {
		List<SuperSonicDTO> lst = superSonicDao.searchAdvance(superSonic, (pageNum - 1) * maxResult, maxResult);

		ResultDto<SuperSonicDTO> result = new ResultDto<>();
		result.setDatas((List<SuperSonicDTO>) lst);
		Long total = superSonicDao.getTotalSearchAd(superSonic);
		int lastPageNumber = (int) Math.ceil(total.doubleValue() / maxResult);
		result.setTotalRecord(total.intValue());
		result.setLastPage(lastPageNumber);

		return (ResultDto<T>) result;
	}

	@Override
	public List<SuperSonicDTO> getAll() {
		List<SuperSonic> superSonics = superSonicDao.getAll();
		ModelMapper mm = new ModelMapper();
		List<SuperSonicDTO> result = superSonics.stream().map(superSonic -> mm.map(superSonic, SuperSonicDTO.class))
				.collect(Collectors.toList());
		return result;
	}

}
