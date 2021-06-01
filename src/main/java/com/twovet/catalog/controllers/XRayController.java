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
import com.twovet.catalog.dto.SuperSonicDTO;
import com.twovet.catalog.dto.XRayDTO;
import com.twovet.catalog.model.XRay;
import com.twovet.catalog.services.implement.XRayService;

@Controller
@RequestMapping("catalog/xray")

public class XRayController extends BaseController {
	@Autowired
	XRayService xRayService;

	@Override
	public void setActiveLink(ServletRequestAttributes servletRequestAttributes, HttpSession httpSession) {
		new FunctionCommon(servletRequestAttributes, httpSession);

	}

	@Override
	public String initScreen(Model model, HttpSession session) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		setActiveLink(attr, session);
		model.addAttribute("title", "Danh mục XQ");

		ResultDto<XRayDTO> result = xRayService.searchAdvance(new XRay(), start, size);
		result.setCurrentPage(1);
		result.setMaxSize(size);
		
		model.addAttribute("xRayDto", new XRayDTO());
		model.addAttribute("xRay", new XRay());
		model.addAttribute("lstXRay", result);
		
		return ViewNameConstants.CATALOG_XRAY;
	}
	
	@PostMapping(value = "/searchAdvance", consumes = "application/json", produces = "application/json")
	public String searchAdvance(ModelMap map, HttpServletRequest request, @RequestBody ParamBean<XRay> paramBean) {
		String currentPageStr = paramBean.getCurrentPage();
		XRay xRay = paramBean.getData();
		int currentPage = currentPageStr != null ? Integer.valueOf(currentPageStr) : 1;
		ResultDto<XRayDTO> result = xRayService.searchAdvance(xRay, currentPage, size);
		result.setCurrentPage(Integer.valueOf(currentPage));
		result.setMaxSize(size);
		map.addAttribute("lstXRay", result);
		return "pages/catalog/xray :: #refresh-section";
	}
	
	@GetMapping("/showDetail")
	public String showDetail(ModelMap map, HttpServletRequest request) {
		String xQCode = request.getParameter("xQCode");
		String modeScreen = request.getParameter("mode");
		XRayDTO result = xRayService.getDetailXRay(xQCode);
		map.addAttribute("xRayDto", result);
		return "edit".equals(modeScreen) ? "pages/catalog/xrayEdit :: #detailXRayEdit" : "pages/catalog/xrayCrud :: #detailXRay";
	}
	
	@PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
	public String addNew(ModelMap map, HttpServletRequest request,@RequestBody ParamBean<XRay> paramBean) {
		Long count = xRayService.insert(paramBean.getData());
		SearchAdvanceParamBean<XRay> advanceParam = paramBean.getAdvanceParam();
		ResultDto<XRayDTO> result = xRayService.searchAdvance(advanceParam.getSearchAdvanceParam(), 1, size);
		result.setCurrentPage(1);
		result.setMaxSize(size);
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else  {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstXRay", result);
		return "pages/catalog/xray :: #refresh-section";
	}
	
	@PostMapping(value = "/edit", consumes = "application/json", produces = "application/json")
	public String edit(ModelMap map, HttpServletRequest request,@RequestBody ParamBean<XRay> paramBean) {
		XRay xRay = paramBean.getData();
		int currentPage = Integer.valueOf(paramBean.getCurrentPage());
		Long count = xRayService.edit(xRay);
		String message = "";
		
		SearchAdvanceParamBean<XRay> advanceParam = paramBean.getAdvanceParam();
		ResultDto<SuperSonicDTO> result = xRayService.searchAdvance(advanceParam.getSearchAdvanceParam(), currentPage, size);
		result.setCurrentPage(currentPage);
		result.setMaxSize(size);
		
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else  {
			message = "ERROR";
		}
		
		map.addAttribute("message", message);
		map.addAttribute("lstXRay", result);
		
		return "pages/catalog/xray :: #refresh-section";
	}

}
