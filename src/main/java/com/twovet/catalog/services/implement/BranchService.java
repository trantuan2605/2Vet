package com.twovet.catalog.services.implement;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.twovet.base.common.FunctionCommon;
import com.twovet.base.common.ResultDto;
import com.twovet.catalog.dao.implement.BranchDao;
import com.twovet.catalog.dto.BranchDTO;
import com.twovet.catalog.dto.ServiceDTO;
import com.twovet.catalog.model.Branch;
import com.twovet.catalog.model.ServicesBranch;
import com.twovet.catalog.services.IBranchService;

@Service
public class BranchService implements IBranchService{
	
	@Autowired
	private BranchDao branchDao;

	@Override
	public Long insert(Branch branch, List<BranchDTO> lstDTO, HttpServletRequest request) {
		String branchCode = FunctionCommon.getCodeNextVal(lstDTO, "2VCN", "%03d");
		branch.setBranchCode(branchCode);
		MultipartFile multipartFile = branch.getFileImage();
		String path = FunctionCommon.uploadAndGetPath(multipartFile, request);
		branch.setPath(path);
		return branchDao.insert(branch);
	}

	@Override
	public BranchDTO getDetailBranch(String branchCode) {
		return branchDao.getDetailBranch(branchCode);
	}

	@Override
	public Long edit(Branch branch, HttpServletRequest request) {
		if (branch.getFileImage().getSize() > 0) {
			String path = FunctionCommon.uploadAndGetPath(branch.getFileImage(), request);
			branch.setPath(path);
		} else {
			branch.setPath(StringUtils.isNotBlank(branch.getPathHidden()) ? branch.getPathHidden() : null);
		}
		return branchDao.edit(branch);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> ResultDto<T> searchAdvance(Branch branch, int pageNum, int maxResult) {
		List<BranchDTO> lst = branchDao.searchAdvance(branch, (pageNum -1)*maxResult, maxResult);
		ResultDto<BranchDTO> result = new ResultDto<>();
		result.setDatas((List<BranchDTO>) lst);
		Long total = branchDao.getTotalSearchAd(branch);
		int lastPageNumber = (int) Math.ceil(total.doubleValue()/maxResult);
		result.setTotalRecord(total.intValue());
		result.setLastPage(lastPageNumber);
		return (ResultDto<T>) result;
	}

	@Override
	public List<ServiceDTO> getListBranchService(String branchCode) {
		return branchDao.getListBranchService(branchCode);
	}

	@Override
	public boolean saveServicesBranch(List<ServicesBranch> lstParam) {
		return branchDao.saveServicesBranch(lstParam);
	}

	@Override
	public List<BranchDTO> getAllBranchs() {
		List<Branch> branchs = branchDao.getAllBranchs();
		ModelMapper mm = new ModelMapper();
		List<BranchDTO> result = branchs.stream()
				.map(cus -> mm.map(cus, BranchDTO.class))
				.collect(Collectors.toList());
		return result;
	}

}
