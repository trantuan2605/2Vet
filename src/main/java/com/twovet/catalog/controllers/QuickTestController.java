package com.twovet.catalog.controllers;

import java.util.List;

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
import com.twovet.base.constant.ViewNameConstants;
import com.twovet.catalog.dto.QuickTestDTO;
import com.twovet.catalog.model.QuickTest;
import com.twovet.catalog.services.implement.QuickTestService;

@Controller
@RequestMapping("catalog/quicktest")
public class QuickTestController extends BaseController{
	@Autowired
	private QuickTestService quickService;

	@Override
	public String initScreen(Model model, HttpSession session) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		setActiveLink(attr, session);
		model.addAttribute("title", "Danh mục test nhanh");
		List<QuickTestDTO> lstQuick = quickService.getAllQuickTest();
		model.addAttribute("lstQuick", lstQuick);
		model.addAttribute("quickDto", new QuickTestDTO());
		model.addAttribute("quick", new QuickTest());
		return ViewNameConstants.CATALOG_QUICK_TEST;
	}

	@Override
	public void setActiveLink(ServletRequestAttributes servletRequestAttributes, HttpSession httpSession) {
		// TODO Auto-generated method stub
		new FunctionCommon(servletRequestAttributes, httpSession);
	}
	
	@GetMapping("/showDetail")
	public String showDetail(ModelMap map, HttpServletRequest request) {
		String quickCode = request.getParameter("quickCode");
		String modeScreen = request.getParameter("mode");
		QuickTestDTO result = quickService.getDetaiQuickTest(quickCode);
		map.addAttribute("quickDto", result);
		return "edit".equals(modeScreen) ? "pages/catalog/quickTestEdit :: #detailQuickTestEdit" : "pages/catalog/quickTestCrud :: #detailQuickTest";
	}
	
	@PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
	public String addNew(ModelMap map, HttpServletRequest request,@RequestBody ParamBean<QuickTest> paramBean) {
		Long count = quickService.insert(paramBean.getData());
		List<QuickTestDTO> lstQuick = quickService.getAllQuickTest();
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else  {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstQuick", lstQuick);
		return "pages/catalog/quickTest :: #refresh-section";
	}
	
	@PostMapping(value = "/edit", consumes = "application/json", produces = "application/json")
	public String edit(ModelMap map, HttpServletRequest request,@RequestBody ParamBean<QuickTest> paramBean) {
		QuickTest quick = paramBean.getData();
		Long count = quickService.edit(quick);
		List<QuickTestDTO> lstQuick = quickService.getAllQuickTest();
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else  {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstQuick", lstQuick);
		return "pages/catalog/quickTest :: #refresh-section";
	}
}
