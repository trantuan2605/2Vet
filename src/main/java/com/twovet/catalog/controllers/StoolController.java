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
import com.twovet.base.constant.ViewNameConstants;
import com.twovet.catalog.dto.StoolDTO;
import com.twovet.catalog.model.Stool;
import com.twovet.catalog.services.implement.StoolService;

@Controller
@RequestMapping("catalog/stool")
public class StoolController extends BaseController {
	@Autowired
	StoolService stoolService;

	@Override
	public void setActiveLink(ServletRequestAttributes servletRequestAttributes, HttpSession httpSession) {
		new FunctionCommon(servletRequestAttributes, httpSession);

	}

	@Override
	public String initScreen(Model model, HttpSession httpSession) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		setActiveLink(attr, httpSession);
		model.addAttribute("title", "Danh mục Xét nghiệm phân");

		ResultDto<StoolDTO> result = stoolService.searchAdvance(new Stool(), 0, 0);
		result.setCurrentPage(1);
		result.setMaxSize(size);

		model.addAttribute("stoolDto", new StoolDTO());
		model.addAttribute("stool", new Stool());
		model.addAttribute("lstStool", result);

		return ViewNameConstants.CATALOG_STOOL;
	}


	@GetMapping("/showDetail")
	public String showDetail(ModelMap map, HttpServletRequest request) {
		String stoolCode = request.getParameter("stoolCode");
		String modeScreen = request.getParameter("mode");
		StoolDTO result = stoolService.getDetailStool(stoolCode);
		map.addAttribute("stoolDto", result);
		return "edit".equals(modeScreen) ? "pages/catalog/stoolEdit :: #detailStoolEdit": "pages/catalog/stoolCrud :: #detailStool";
	}

	@PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
	public String addNew(ModelMap map, HttpServletRequest request, @RequestBody ParamBean<Stool> paramBean) {
		Long count = stoolService.insert(paramBean.getData());
		ResultDto<StoolDTO> result = stoolService.searchAdvance(new Stool(), 0, 0);
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstStool", result);
		return "pages/catalog/stool :: #refresh-section";
	}

	@PostMapping(value = "/edit", consumes = "application/json", produces = "application/json")
	public String edit(ModelMap map, HttpServletRequest request, @RequestBody ParamBean<Stool> paramBean) {
		Stool stool = paramBean.getData();
		Long count = stoolService.edit(stool);
		String message = "";

		ResultDto<StoolDTO> result = stoolService.searchAdvance(new Stool(), 0, 0);

		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else {
			message = "ERROR";
		}

		map.addAttribute("message", message);
		map.addAttribute("lstStool", result);

		return "pages/catalog/stool :: #refresh-section";
	}

}
