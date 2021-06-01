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
import com.twovet.catalog.dto.BreedDTO;
import com.twovet.catalog.dto.SuperSonicDTO;
import com.twovet.catalog.model.Breed;
import com.twovet.catalog.model.SuperSonic;
import com.twovet.catalog.services.implement.SuperSonicService;

@Controller
@RequestMapping("catalog/sonic")

public class SuperSonicController extends BaseController {
	@Autowired
	SuperSonicService superSonicService;

	@Override
	public void setActiveLink(ServletRequestAttributes servletRequestAttributes, HttpSession httpSession) {
		new FunctionCommon(servletRequestAttributes, httpSession);

	}

	@Override
	public String initScreen(Model model, HttpSession session) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		setActiveLink(attr, session);
		model.addAttribute("title", "Danh mục Siêu âm");

		ResultDto<SuperSonicDTO> result = superSonicService.searchAdvance(new SuperSonic(), start, size);
		result.setCurrentPage(1);
		result.setMaxSize(size);
		
		model.addAttribute("superSonicDto", new SuperSonicDTO());
		model.addAttribute("superSonic", new SuperSonic());
		model.addAttribute("lstSuperSonic", result);
		
		return ViewNameConstants.CATALOG_SONIC;
	}
	
	@PostMapping(value = "/searchAdvance", consumes = "application/json", produces = "application/json")
	public String searchAdvance(ModelMap map, HttpServletRequest request, @RequestBody ParamBean<SuperSonic> paramBean) {
		String currentPageStr = paramBean.getCurrentPage();
		SuperSonic superSonic = paramBean.getData();
		int currentPage = currentPageStr != null ? Integer.valueOf(currentPageStr) : 1;
		ResultDto<SuperSonicDTO> result = superSonicService.searchAdvance(superSonic, currentPage, size);
		result.setCurrentPage(Integer.valueOf(currentPage));
		result.setMaxSize(size);
		map.addAttribute("lstSuperSonic", result);
		return "pages/catalog/sonic :: #refresh-section";
	}
	
	@GetMapping("/showDetail")
	public String showDetail(ModelMap map, HttpServletRequest request) {
		String superSonicCode = request.getParameter("superSonicCode");
		String modeScreen = request.getParameter("mode");
		SuperSonicDTO result = superSonicService.getDetailSuperSonic(superSonicCode);
		map.addAttribute("superSonicDto", result);
		return "edit".equals(modeScreen) ? "pages/catalog/sonicEdit :: #detailSuperSonicEdit" : "pages/catalog/sonicCrud :: #detailSuperSonic";
	}
	
	@PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
	public String addNew(ModelMap map, HttpServletRequest request,@RequestBody ParamBean<SuperSonic> paramBean) {
		Long count = superSonicService.insert(paramBean.getData());
		SearchAdvanceParamBean<SuperSonic> advanceParam = paramBean.getAdvanceParam();
		ResultDto<SuperSonicDTO> result = superSonicService.searchAdvance(advanceParam.getSearchAdvanceParam(), 1, size);
		result.setCurrentPage(1);
		result.setMaxSize(size);
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else  {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstSuperSonic", result);
		return "pages/catalog/sonic :: #refresh-section";
	}
	
	@PostMapping(value = "/edit", consumes = "application/json", produces = "application/json")
	public String edit(ModelMap map, HttpServletRequest request,@RequestBody ParamBean<SuperSonic> paramBean) {
		SuperSonic superSonic = paramBean.getData();
		int currentPage = Integer.valueOf(paramBean.getCurrentPage());
		Long count = superSonicService.edit(superSonic);
		String message = "";
		
		SearchAdvanceParamBean<SuperSonic> advanceParam = paramBean.getAdvanceParam();
		ResultDto<SuperSonicDTO> result = superSonicService.searchAdvance(advanceParam.getSearchAdvanceParam(), currentPage, size);
		result.setCurrentPage(currentPage);
		result.setMaxSize(size);
		
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else  {
			message = "ERROR";
		}
		
		map.addAttribute("message", message);
		map.addAttribute("lstSuperSonic", result);
		
		return "pages/catalog/sonic :: #refresh-section";
	}

}
