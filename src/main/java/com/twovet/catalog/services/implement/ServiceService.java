package com.twovet.catalog.services.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twovet.catalog.dao.implement.ServiceDao;
import com.twovet.catalog.dto.ServiceDTO;
import com.twovet.catalog.model.Services;
import com.twovet.catalog.services.IServiceService;

@Service
public class ServiceService implements IServiceService{
	
	@Autowired
	private ServiceDao serviceDao;

	@Override
	public List<ServiceDTO> getAllSevices(String branchCode) {
		List<ServiceDTO> lst = serviceDao.getAllServices(branchCode);
		return lst;
	}

	@Override
	public ServiceDTO getDetailService(String serviceCode) {
		ServiceDTO serviceDto = serviceDao.getDetailService(serviceCode);
		return serviceDto;
	}

	@Override
	public Long edit(Services service) {
		return serviceDao.edit(service);
	}

	@Override
	public Long insert(Services service) {
		return serviceDao.insert(service);
	}
}
