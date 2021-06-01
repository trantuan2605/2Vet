package com.twovet.catalog.dao;

import java.util.List;

import com.twovet.catalog.dto.CustomerDTO;
import com.twovet.catalog.model.Customer;

public interface ICustomerDao {

	List<Customer> listAllCustomer();
	List<Customer> listCustomer(int firstResult, int maxResult);
	Long getTotal();
	Long insert(Customer customer);
	Long edit(Customer customer);
	Customer getDetailCustomer(String cusCode);
	CustomerDTO getDetailCustomerExt(String cusCode);
	List<CustomerDTO> searchAdvance(Customer customer, int firstResult, int maxResult);
	Long getTotalSearchAd(Customer customer);
}
