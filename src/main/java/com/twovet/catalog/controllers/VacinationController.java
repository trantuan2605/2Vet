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
import com.twovet.catalog.dto.VacinationDTO;
import com.twovet.catalog.model.Vacination;
import com.twovet.catalog.services.implement.VacinationService;

@Controller
@RequestMapping("catalog/vacination")
public class VacinationController extends BaseController{
	@Autowired
	private VacinationService vacineService;

	@Override
	public String initScreen(Model model, HttpSession session) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		setActiveLink(attr, session);
		model.addAttribute("title", "Danh mục tiêm phòng");
		List<VacinationDTO> lstVacination = vacineService.getAllVacinations();
		model.addAttribute("lstVacination", lstVacination);
		model.addAttribute("vacineDto", new VacinationDTO());
		model.addAttribute("vacine", new Vacination());
		return ViewNameConstants.CATALOG_VACINATION;
	}

	@Override
	public void setActiveLink(ServletRequestAttributes servletRequestAttributes, HttpSession httpSession) {
		// TODO Auto-generated method stub
		new FunctionCommon(servletRequestAttributes, httpSession);
	}
	
	@GetMapping("/showDetail")
	public String showDetail(ModelMap map, HttpServletRequest request) {
		String vacineCode = request.getParameter("vacineCode");
		String modeScreen = request.getParameter("mode");
		VacinationDTO result = vacineService.getDetaiVacinaton(vacineCode);
		map.addAttribute("vacineDto", result);
		return "edit".equals(modeScreen) ? "pages/catalog/vacinationEdit :: #detailVacineEdit" : "pages/catalog/vacinationCrud :: #detailVacine";
	}
	
	@PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
	public String addNew(ModelMap map, HttpServletRequest request,@RequestBody ParamBean<Vacination> paramBean) {
		Long count = vacineService.insert(paramBean.getData());
		List<VacinationDTO> lstVacination = vacineService.getAllVacinations();
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else  {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstVacination", lstVacination);
		return "pages/catalog/vacination :: #refresh-section";
	}
	
	@PostMapping(value = "/edit", consumes = "application/json", produces = "application/json")
	public String edit(ModelMap map, HttpServletRequest request,@RequestBody ParamBean<Vacination> paramBean) {
		Vacination vacine = paramBean.getData();
		Long count = vacineService.edit(vacine);
		List<VacinationDTO> lstVacination = vacineService.getAllVacinations();
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else  {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstVacination", lstVacination);
		return "pages/catalog/vacination :: #refresh-section";
	}
}
