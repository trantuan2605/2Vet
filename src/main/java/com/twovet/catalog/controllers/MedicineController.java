package com.twovet.catalog.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.twovet.catalog.model.Branch;
import com.twovet.catalog.services.implement.BranchService;

@Controller
@RequestMapping("catalog/medicine")
public class MedicineController extends BaseController{
	@Autowired 
	BranchService branchService;

	/*
	 * @Override public String initial(Model model) { model.addAttribute("title",
	 * "Danh mục Thuốc"); return ViewNameConstants.CATALOG_MEDICINE; }
	 */

	@Override
	public String initScreen(Model model, HttpSession session) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		setActiveLink(attr, session);
		model.addAttribute("title", "Danh mục Thuốc");
		ResultDto<BranchDTO> result = branchService.searchAdvance(new Branch(), 0, 0);
		result.setCurrentPage(1);
		result.setMaxSize(size);
		model.addAttribute("lstBranch", result);
		model.addAttribute("branchDto", new Branch());
		Branch branch = new Branch();
		
		model.addAttribute("branch", branch);
		return ViewNameConstants.CATALOG_MEDICINE;
	}
	
	@GetMapping("/showDetail")
	public String showDetail(ModelMap map, HttpServletRequest request) {
		String branchCode = request.getParameter("branchCode");
		String modeScreen = request.getParameter("mode");
		BranchDTO result = branchService.getDetailBranch(branchCode);
		map.addAttribute("branchDto", result);
		return "edit".equals(modeScreen) ? "pages/catalog/medicineEdit :: #detailMedicineEdit" : "pages/catalog/medicineCrud :: #detailMedicine";
	}
	
	@PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
	public String addNew(ModelMap map, HttpServletRequest request,@RequestBody ParamBean<Branch> paramBean) {
//		Long count = branchService.insert(paramBean.getData(), );
//		SearchAdvanceParamBean<Branch> advanceParam = paramBean.getAdvanceParam();
//		ResultDto<BranchDTO> result = branchService.searchAdvance(new Branch(), 0, 0);
//		result.setCurrentPage(1);
//		result.setMaxSize(size);
//		String message = "";
//		if (count != null && count.compareTo(0L) > 0) {
//			message = "SUCCESS";
//		} else  {
//			message = "ERROR";
//		}
//		map.addAttribute("message", message);
//		map.addAttribute("lstBranch", result);
		return "pages/catalog/medicine :: #refresh-section";
	}

	@PostMapping(value = "/edit", consumes = "application/json", produces = "application/json")
	public String edit(ModelMap map, HttpServletRequest request,@RequestBody ParamBean<Branch> paramBean) {
		Branch branch = paramBean.getData();
		int currentPage = Integer.valueOf(paramBean.getCurrentPage());
		Long count = branchService.edit(branch, request);
		SearchAdvanceParamBean<Branch> advanceParam = paramBean.getAdvanceParam();
		ResultDto<BranchDTO> result = branchService.searchAdvance(new Branch(), 0, 0);
		result.setCurrentPage(currentPage);
		result.setMaxSize(size);
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else  {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstBranch", result);
		return "pages/catalog/medicine :: #refresh-section";
	}
	
	@Override
	public void setActiveLink(ServletRequestAttributes servletRequestAttributes, HttpSession httpSession) {
		// TODO Auto-generated method stub
		new FunctionCommon(servletRequestAttributes, httpSession);
	}
}
