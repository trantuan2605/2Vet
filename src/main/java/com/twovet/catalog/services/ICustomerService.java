package com.twovet.catalog.services;

import java.util.List;

import com.twovet.base.common.ResultDto;
import com.twovet.catalog.dto.CustomerDTO;
import com.twovet.catalog.dto.PetDTO;
import com.twovet.catalog.model.Customer;

public interface ICustomerService {
	public List<CustomerDTO> listAllCustomer();
	public <T> ResultDto<T> listCustomer(int pageNum, int maxResult);
	public Long insert(Customer customer,List<CustomerDTO> lstDTO, List<PetDTO> lstPetDTO);
	public Long edit(Customer customer, List<CustomerDTO> lstDTO, List<PetDTO> lstPetDTO);
	public CustomerDTO getDetailCustomer(String cusCode);
	public <T> ResultDto<T> searchAdvance(Customer customer, int pageNum, int maxResult);
}
