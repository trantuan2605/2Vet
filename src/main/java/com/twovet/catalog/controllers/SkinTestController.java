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
import com.twovet.catalog.dto.SkinTestDTO;
import com.twovet.catalog.model.SkinTest;
import com.twovet.catalog.services.implement.SkinTestService;

@Controller
@RequestMapping("catalog/skin")
public class SkinTestController extends BaseController {
	@Autowired
	SkinTestService skinTestService;

	@Override
	public void setActiveLink(ServletRequestAttributes servletRequestAttributes, HttpSession httpSession) {
		new FunctionCommon(servletRequestAttributes, httpSession);

	}

	@Override
	public String initScreen(Model model, HttpSession httpSession) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		setActiveLink(attr, httpSession);
		model.addAttribute("title", "Danh mục Xét nghiệm da");

		ResultDto<SkinTestDTO> result = skinTestService.searchAdvance(new SkinTest(), start, size);
		result.setCurrentPage(1);
		result.setMaxSize(size);

		model.addAttribute("skinTestDto", new SkinTestDTO());
		model.addAttribute("skinTest", new SkinTest());
		model.addAttribute("lstSkinTest", result);

		return ViewNameConstants.CATALOG_SKIN;
	}

	@PostMapping(value = "/searchAdvance", consumes = "application/json", produces = "application/json")
	public String searchAdvance(ModelMap map, HttpServletRequest request, @RequestBody ParamBean<SkinTest> paramBean) {
		String currentPageStr = paramBean.getCurrentPage();
		SkinTest skinTest = paramBean.getData();
		int currentPage = currentPageStr != null ? Integer.valueOf(currentPageStr) : 1;
		ResultDto<SkinTestDTO> result = skinTestService.searchAdvance(skinTest, currentPage, size);
		result.setCurrentPage(Integer.valueOf(currentPage));
		result.setMaxSize(size);
		map.addAttribute("lstSkinTest", result);
		return "pages/catalog/skin :: #refresh-section";
	}

	@GetMapping("/showDetail")
	public String showDetail(ModelMap map, HttpServletRequest request) {
		String skinTestCode = request.getParameter("skinTestCode");
		String modeScreen = request.getParameter("mode");
		SkinTestDTO result = skinTestService.getDetailSkinTest(skinTestCode);
		map.addAttribute("skinTestDto", result);
		return "edit".equals(modeScreen) ? "pages/catalog/skinEdit :: #detailSkinTestEdit" : "pages/catalog/skinCrud :: #detailSkinTest";
	}

	@PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
	public String addNew(ModelMap map, HttpServletRequest request, @RequestBody ParamBean<SkinTest> paramBean) {
		Long count = skinTestService.insert(paramBean.getData());
		SearchAdvanceParamBean<SkinTest> advanceParam = paramBean.getAdvanceParam();
		ResultDto<SkinTestDTO> result = skinTestService.searchAdvance(advanceParam.getSearchAdvanceParam(), 1, size);
		result.setCurrentPage(1);
		result.setMaxSize(size);
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstSkinTest", result);
		return "pages/catalog/skin :: #refresh-section";
	}

	@PostMapping(value = "/edit", consumes = "application/json", produces = "application/json")
	public String edit(ModelMap map, HttpServletRequest request, @RequestBody ParamBean<SkinTest> paramBean) {
		SkinTest skinTest = paramBean.getData();
		int currentPage = Integer.valueOf(paramBean.getCurrentPage());
		Long count = skinTestService.edit(skinTest);
		String message = "";

		SearchAdvanceParamBean<SkinTest> advanceParam = paramBean.getAdvanceParam();
		ResultDto<SkinTestDTO> result = skinTestService.searchAdvance(advanceParam.getSearchAdvanceParam(), currentPage, size);
		result.setCurrentPage(currentPage);
		result.setMaxSize(size);

		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else {
			message = "ERROR";
		}

		map.addAttribute("message", message);
		map.addAttribute("lstSkinTest", result);

		return "pages/catalog/skin :: #refresh-section";
	}

}
