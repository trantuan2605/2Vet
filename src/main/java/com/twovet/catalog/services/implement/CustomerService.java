package com.twovet.catalog.services.implement;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twovet.base.common.FunctionCommon;
import com.twovet.base.common.ResultDto;
import com.twovet.catalog.dao.implement.CustomerDao;
import com.twovet.catalog.dao.implement.PetDao;
import com.twovet.catalog.dto.CustomerDTO;
import com.twovet.catalog.dto.PetDTO;
import com.twovet.catalog.model.Customer;
import com.twovet.catalog.model.CustomerType;
import com.twovet.catalog.model.Pet;
import com.twovet.catalog.services.ICustomerService;

@Service
public class CustomerService implements ICustomerService{
	
	@Autowired
	private CustomerDao customerDao;
	@Autowired 
	private PetDao petDao;

	@Override
	public List<CustomerDTO> listAllCustomer() {
		List<Customer> lstCustomer = customerDao.listAllCustomer();
		ModelMapper mm = new ModelMapper();
		List<CustomerDTO> result = lstCustomer.stream()
				.map(cus -> mm.map(cus, CustomerDTO.class))
				.collect(Collectors.toList());
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> ResultDto<T> listCustomer(int pageNum, int maxResult) {
		List<Customer> lst = customerDao.listCustomer((pageNum -1)*maxResult, maxResult);
		ModelMapper modelMapper = new ModelMapper();
//		List<CustomerDTO> customersDto = lst.stream()
//				.map(cus -> modelMapper.map(cus, CustomerDTO.class))
//				.collect(Collectors.toList());
		List<CustomerType> types = new FunctionCommon().intialCustomerTypes();
		List<CustomerDTO> customersDto = lst.stream()
				.map(cus -> {
					CustomerDTO dto = modelMapper.map(cus, CustomerDTO.class);
					CustomerType typeObj = types.stream()
					.filter(x -> x.getCode() == dto.getCusType())
//					.map(CustomerType::getName )
					.findAny().orElse(null);
					if (typeObj != null && dto.getCusType() == typeObj.getCode()) {
						dto.setCusTypeName(typeObj.getName());
					}
					return dto;
					})
				.collect(Collectors.toList());
				
		Long total = customerDao.getTotal();
		int lastPageNumber = (int) Math.ceil(total.doubleValue()/maxResult);
		ResultDto<CustomerDTO> result = new ResultDto<>();
		result.setDatas((List<CustomerDTO>) customersDto);
		result.setTotalRecord(total.intValue());
		result.setLastPage(lastPageNumber);
		// TODO Auto-generated method stub
		return (ResultDto<T>) result;
	}

	@Override
	public Long insert(Customer customer, List<CustomerDTO> lstDTO, List<PetDTO> lstPetDTO) {
		String cusCode = FunctionCommon.getCodeNextVal(lstDTO, "2VK", "%09d");
		customer.setCusCode(cusCode);
//		if (customer.getLstPet() != null && !customer.getLstPet().isEmpty()) {
//			customer.getLstPet().forEach(pet -> pet.setCustomer(customer));
//		}
		AtomicInteger index = new AtomicInteger(1);
		if (customer.getLstPet() != null && !customer.getLstPet().isEmpty()) {
			customer.getLstPet().forEach(pet -> {
				
				String petCode = FunctionCommon.getCodeNextVal(lstPetDTO, "2VT", "%09d", index.intValue());
				pet.setPetCode(petCode);
				pet.setCustomer(customer);
				index.addAndGet(1);
			});
		}
		return customerDao.insert(customer);
	}

//	@Override
//	public CustomerDTO getDetailCustomer(String cusCode) {
//		// TODO Auto-generated method stub
//		Customer customer = customerDao.getDetailCustomer(cusCode);
//		List<Pet> pets = customer.getLstPet();
//		ModelMapper mm = new ModelMapper();
//		CustomerDTO customerDto = mm.map(customer, CustomerDTO.class);
//		if (pets != null && !pets.isEmpty()) {
//			List<PetDTO> petDtos = pets.stream()
//					.map(pet -> {
//						PetDTO dto = mm.map(pet, PetDTO.class);
//						return dto;
//					})
//					.collect(Collectors.toList());
//			customerDto.setPetDtos(petDtos);
//		}
//		return customerDto;
//	}
	
	@Override
	public CustomerDTO getDetailCustomer(String cusCode) {
		// TODO Auto-generated method stub
		Customer customer = customerDao.getDetailCustomer(cusCode);
		List<PetDTO> pets = petDao.getLstDetailPet(cusCode);
		ModelMapper mm = new ModelMapper();
		CustomerDTO customerDto = mm.map(customer, CustomerDTO.class);
		if (pets != null && !pets.isEmpty()) {
			customerDto.setPetDtos(pets);
		}
		CustomerDTO customerExt = customerDao.getDetailCustomerExt(cusCode);
		customerDto.setWardId(customerExt.getWardId());
		customerDto.setWardName(customerExt.getWardName());
		customerDto.setDistrictId(customerExt.getDistrictId());
		customerDto.setDistrictName(customerExt.getDistrictName());
		customerDto.setProvinceId(customerExt.getProvinceId());
		customerDto.setProvinceName(customerExt.getProvinceName());
		return customerDto;
	}

	@Override
	public Long edit(Customer customer, List<CustomerDTO> lstDTO, List<PetDTO> lstPetDTO) {
		AtomicInteger index = new AtomicInteger(1);
		if (customer.getLstPet() != null && !customer.getLstPet().isEmpty()) {
//			customer.getLstPet().forEach(pet -> pet.setCustomer(customer));
			customer.getLstPet().forEach(pet -> {
				if(!StringUtils.isNotBlank(pet.getPetCode())) {
					String petCode = FunctionCommon.getCodeNextVal(lstPetDTO, "2VT", "%09d", index.intValue());
					pet.setPetCode(petCode);
					index.addAndGet(1);
				}
				pet.setCustomer(customer);
			});
		}
		return customerDao.edit(customer);
	}

//	@Override
//	public <T> ResultDto<T> searchAdvance(Customer customer, int pageNum, int maxResult) {
//		List<CustomerDTO> lst = customerDao.searchAdvance(customer, (pageNum -1)*maxResult, maxResult);
//		ModelMapper modelMapper = new ModelMapper();
//		List<CustomerType> types = new FunctionCommon().intialCustomerTypes();
//		List<CustomerDTO> customersDto = lst.stream()
//				.map(cus -> {
//					CustomerDTO dto = modelMapper.map(cus, CustomerDTO.class);
//					CustomerType typeObj = types.stream()
//					.filter(x -> x.getCode() == dto.getCusType())
//					.findAny().orElse(null);
//					if (typeObj != null && dto.getCusType() == typeObj.getCode()) {
//						dto.setCusTypeName(typeObj.getName());
//					}
//					return dto;
//					})
//				.collect(Collectors.toList());
//		ResultDto<CustomerDTO> result = new ResultDto<>();
//		result.setDatas((List<CustomerDTO>) customersDto);
////		result.setTotalRecord(total.intValue());
////		result.setLastPage(lastPageNumber);
//		return (ResultDto<T>) result;
//	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> ResultDto<T> searchAdvance(Customer customer, int pageNum, int maxResult) {
		List<CustomerDTO> lst = customerDao.searchAdvance(customer, (pageNum -1)*maxResult, maxResult);
		List<CustomerType> types = new FunctionCommon().intialCustomerTypes();
		List<CustomerDTO> customersDto = lst.stream()
				.map(cus -> {
					CustomerType typeObj = types.stream()
					.filter(x -> x.getCode() == cus.getCusType())
					.findAny().orElse(null);
					if (typeObj != null && cus.getCusType() == typeObj.getCode()) {
						cus.setCusTypeName(typeObj.getName());
					}
					return cus;
					})
				.collect(Collectors.toList());
		ResultDto<CustomerDTO> result = new ResultDto<>();
		result.setDatas((List<CustomerDTO>) customersDto);
		Long total = customerDao.getTotalSearchAd(customer);
		int lastPageNumber = (int) Math.ceil(total.doubleValue()/maxResult);
		result.setTotalRecord(total.intValue());
		result.setLastPage(lastPageNumber);
		return (ResultDto<T>) result;
	}

}
