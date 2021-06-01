package com.twovet.catalog.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.twovet.base.common.ResultDto;
import com.twovet.catalog.dto.BranchDTO;
import com.twovet.catalog.dto.ServiceDTO;
import com.twovet.catalog.model.Branch;
import com.twovet.catalog.model.ServicesBranch;

public interface IBranchService {
	public Long insert(Branch branch, List<BranchDTO> lstDTO, HttpServletRequest request);
	
	public Long edit(Branch branch, HttpServletRequest request);
	
	public BranchDTO getDetailBranch(String branchCode);
	
	public <T> ResultDto<T> searchAdvance(Branch branch, int pageNum, int maxResult);
	
	public List<ServiceDTO> getListBranchService(String branchCode);
	
	public boolean saveServicesBranch(List<ServicesBranch> lstParam);
	
	public List<BranchDTO> getAllBranchs();
}
