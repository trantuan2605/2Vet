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
import com.twovet.catalog.dto.SputumDTO;
import com.twovet.catalog.model.Sputum;
import com.twovet.catalog.services.implement.SputumService;

@Controller
@RequestMapping("catalog/sputum")
public class SputumController extends BaseController {
	@Autowired
	SputumService sputumService;

	@Override
	public void setActiveLink(ServletRequestAttributes servletRequestAttributes, HttpSession httpSession) {
		new FunctionCommon(servletRequestAttributes, httpSession);

	}

	@Override
	public String initScreen(Model model, HttpSession httpSession) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		setActiveLink(attr, httpSession);
		model.addAttribute("title", "Danh mục Xét nghiệm dịch đờm");

		ResultDto<SputumDTO> result = sputumService.searchAdvance(new Sputum(), 0, 0);
		result.setCurrentPage(1);
		result.setMaxSize(size);

		model.addAttribute("sputumDto", new SputumDTO());
		model.addAttribute("sputum", new Sputum());
		model.addAttribute("lstSputum", result);

		return ViewNameConstants.CATALOG_SPUTUM;
	}


	@GetMapping("/showDetail")
	public String showDetail(ModelMap map, HttpServletRequest request) {
		String sputumCode = request.getParameter("sputumCode");
		String modeScreen = request.getParameter("mode");
		SputumDTO result = sputumService.getDetailSputum(sputumCode);
		map.addAttribute("sputumDto", result);
		return "edit".equals(modeScreen) ? "pages/catalog/sputumEdit :: #detailSputumEdit": "pages/catalog/sputumCrud :: #detailSputum";
	}

	@PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
	public String addNew(ModelMap map, HttpServletRequest request, @RequestBody ParamBean<Sputum> paramBean) {
		Long count = sputumService.insert(paramBean.getData());
		ResultDto<SputumDTO> result = sputumService.searchAdvance(new Sputum(), 0, 0);
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstSputum", result);
		return "pages/catalog/sputum :: #refresh-section";
	}

	@PostMapping(value = "/edit", consumes = "application/json", produces = "application/json")
	public String edit(ModelMap map, HttpServletRequest request, @RequestBody ParamBean<Sputum> paramBean) {
		Sputum sputum = paramBean.getData();
		Long count = sputumService.edit(sputum);
		String message = "";

		ResultDto<SputumDTO> result = sputumService.searchAdvance(new Sputum(), 0, 0);

		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else {
			message = "ERROR";
		}

		map.addAttribute("message", message);
		map.addAttribute("lstSputum", result);

		return "pages/catalog/sputum :: #refresh-section";
	}

}
