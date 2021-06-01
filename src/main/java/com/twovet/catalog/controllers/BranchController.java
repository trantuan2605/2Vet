package com.twovet.catalog.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.twovet.base.common.BaseController;
import com.twovet.base.common.FunctionCommon;
import com.twovet.base.common.ParamBean;
import com.twovet.base.common.ResultDto;
import com.twovet.base.common.SearchAdvanceParamBean;
import com.twovet.base.constant.ViewNameConstants;
import com.twovet.catalog.dto.BranchDTO;
import com.twovet.catalog.dto.DistrictDTO;
import com.twovet.catalog.dto.ProvinceDTO;
import com.twovet.catalog.dto.ServiceDTO;
import com.twovet.catalog.dto.WardDTO;
import com.twovet.catalog.model.Branch;
import com.twovet.catalog.model.ServicesBranch;
import com.twovet.catalog.services.implement.BranchService;
import com.twovet.catalog.services.implement.ProvinceService;
import com.twovet.catalog.services.implement.ServiceService;

@Controller
@RequestMapping("catalog/branch")
public class BranchController extends BaseController{
	@Autowired 
	BranchService branchService;
	@Autowired
	ServiceService services;
	@Autowired
	ProvinceService provinceService;
	
	private List<DistrictDTO> districts;
	private List<WardDTO> wards;
	private List<BranchDTO> lstBranchSeqCode; 
	
	@Override
	public String initScreen(Model model, HttpSession session) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		setActiveLink(attr, session);
		model.addAttribute("title", "Danh mục Chi nhánh");
		ResultDto<BranchDTO> result = branchService.searchAdvance(new Branch(), start, size);
		result.setCurrentPage(1);
		result.setMaxSize(size);
		model.addAttribute("lstBranch", result);
		model.addAttribute("branchDto", new BranchDTO());
		List<ProvinceDTO> provinces = provinceService.getAll();
		model.addAttribute("provinces", provinces != null ? provinces : new ArrayList<>());
		model.addAttribute("districts", new ArrayList<DistrictDTO>());
		model.addAttribute("wards", new ArrayList<WardDTO>());
		Branch branch = new Branch();
		if (result.getDatas() != null && !result.getDatas().isEmpty()) {
			lstBranchSeqCode = result.getDatas();
		} else {
			lstBranchSeqCode = new ArrayList<>();
		}
		model.addAttribute("branch", branch);
		return ViewNameConstants.CATALOG_BRANCH;
	}

	@Override
	public void setActiveLink(ServletRequestAttributes servletRequestAttributes, HttpSession httpSession) {
		// TODO Auto-generated method stub
		new FunctionCommon(servletRequestAttributes, httpSession);
	}
	
	@PostMapping(value = "/add")
	public String addNew(ModelMap map, HttpServletRequest request,@ModelAttribute Branch paramBean) {
		Long count = branchService.insert(paramBean, lstBranchSeqCode, request);
		ResultDto<BranchDTO> result = branchService.searchAdvance(new Branch(), 0, 0);
		result.setCurrentPage(1);
		result.setMaxSize(size);
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
			lstBranchSeqCode = result.getDatas();
		} else  {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstBranch", result);
		return "pages/catalog/branch :: #refresh-section";
	}
	
	@PostMapping(value = "/edit")
	public String edit(ModelMap map, HttpServletRequest request, @ModelAttribute Branch paramBean) {
		Branch branch = paramBean;
		Long count = branchService.edit(branch, request);
		ResultDto<BranchDTO> result = branchService.searchAdvance(new Branch(), 0, 0);
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else  {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstBranch", result);
		return "pages/catalog/branch :: #refresh-section";
	}
	
	@GetMapping("/showDetail")
	public String showDetail(ModelMap map, HttpServletRequest request) {
		String branchCode = request.getParameter("branchCode");
		String modeScreen = request.getParameter("mode");
		BranchDTO result = branchService.getDetailBranch(branchCode);
		map.addAttribute("branchDto", result);
		if (StringUtils.isBlank(modeScreen)) {
			List<ServiceDTO> lstBranchService = branchService.getListBranchService(branchCode);
			map.addAttribute("lstBranchService", lstBranchService);
		}
		List<ProvinceDTO> provinces = provinceService.getAll();
		map.addAttribute("provinces", provinces != null ? provinces : new ArrayList<>());
		districts = new ArrayList<DistrictDTO>();
		districts.add(new DistrictDTO(result.getDistrictId(), result.getDistrictName(), result.getProvinceId()));
		map.addAttribute("districts", districts);
		wards = new ArrayList<WardDTO>();
		wards.add(new WardDTO(result.getWardId(), result.getWardName(), result.getDistrictId()));
		map.addAttribute("wards", wards);
		return "edit".equals(modeScreen) ? "pages/catalog/branchEdit :: #detailBranchEdit" : "pages/catalog/branchCrud :: #detailBranch";
	}
	
	@PostMapping(value = "/searchAdvance", consumes = "application/json", produces = "application/json")
	public String searchAdvance(ModelMap map, HttpServletRequest request, @RequestBody ParamBean<Branch> paramBean) {
		String currentPageStr = paramBean.getCurrentPage();
		Branch branch = paramBean.getData();
		int currentPage = currentPageStr != null ? Integer.valueOf(currentPageStr) : 1;
		ResultDto<BranchDTO> result = branchService.searchAdvance(branch, currentPage, size);
		result.setCurrentPage(Integer.valueOf(currentPage));
		result.setMaxSize(size);
		map.addAttribute("lstBranch", result);
		return "pages/catalog/branch :: #refresh-section";
	}
	
	@GetMapping("/showListService")
	public String showListService(ModelMap map, HttpServletRequest request) {
		String branchCode = request.getParameter("branchCode");
		List<ServiceDTO> result = services.getAllSevices(branchCode);
		map.addAttribute("lstService", result);
		return "pages/catalog/branchServiceAddNew.html :: #refresh-service-section";
	}
	
	@PostMapping(value = "/saveSeviceBranch", consumes = "application/json", produces = "application/json")
	public String saveSeviceBranch(ModelMap map, HttpServletRequest request,@RequestBody ParamBean<Branch> paramBean) {
		List<ServicesBranch> lstParam = paramBean.getData().getLstServiceBranch();
		if (lstParam != null && !lstParam.isEmpty()) {
			boolean resultSave = branchService.saveServicesBranch(lstParam);
			String message = "";
			if (resultSave) {
				message = "SUCCESS";
			} else {
				message = "ERROR";
			}
			map.addAttribute("message", message);
			String branchCode = lstParam.get(0).getBranchCode();
			BranchDTO result = branchService.getDetailBranch(branchCode);
			map.addAttribute("branchDto", result);
			List<ServiceDTO> lstBranchService = branchService.getListBranchService(branchCode);
			map.addAttribute("lstBranchService", lstBranchService);
		} else {
			map.addAttribute("message", "SUCCESS");
			map.addAttribute("lstBranchService", new ArrayList<>());
		}
		return "pages/catalog/branchCrud :: #detailBranch";
	}
}
