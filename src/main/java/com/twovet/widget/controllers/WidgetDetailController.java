package com.twovet.widget.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.twovet.base.common.BaseController;
import com.twovet.base.common.FunctionCommon;
import com.twovet.base.constant.ViewNameConstants;
import com.twovet.widget.dto.GroupItemDTO;
import com.twovet.widget.model.GroupItem;
import com.twovet.widget.services.implement.WidgetService;

@Controller
@RequestMapping("widget/detail")
public class WidgetDetailController extends BaseController{
	
	@Autowired
	private WidgetService widgetService;

	@Override
	public void setActiveLink(ServletRequestAttributes servletRequestAttributes, HttpSession httpSession) {
		new FunctionCommon(servletRequestAttributes, httpSession);
		
	}
	private List<Integer> lst3ndCodeInt;

	@Override
	public String initScreen(Model model, HttpSession httpSession) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		String group2ndCode = attr.getRequest().getParameter("group2ndCode");
		String groupName = attr.getRequest().getParameter("groupName");
		setActiveLink(attr, httpSession);
		model.addAttribute("title", groupName);
		model.addAttribute("titleRoot", "Sản phẩm - Dịch vụ");
		GroupItem groupItem = null;
		groupItem = widgetService.getGroupItem(group2ndCode);
		List<GroupItemDTO> lstGroupItem =  new ArrayList<>();
		lstGroupItem = widgetService.getLstGroup3ND(group2ndCode);
		if (lstGroupItem != null && !lstGroupItem.isEmpty()) {
			lst3ndCodeInt = lstGroupItem.stream()
					.map(e ->e.getLast3ndCodeInt())
					.collect(Collectors.toList());
		} else {
			lst3ndCodeInt = new ArrayList<Integer>();
			lst3ndCodeInt.add(0);
		}
		model.addAttribute("groupItem", groupItem != null ? groupItem : new GroupItem());
		model.addAttribute("lstGroupItem", lstGroupItem);
		model.addAttribute("groupItemDto", new GroupItemDTO());
		String legend = groupItem != null ? "Thông tin " + groupItem.getGroup2ndName() : "Thông tin";
		model.addAttribute("legend", legend);
		return ViewNameConstants.WIDGET_DETAIL;
	}
	
	@GetMapping("/showDetail")
	public String showDetail(ModelMap map, HttpServletRequest request) {
		String group3ndCode = request.getParameter("group3ndCode");
		String modeScreen = request.getParameter("mode");
		GroupItemDTO result = widgetService.getDetailGroup3nd(group3ndCode);
		map.addAttribute("groupItemDto", result);
		String legend = "Thông tin " + result.getGroup2ndName();
		map.addAttribute("legend", legend);
		return "edit".equals(modeScreen) ? "pages/widget/itemEdit :: #detailGroup3ndEdit" : "pages/widget/itemCrud :: #itemInfo";
	}
	
	@PostMapping(value = "/edit")
	public String edit(ModelMap map, HttpServletRequest request, @ModelAttribute GroupItem paramBean) {
		GroupItem group3ndParam = paramBean;
		Long count = widgetService.edit(group3ndParam, request);
		List<GroupItemDTO> lstGroupItem =  new ArrayList<>();
		lstGroupItem = widgetService.getLstGroup3ND(group3ndParam.getGroup2ndCode());
		GroupItem groupItem = null;
		groupItem = widgetService.getGroupItem(group3ndParam.getGroup2ndCode());
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else  {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstGroupItem", lstGroupItem);
		map.addAttribute("groupItem", groupItem != null ? groupItem : new GroupItem());
		return "pages/widget/detail :: #refresh-section";
	}
	
	@PostMapping(value = "/add")
	public String addNew(ModelMap map, HttpServletRequest request, @ModelAttribute GroupItem paramBean) {
		GroupItem group3ndParam = paramBean;
		String last3ndCode = FunctionCommon.getLastCodeVal(lst3ndCodeInt, group3ndParam.getGroup2ndCode());
		group3ndParam.setGroup3ndCode(last3ndCode);
		
		Long count = widgetService.insert(group3ndParam, request);
		List<GroupItemDTO> lstGroupItem =  new ArrayList<>();
		lstGroupItem = widgetService.getLstGroup3ND(group3ndParam.getGroup2ndCode());
		if (lstGroupItem != null && !lstGroupItem.isEmpty()) {
			lst3ndCodeInt = lstGroupItem.stream()
					.map(e ->e.getLast3ndCodeInt())
					.collect(Collectors.toList());
		} else {
			lst3ndCodeInt = new ArrayList<Integer>();
		}
		GroupItem groupItem = null;
		groupItem = widgetService.getGroupItem(group3ndParam.getGroup2ndCode());
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else  {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstGroupItem", lstGroupItem);
		map.addAttribute("groupItem", groupItem != null ? groupItem : new GroupItem());
		return "pages/widget/detail :: #refresh-section";
	}

	public List<Integer> getLst3ndCodeInt() {
		return lst3ndCodeInt;
	}

	public void setLst3ndCodeInt(List<Integer> lst3ndCodeInt) {
		this.lst3ndCodeInt = lst3ndCodeInt;
	}
	
}
