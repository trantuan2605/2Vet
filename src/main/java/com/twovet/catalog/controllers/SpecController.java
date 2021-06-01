package com.twovet.catalog.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.twovet.catalog.dto.BreedDTO;
import com.twovet.catalog.dto.PetDTO;
import com.twovet.catalog.dto.SpecDTO;
import com.twovet.catalog.model.Pet;
import com.twovet.catalog.model.Spec;
import com.twovet.catalog.services.implement.BreedService;
import com.twovet.catalog.services.implement.SpecService;

@Controller
@RequestMapping("catalog/spec")

public class SpecController extends BaseController {
	@Autowired
	private SpecService specService;
	@Autowired
	private BreedService breedService;
	
	private List<BreedDTO> lstBreed;
	private List<SpecDTO> lstSpecSeqCode; 

	@Override
	public void setActiveLink(ServletRequestAttributes servletRequestAttributes, HttpSession httpSession) {
		new FunctionCommon(servletRequestAttributes, httpSession);

	}

	@Override
	public String initScreen(Model model, HttpSession session) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		setActiveLink(attr, session);
		model.addAttribute("title", "Danh mục Giống");
		ResultDto<SpecDTO> result = specService.searchAdvance(new Spec(), start, size);
		result.setCurrentPage(1);
		result.setMaxSize(size);
		model.addAttribute("lstSpec", result);
		if (result.getDatas() != null && !result.getDatas().isEmpty()) {
			lstSpecSeqCode = result.getDatas();
		} else {
			lstSpecSeqCode = new ArrayList<>();
		}
		model.addAttribute("specDto", new SpecDTO());
		model.addAttribute("spec", new Spec());
		lstBreed = breedService.getAll();
		model.addAttribute("lstBreed", lstBreed);
		return ViewNameConstants.CATALOG_SPEC;
	}
	
	@PostMapping(value = "/searchAdvance", consumes = "application/json", produces = "application/json")
	public String searchAdvance(ModelMap map, HttpServletRequest request, @RequestBody ParamBean<Spec> paramBean) {
		String currentPageStr = paramBean.getCurrentPage();
		Spec spec = paramBean.getData();
		int currentPage = currentPageStr != null ? Integer.valueOf(currentPageStr) : 1;
		ResultDto<SpecDTO> result = specService.searchAdvance(spec, currentPage, size);
		result.setCurrentPage(Integer.valueOf(currentPage));
		result.setMaxSize(size);
		map.addAttribute("lstSpec", result);
		return "pages/catalog/spec :: #refresh-section";
	}
	
	@GetMapping("/showDetail")
	public String showDetail(ModelMap map, HttpServletRequest request) {
		String specCode = request.getParameter("specCode");
		String modeScreen = request.getParameter("mode");
		SpecDTO result = specService.getDetailSpec(specCode);
		map.addAttribute("specDto", result);
		map.addAttribute("lstBreed", lstBreed);
		return "edit".equals(modeScreen) ? "pages/catalog/specEdit :: #detailSpecEdit" : "pages/catalog/specCrud :: #detailSpec";
	}
	
	@PostMapping(value = "/add")
	public String addNew(ModelMap map, HttpServletRequest request,@ModelAttribute Spec paramBean) {
		Long count = specService.insert(paramBean, lstSpecSeqCode, request);
		ResultDto<SpecDTO> result = specService.searchAdvance(new Spec(), 0, 0);
		result.setCurrentPage(1);
		result.setMaxSize(size);
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
			lstSpecSeqCode = result.getDatas();
		} else  {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstSpec", result);
		return "pages/catalog/spec :: #refresh-section";
	}
	
	@PostMapping(value = "/edit")
	public String edit(ModelMap map, HttpServletRequest request,@ModelAttribute Spec paramBean) {
		Spec spec = paramBean;
		Long count = specService.edit(spec, request);
		ResultDto<SpecDTO> result = specService.searchAdvance(new Spec(), 0, 0);
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else  {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstSpec", result);
		return "pages/catalog/spec :: #refresh-section";
	}

}
