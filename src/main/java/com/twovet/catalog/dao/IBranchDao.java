package com.twovet.catalog.dao;

import java.util.List;

import com.twovet.catalog.dto.BranchDTO;
import com.twovet.catalog.dto.ServiceDTO;
import com.twovet.catalog.model.Branch;
import com.twovet.catalog.model.ServicesBranch;

public interface IBranchDao {
	
	Long insert(Branch branch);
	
	Long edit(Branch doctor);
	
	BranchDTO getDetailBranch(String branchCode);
	
	List<BranchDTO> searchAdvance(Branch branch, int firstResult, int maxResult);
	
	Long getTotalSearchAd(Branch branch);
	
	List<ServiceDTO> getListBranchService(String branchCode);
	
	boolean saveServicesBranch(List<ServicesBranch> lstParam);
	
	List<Branch> getAllBranchs();
}
