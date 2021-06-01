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
import com.twovet.catalog.dto.ParasiticDTO;
import com.twovet.catalog.model.Parasitic;
import com.twovet.catalog.services.implement.ParasiticTestService;

@Controller
@RequestMapping("catalog/parasitic")
public class ParasiticTestController extends BaseController {
	@Autowired
	ParasiticTestService parasiticTestService;

	@Override
	public void setActiveLink(ServletRequestAttributes servletRequestAttributes, HttpSession httpSession) {
		new FunctionCommon(servletRequestAttributes, httpSession);

	}

	@Override
	public String initScreen(Model model, HttpSession httpSession) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		setActiveLink(attr, httpSession);
		model.addAttribute("title", "Danh mục Xét nghiệm kí sinh trùng");

		ResultDto<ParasiticDTO> result = parasiticTestService.searchAdvance(new Parasitic(), start, size);
		result.setCurrentPage(1);
		result.setMaxSize(size);

		model.addAttribute("parasiticTestDto", new ParasiticDTO());
		model.addAttribute("parasiticTest", new Parasitic());
		model.addAttribute("lstParasiticTest", result);

		return ViewNameConstants.CATALOG_PARASITIC;
	}

	@PostMapping(value = "/searchAdvance", consumes = "application/json", produces = "application/json")
	public String searchAdvance(ModelMap map, HttpServletRequest request, @RequestBody ParamBean<Parasitic> paramBean) {
		String currentPageStr = paramBean.getCurrentPage();
		Parasitic parasitic = paramBean.getData();
		int currentPage = currentPageStr != null ? Integer.valueOf(currentPageStr) : 1;
		ResultDto<ParasiticDTO> result = parasiticTestService.searchAdvance(parasitic, currentPage, size);
		result.setCurrentPage(Integer.valueOf(currentPage));
		result.setMaxSize(size);
		map.addAttribute("lstParasiticTest", result);
		return "pages/catalog/parasitic :: #refresh-section";
	}

	@GetMapping("/showDetail")
	public String showDetail(ModelMap map, HttpServletRequest request) {
		String parasiticTestCode = request.getParameter("parasiticTestCode");
		String modeScreen = request.getParameter("mode");
		ParasiticDTO result = parasiticTestService.getDetailParasiticTest(parasiticTestCode);
		map.addAttribute("parasiticTestDto", result);
		return "edit".equals(modeScreen) ? "pages/catalog/parasiticEdit :: #detailParasiticTestEdit": "pages/catalog/parasiticCrud :: #detailParasiticTest";
	}

	@PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
	public String addNew(ModelMap map, HttpServletRequest request, @RequestBody ParamBean<Parasitic> paramBean) {
		Long count = parasiticTestService.insert(paramBean.getData());
		SearchAdvanceParamBean<Parasitic> advanceParam = paramBean.getAdvanceParam();
		ResultDto<ParasiticDTO> result = parasiticTestService.searchAdvance(advanceParam.getSearchAdvanceParam(), 1, size);
		result.setCurrentPage(1);
		result.setMaxSize(size);
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstParasiticTest", result);
		return "pages/catalog/parasitic :: #refresh-section";
	}

	@PostMapping(value = "/edit", consumes = "application/json", produces = "application/json")
	public String edit(ModelMap map, HttpServletRequest request, @RequestBody ParamBean<Parasitic> paramBean) {
		Parasitic parasitic = paramBean.getData();
		int currentPage = Integer.valueOf(paramBean.getCurrentPage());
		Long count = parasiticTestService.edit(parasitic);
		String message = "";

		SearchAdvanceParamBean<Parasitic> advanceParam = paramBean.getAdvanceParam();
		ResultDto<ParasiticDTO> result = parasiticTestService.searchAdvance(advanceParam.getSearchAdvanceParam(), currentPage,size);
		result.setCurrentPage(currentPage);
		result.setMaxSize(size);

		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else {
			message = "ERROR";
		}

		map.addAttribute("message", message);
		map.addAttribute("lstParasiticTest", result);

		return "pages/catalog/parasitic :: #refresh-section";
	}

}
