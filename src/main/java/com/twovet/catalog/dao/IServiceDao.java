package com.twovet.catalog.dao;

import java.util.List;

import com.twovet.catalog.dto.ServiceDTO;
import com.twovet.catalog.model.Services;

public interface IServiceDao {

	public List<ServiceDTO> getAllServices(String branchCode);
	ServiceDTO getDetailService(String serviceCode);
	Long edit(Services vacine);
	Long insert(Services vacine);
}
