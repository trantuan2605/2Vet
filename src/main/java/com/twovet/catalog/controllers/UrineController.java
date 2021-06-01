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
import com.twovet.catalog.dto.UrineDTO;
import com.twovet.catalog.model.Stool;
import com.twovet.catalog.model.Urine;
import com.twovet.catalog.services.implement.UrineService;

@Controller
@RequestMapping("catalog/urine")
public class UrineController extends BaseController {
	@Autowired
	UrineService urineService;

	@Override
	public void setActiveLink(ServletRequestAttributes servletRequestAttributes, HttpSession httpSession) {
		new FunctionCommon(servletRequestAttributes, httpSession);

	}

	@Override
	public String initScreen(Model model, HttpSession httpSession) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		setActiveLink(attr, httpSession);
		model.addAttribute("title", "Danh mục Xét nghiệm nước tiểu");

		ResultDto<UrineDTO> result = urineService.searchAdvance(new Urine(), 0, 0);
		result.setCurrentPage(1);
		result.setMaxSize(size);

		model.addAttribute("urineDto", new UrineDTO());
		model.addAttribute("urine", new Urine());
		model.addAttribute("lstUrine", result);

		return ViewNameConstants.CATALOG_URINE;
	}


	@GetMapping("/showDetail")
	public String showDetail(ModelMap map, HttpServletRequest request) {
		String urineCode = request.getParameter("urineCode");
		String modeScreen = request.getParameter("mode");
		UrineDTO result = urineService.getDetailUrine(urineCode);
		map.addAttribute("urineDto", result);
		return "edit".equals(modeScreen) ? "pages/catalog/urineEdit :: #detailUrineEdit": "pages/catalog/urineCrud :: #detailUrine";
	}

	@PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
	public String addNew(ModelMap map, HttpServletRequest request, @RequestBody ParamBean<Urine> paramBean) {
		Long count = urineService.insert(paramBean.getData());
		ResultDto<UrineDTO> result = urineService.searchAdvance(new Urine(), 0, 0);
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstUrine", result);
		return "pages/catalog/urine :: #refresh-section";
	}

	@PostMapping(value = "/edit", consumes = "application/json", produces = "application/json")
	public String edit(ModelMap map, HttpServletRequest request, @RequestBody ParamBean<Urine> paramBean) {
		Urine urine = paramBean.getData();
		Long count = urineService.edit(urine);
		String message = "";

		ResultDto<UrineDTO> result = urineService.searchAdvance(new Urine(), 0, 0);

		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else {
			message = "ERROR";
		}

		map.addAttribute("message", message);
		map.addAttribute("lstUrine", result);

		return "pages/catalog/urine :: #refresh-section";
	}

}
