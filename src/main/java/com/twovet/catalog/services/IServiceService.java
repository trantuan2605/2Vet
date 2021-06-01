package com.twovet.catalog.services;

import java.util.List;

import com.twovet.catalog.dto.ServiceDTO;
import com.twovet.catalog.model.Services;

public interface IServiceService {
	public List<ServiceDTO> getAllSevices(String branchCode);
	public ServiceDTO getDetailService(String serviceCode);
	public Long edit(Services service);
	public Long insert(Services service);
}
