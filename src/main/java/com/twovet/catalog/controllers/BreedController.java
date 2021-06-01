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
import com.twovet.catalog.model.Breed;
import com.twovet.catalog.services.implement.BreedService;

@Controller
@RequestMapping("catalog/breed")

public class BreedController extends BaseController {
	@Autowired
	BreedService breedService;
	private List<BreedDTO> lstBreedSeqCode; 

	@Override
	public void setActiveLink(ServletRequestAttributes servletRequestAttributes, HttpSession httpSession) {
		new FunctionCommon(servletRequestAttributes, httpSession);

	}

	@Override
	public String initScreen(Model model, HttpSession session) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		setActiveLink(attr, session);
		model.addAttribute("title", "Danh mục Loài");

		ResultDto<BreedDTO> result = breedService.searchAdvance(new Breed(), start, size);
		result.setCurrentPage(1);
		result.setMaxSize(size);
		model.addAttribute("breedDto", new BreedDTO());
		Breed breed = new Breed();
		model.addAttribute("breed", new Breed());
		model.addAttribute("lstBreed", result);
		if (result.getDatas() != null && !result.getDatas().isEmpty()) {
			lstBreedSeqCode = result.getDatas();
		} else {
			lstBreedSeqCode = new ArrayList<>();
		}
		return ViewNameConstants.CATALOG_BREED;
	}
	
	@PostMapping(value = "/searchAdvance", consumes = "application/json", produces = "application/json")
	public String searchAdvance(ModelMap map, HttpServletRequest request, @RequestBody ParamBean<Breed> paramBean) {
		String currentPageStr = paramBean.getCurrentPage();
		Breed breed = paramBean.getData();
		int currentPage = currentPageStr != null ? Integer.valueOf(currentPageStr) : 1;
		ResultDto<BreedDTO> result = breedService.searchAdvance(breed, currentPage, size);
		result.setCurrentPage(Integer.valueOf(currentPage));
		result.setMaxSize(size);
		map.addAttribute("lstBreed", result);
		return "pages/catalog/breed :: #refresh-section";
	}
	
	@GetMapping("/showDetail")
	public String showDetail(ModelMap map, HttpServletRequest request) {
		String breedCode = request.getParameter("breedCode");
		String modeScreen = request.getParameter("mode");
		BreedDTO result = breedService.getDetailBreed(breedCode);
		map.addAttribute("breedDto", result);
		return "edit".equals(modeScreen) ? "pages/catalog/breedEdit :: #detailBreedEdit" : "pages/catalog/breedCrud :: #detailBreed";
	}
	
	@PostMapping(value = "/add")
	public String addNew(ModelMap map, HttpServletRequest request, @ModelAttribute Breed paramBean) {
		Long count = breedService.insert(paramBean, lstBreedSeqCode, request);
		//SearchAdvanceParamBean<Breed> advanceParam = paramBean.getAdvanceParam();
		ResultDto<BreedDTO> result = breedService.searchAdvance(new Breed(), 0, 0);
		result.setCurrentPage(1);
		result.setMaxSize(size);
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
			lstBreedSeqCode = result.getDatas();
		} else  {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstBreed", result);
		return "pages/catalog/breed :: #refresh-section";
	}
	
	@PostMapping(value = "/edit")
	public String edit(ModelMap map, HttpServletRequest request,@ModelAttribute Breed paramBean) {
		Breed breed = paramBean;
		Long count = breedService.edit(breed, request);
		ResultDto<BreedDTO> result = breedService.searchAdvance(new Breed(), 0, 0);
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else  {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstBreed", result);
		return "pages/catalog/breed :: #refresh-section";
	}

}
