package com.twovet.widget.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.twovet.base.common.BaseController;
import com.twovet.base.common.FunctionCommon;
import com.twovet.base.constant.ViewNameConstants;
import com.twovet.widget.dto.AppGroupDTO;
import com.twovet.widget.services.implement.WidgetService;

@Controller
@RequestMapping("widget/master")
public class WidgetMasterController extends BaseController{
	
	@Autowired
	private WidgetService widgetService;

	@Override
	public void setActiveLink(ServletRequestAttributes servletRequestAttributes, HttpSession httpSession) {
		new FunctionCommon(servletRequestAttributes, httpSession);
		
	}

	@Override
	public String initScreen(Model model, HttpSession httpSession) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		String contextRoot = httpSession.getServletContext().getContextPath();
		setActiveLink(attr, httpSession);
		List<AppGroupDTO> lstGroup = new ArrayList<>();
		lstGroup = widgetService.getAllWidget();
		model.addAttribute("title", "Sản phẩm - Dịch vụ");
		model.addAttribute("lstGroupItem", lstGroup);
		model.addAttribute("contextRoot", contextRoot);
		return ViewNameConstants.WIDGET_MASTER;
	}

}
