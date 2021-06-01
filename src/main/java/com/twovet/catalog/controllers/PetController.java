package com.twovet.catalog.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.twovet.catalog.dto.PetDTO;
import com.twovet.catalog.dto.SpecDTO;
import com.twovet.catalog.model.Pet;
import com.twovet.catalog.services.implement.BreedService;
import com.twovet.catalog.services.implement.CustomerService;
import com.twovet.catalog.services.implement.PetService;
import com.twovet.catalog.services.implement.SpecService;

@Controller
@RequestMapping("catalog/pet")
public class PetController extends BaseController{
	@Autowired
	private PetService petService;
	@Autowired
	private BreedService breedService;
	@Autowired
	private SpecService specService;
	@Autowired
	private CustomerService customerService;
	
	private List<BreedDTO> lstBreed;
	private List<SpecDTO> lstSpec;
	private List<CustomerDTO> lstCus;
	private List<PetDTO> lstPetSeqCode;

	@Override
	public String initScreen(Model model, HttpSession session) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		setActiveLink(attr, session);
		model.addAttribute("title", "Danh mục Thú cưng");
		ResultDto<PetDTO> result = petService.searchAdvance(new Pet(), 0, 0);
		result.setCurrentPage(1);
		result.setMaxSize(size);
		model.addAttribute("lstPet", result);
		model.addAttribute("petDto", new PetDTO());
		model.addAttribute("pet", new Pet());
		lstBreed = breedService.getAll();
		model.addAttribute("lstBreed",lstBreed); 
		lstCus = customerService.listAllCustomer();
		model.addAttribute("lstCus",lstCus);
		if (result.getDatas() != null && !result.getDatas().isEmpty()) {
			lstPetSeqCode = result.getDatas();
		} else {
			lstPetSeqCode = new ArrayList<>();
		}
		return ViewNameConstants.CATALOG_PET;
	}

	@Override
	public void setActiveLink(ServletRequestAttributes servletRequestAttributes, HttpSession httpSession) {
		// TODO Auto-generated method stub
		new FunctionCommon(servletRequestAttributes, httpSession);
	}
	
	@PostMapping(value = "/searchAdvance", consumes = "application/json", produces = "application/json")
	public String searchAdvance(ModelMap map, HttpServletRequest request, @RequestBody ParamBean<Pet> paramBean) {
		String currentPageStr = paramBean.getCurrentPage();
		Pet pet = paramBean.getData();
		int currentPage = currentPageStr != null ? Integer.valueOf(currentPageStr) : 1;
		ResultDto<CustomerDTO> result = petService.searchAdvance(pet, currentPage, size);
		result.setCurrentPage(Integer.valueOf(currentPage));
		result.setMaxSize(size);
		map.addAttribute("lstPet", result);
		return "pages/catalog/pet :: #refresh-section";
	}
	
	@GetMapping("/showDetail")
	public String showDetail(ModelMap map, HttpServletRequest request) {
		String petCode = request.getParameter("petCode");
		String modeScreen = request.getParameter("mode");
		PetDTO result = petService.getDetailPet(petCode);
		map.addAttribute("petDto", result);
		map.addAttribute("lstBreed", lstBreed);
		lstSpec = specService.getLstSpecByCode(result.getBreedCode());
		map.addAttribute("lstSpec", lstSpec);
		map.addAttribute("lstCus", lstCus);
		return "edit".equals(modeScreen) ? "pages/catalog/petEdit :: #detailPetEdit" : "pages/catalog/petCrud :: #detailPet";
	}
	
//	@PostMapping(value = "/add")
//	public String addNew(ModelMap map, HttpServletRequest request,@RequestBody ParamBean<Pet> paramBean) {
//		Long count = petService.insert(paramBean.getData(), lstPetSeqCode, request);
//		SearchAdvanceParamBean<Pet> advanceParam = paramBean.getAdvanceParam();
//		ResultDto<Pet> result = petService.searchAdvance(new Pet(), 0, 0);
//		result.setCurrentPage(1);
//		result.setMaxSize(size);
//		String message = "";
//		if (count != null && count.compareTo(0L) > 0) {
//			message = "SUCCESS";
//		} else  {
//			message = "ERROR";
//		}
//		map.addAttribute("message", message);
//		map.addAttribute("lstPet", result);
//		return "pages/catalog/pet :: #refresh-section";
//	}
	
	@PostMapping(value = "/add")
	public String addNew(ModelMap map, HttpServletRequest request, @ModelAttribute Pet paramBean) {
		Long count = petService.insert(paramBean, lstPetSeqCode, request);
		ResultDto<PetDTO> result = petService.searchAdvance(new Pet(), 0, 0);
		result.setCurrentPage(1);
		result.setMaxSize(size);
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
			lstPetSeqCode = result.getDatas();
		} else  {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstPet", result);
		return "pages/catalog/pet :: #refresh-section";
	}
	
	@PostMapping(value = "/edit")
	public String edit(ModelMap map, HttpServletRequest request, @ModelAttribute Pet paramBean) {
		Pet pet = paramBean;
		Long count = petService.edit(pet, request);
//		ResultDto<PetDTO> result = petService.searchAdvance(advanceParam.getSearchAdvanceParam(), currentPage, size);
		ResultDto<PetDTO> result = petService.searchAdvance(new Pet(), 0, 0);
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else  {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstPet", result);
		return "pages/catalog/pet :: #refresh-section";
	}
	
	@GetMapping("/getSpecsByBreedCode")
	public String getSpecsByBreedCode(ModelMap map, HttpServletRequest request) {
		String breedCode = request.getParameter("breedCode");
		String edit = request.getParameter("edit");
		List<SpecDTO> extendSpecs = new ArrayList<>();
		extendSpecs = specService.getLstSpecByCode(breedCode);
		map.addAttribute("lstSpec", extendSpecs);
		map.addAttribute("petDto", new PetDTO());
		return "1".equals(edit) ? "pages/catalog/petEdit :: #species-droplist-edit" : "pages/catalog/petAddNew :: #species-droplist-add";
	}
	
	@GetMapping("/getLstPetByCusCode")
	@ResponseBody
	public ResponseEntity<?> getLstPetByCusCode(ModelMap map, HttpServletRequest request) {
		ResultBean<PetDTO> result = new ResultBean<>();
		String cusCode = request.getParameter("cusCode");
		List<PetDTO> extendSpecs = new ArrayList<>();
		extendSpecs = petService.getPetByCusCode(cusCode);
		result.setResults(extendSpecs);
		return ResponseEntity.ok(result);
	}

	public List<PetDTO> getLstPetSeqCode() {
		return lstPetSeqCode;
	}

	public void setLstPetSeqCode(List<PetDTO> lstPetSeqCode) {
		this.lstPetSeqCode = lstPetSeqCode;
	}
	
}
