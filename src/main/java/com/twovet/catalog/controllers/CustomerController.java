package com.twovet.catalog.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.twovet.base.common.BaseController;
import com.twovet.base.common.FunctionCommon;
import com.twovet.base.common.ParamBean;
import com.twovet.base.common.ResultBean;
import com.twovet.base.common.ResultDto;
import com.twovet.base.common.SearchAdvanceParamBean;
import com.twovet.base.constant.ViewNameConstants;
import com.twovet.catalog.dto.BreedDTO;
import com.twovet.catalog.dto.CustomerDTO;
import com.twovet.catalog.dto.DistrictDTO;
import com.twovet.catalog.dto.PetDTO;
import com.twovet.catalog.dto.ProvinceDTO;
import com.twovet.catalog.dto.SpecDTO;
import com.twovet.catalog.dto.WardDTO;
import com.twovet.catalog.model.Customer;
import com.twovet.catalog.model.CustomerType;
import com.twovet.catalog.services.implement.BreedService;
import com.twovet.catalog.services.implement.CustomerService;
import com.twovet.catalog.services.implement.DistrictService;
import com.twovet.catalog.services.implement.PetService;
import com.twovet.catalog.services.implement.ProvinceService;
import com.twovet.catalog.services.implement.SpecService;
import com.twovet.catalog.services.implement.WardService;

@Controller
@RequestMapping("catalog/customer")
public class CustomerController extends BaseController{
	@Autowired 
	CustomerService customerService;
	@Autowired
	BreedService breedService;
	@Autowired
	SpecService specService;
	@Autowired
	PetService petService;
	@Autowired
	ProvinceService provinceService;
	private List<BreedDTO> lstBreed;
	
	private List<CustomerDTO> lstCusSeqCode;
	private List<PetDTO> lstPetSeqCode;
	
	private List<ProvinceDTO> provinces;
	private List<DistrictDTO> districts;
	private List<WardDTO> wards;
	
	@Override
	public String initScreen(Model model, HttpSession session) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		setActiveLink(attr, session);
		model.addAttribute("title", "Danh mục Khách hàng");
//		ResultDto<CustomerDTO> result = customerService.listCustomer(start, size);
		ResultDto<CustomerDTO> result = customerService.searchAdvance(new Customer(), start, size);
		result.setCurrentPage(1);
		result.setMaxSize(size);
		model.addAttribute("lstCustomer", result);
		if (result.getDatas() != null && !result.getDatas().isEmpty()) {
			lstCusSeqCode = result.getDatas();
		} else {
			lstCusSeqCode = new ArrayList<>();
		}
		lstPetSeqCode = petService.getLstSequenceCode();
		if (lstPetSeqCode == null || (lstPetSeqCode != null && lstPetSeqCode.isEmpty())) {
			lstPetSeqCode = new ArrayList<>();
		}
		model.addAttribute("customerDto", new CustomerDTO());
		lstBreed = breedService.getAll();
		Customer customer = new Customer();
		
		// initial Customer Type
		List<CustomerType> customerTypes = setDataCustomerType();
		
		// get list province
		provinces = provinceService.getAll();
		
		model.addAttribute("customer", customer);
		model.addAttribute("customerTypes", customerTypes);
		model.addAttribute("provinces", provinces != null ? provinces : new ArrayList<>());
		model.addAttribute("districts", new ArrayList<DistrictDTO>());
		model.addAttribute("wards", new ArrayList<WardDTO>());
		model.addAttribute("lstBreed", lstBreed);
		return ViewNameConstants.CATALOG_CUSTOMER;
	}

	@Override
	public void setActiveLink(ServletRequestAttributes servletRequestAttributes, HttpSession httpSession) {
		// TODO Auto-generated method stub
		new FunctionCommon(servletRequestAttributes, httpSession);
	}
	
	@GetMapping("/search")
	public String clickPageNum(ModelMap map, HttpServletRequest request) {
		String pageNumStr = request.getParameter("page");
		int pageNum = StringUtils.isNotBlank(pageNumStr) ? Integer.valueOf(pageNumStr) : 0;
		ResultDto<CustomerDTO> result = customerService.listCustomer(pageNum, size);
		result.setCurrentPage(pageNum);
		result.setMaxSize(size);
		map.addAttribute("lstCustomer", result);
		return "pages/catalog/customer :: #customerTbl";
	}
	
	@PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
	public String addNew(ModelMap map, HttpServletRequest request,@RequestBody ParamBean<Customer> paramBean) {
		Long count = customerService.insert(paramBean.getData(), lstCusSeqCode, lstPetSeqCode);
//		List<Integer> lstObj = FunctionCommon.getLstSeqCode(lstCusSeqCode);
//		Long count = 0L;
		SearchAdvanceParamBean<Customer> advanceParam = paramBean.getAdvanceParam();
		ResultDto<CustomerDTO> result = customerService.searchAdvance(advanceParam.getSearchAdvanceParam(), 1, size);
		result.setCurrentPage(1);
		result.setMaxSize(size);
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			lstCusSeqCode = result.getDatas();
			lstPetSeqCode = petService.getLstSequenceCode();
			message = "SUCCESS";
		} else  {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstCustomer", result);
		return "pages/catalog/customer :: #refresh-section";
	}
	
	@PostMapping(value = "/edit", consumes = "application/json", produces = "application/json")
//	public String Edit(ModelMap map, HttpServletRequest request,@RequestBody Customer customer) {
	public String edit(ModelMap map, HttpServletRequest request,@RequestBody ParamBean<Customer> paramBean) {
		Customer customer = paramBean.getData();
		int currentPage = Integer.valueOf(paramBean.getCurrentPage());
		Long count = customerService.edit(customer, lstCusSeqCode, lstPetSeqCode);
//		ResultDto<CustomerDTO> result = customerService.listCustomer(currentPage, size);
		SearchAdvanceParamBean<Customer> advanceParam = paramBean.getAdvanceParam();
		ResultDto<CustomerDTO> result = customerService.searchAdvance(advanceParam.getSearchAdvanceParam(), currentPage, size);
		result.setCurrentPage(currentPage);
		result.setMaxSize(size);
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			lstCusSeqCode = result.getDatas();
			lstPetSeqCode = petService.getLstSequenceCode();
			message = "SUCCESS";
		} else  {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstCustomer", result);
//		return "pages/catalog/customer :: #customerTbl";
		return "pages/catalog/customer :: #refresh-section";
	}
	
	@GetMapping("/showDetail")
	public String showDetail(ModelMap map, HttpServletRequest request) {
		String cusCode = request.getParameter("cusCode");
		String modeScreen = request.getParameter("mode");
		CustomerDTO result = customerService.getDetailCustomer(cusCode);
		map.addAttribute("customerDto", result);
		// initial Customer Type
		List<CustomerType> customerTypes = setDataCustomerType();
		map.addAttribute("customerTypes", customerTypes);
		map.addAttribute("lstBreed", lstBreed);
		map.addAttribute("provinces", provinces != null ? provinces : new ArrayList<ProvinceDTO>());
		districts = new ArrayList<DistrictDTO>();
		districts.add(new DistrictDTO(result.getDistrictId(), result.getDistrictName(), result.getProvinceId()));
		map.addAttribute("districts", districts);
		wards = new ArrayList<WardDTO>();
		wards.add(new WardDTO(result.getWardId(), result.getWardName(), result.getDistrictId()));
		map.addAttribute("wards", wards);
		return "edit".equals(modeScreen) ? "pages/catalog/customerEdit :: #detailCustomerEdit" : "pages/catalog/customerCrud :: #detailCustomer";
	}
	
	@PostMapping(value = "/searchAdvance", consumes = "application/json", produces = "application/json")
	public String searchAdvance(ModelMap map, HttpServletRequest request, @RequestBody ParamBean<Customer> paramBean) {
		String currentPageStr = paramBean.getCurrentPage();
		Customer customer = paramBean.getData();
		int currentPage = currentPageStr != null ? Integer.valueOf(currentPageStr) : 1;
		ResultDto<CustomerDTO> result = customerService.searchAdvance(customer, currentPage, size);
		result.setCurrentPage(Integer.valueOf(currentPage));
		result.setMaxSize(size);
		map.addAttribute("lstCustomer", result);
		return "pages/catalog/customer :: #refresh-section";
	}
	
	private List<CustomerType> setDataCustomerType() {
		List<CustomerType> customerTypes = new ArrayList<>();
		customerTypes.add(new CustomerType(1, "Khách hàng cá nhân"));
		customerTypes.add(new CustomerType(2, "Khách hàng doanh nghiệp"));
		customerTypes.add(new CustomerType(3, "Khách hàng trong nước"));
		customerTypes.add(new CustomerType(4, "Khách hàng nước ngoài"));
		return customerTypes;
	}
	
//	@GetMapping("/getSpecsByBreedCode")
//	public String getSpecsByBreedCode(ModelMap map, HttpServletRequest request) {
//		String breedCode = request.getParameter("breedCode");
//		String edit = request.getParameter("edit");
//		String index = request.getParameter("index");
//		String returnId = "pages/catalog/customerAddNew :: #species-droplist-add" + index;
//		List<SpecDTO> extendSpecs = new ArrayList<>();
//		extendSpecs = specService.getLstSpecByCode(breedCode);
//		map.addAttribute("lstSpec", extendSpecs);
////		map.addAttribute("customerDto", new CustomerDTO());
//		return "1".equals(edit) ? "pages/catalog/petEdit :: #species-droplist-edit" : returnId;
//	}
	
	@GetMapping("/getSpecsByBreedCodeJson")
	@ResponseBody
	public ResponseEntity<?> getSpecsByBreedCodeToJson(ModelMap map, HttpServletRequest request) {
		ResultBean<SpecDTO> result = new ResultBean<>();
		String breedCode = request.getParameter("breedCode");
		List<SpecDTO> extendSpecs = new ArrayList<>();
		extendSpecs = specService.getLstSpecByCode(breedCode);
		result.setResults(extendSpecs);
		return ResponseEntity.ok(result);
	}

	public List<CustomerDTO> getLstCusSeqCode() {
		return lstCusSeqCode;
	}

	public void setLstCusSeqCode(List<CustomerDTO> lstCusSeqCode) {
		this.lstCusSeqCode = lstCusSeqCode;
	}

	public List<PetDTO> getLstPetSeqCode() {
		return lstPetSeqCode;
	}

	public void setLstPetSeqCode(List<PetDTO> lstPetSeqCode) {
		this.lstPetSeqCode = lstPetSeqCode;
	}

	public final List<ProvinceDTO> getProvinces() {
		return provinces;
	}

	public final void setProvinces(List<ProvinceDTO> provinces) {
		this.provinces = provinces;
	}

	public final List<DistrictDTO> getDistricts() {
		return districts;
	}

	public final void setDistricts(List<DistrictDTO> districts) {
		this.districts = districts;
	}

	public final List<WardDTO> getWards() {
		return wards;
	}

	public final void setWards(List<WardDTO> wards) {
		this.wards = wards;
	}
}
