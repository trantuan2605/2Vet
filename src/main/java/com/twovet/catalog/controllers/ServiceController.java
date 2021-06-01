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
import com.twovet.catalog.dto.ServiceDTO;
import com.twovet.catalog.model.Services;
import com.twovet.catalog.services.implement.ServiceService;

@Controller
@RequestMapping("catalog/service")
public class ServiceController extends BaseController{
	@Autowired
	private ServiceService service;

	@Override
	public String initScreen(Model model, HttpSession session) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		setActiveLink(attr, session);
		model.addAttribute("title", "Danh mục dịch vụ");
		List<ServiceDTO> lstService = service.getAllSevices(null);
		model.addAttribute("lstService", lstService);
		model.addAttribute("serviceDto", new ServiceDTO());
		model.addAttribute("service", new Services());
		return ViewNameConstants.CATALOG_SERVICE;
	}

	@Override
	public void setActiveLink(ServletRequestAttributes servletRequestAttributes, HttpSession httpSession) {
		// TODO Auto-generated method stub
		new FunctionCommon(servletRequestAttributes, httpSession);
	}
	
	@GetMapping("/showDetail")
	public String showDetail(ModelMap map, HttpServletRequest request) {
		String serviceCode = request.getParameter("serviceCode");
		String modeScreen = request.getParameter("mode");
		ServiceDTO result = service.getDetailService(serviceCode);
		map.addAttribute("serviceDto", result);
		return "edit".equals(modeScreen) ? "pages/catalog/serviceEdit :: #detailServiceEdit" : "pages/catalog/serviceCrud :: #detailService";
	}
	
	@PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
	public String addNew(ModelMap map, HttpServletRequest request,@RequestBody ParamBean<Services> paramBean) {
		Long count = service.insert(paramBean.getData());
		List<ServiceDTO> lstService = service.getAllSevices(null);
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else  {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstService", lstService);
		return "pages/catalog/services :: #refresh-section";
	}
	
	@PostMapping(value = "/edit", consumes = "application/json", produces = "application/json")
	public String edit(ModelMap map, HttpServletRequest request,@RequestBody ParamBean<Services> paramBean) {
		Services serviceParam = paramBean.getData();
		Long count = service.edit(serviceParam);
		List<ServiceDTO> lstService = service.getAllSevices(null);
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else  {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstService", lstService);
		return "pages/catalog/services :: #refresh-section";
	}
}
