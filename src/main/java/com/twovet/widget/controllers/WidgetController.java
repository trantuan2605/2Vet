package com.twovet.widget.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
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
import com.twovet.widget.dto.AppGroupDTO;
import com.twovet.widget.dto.GroupItemDTO;
import com.twovet.widget.model.GroupItem;
import com.twovet.widget.services.implement.WidgetService;

@Controller
@RequestMapping("widget")
public class WidgetController extends BaseController{
	
	@Autowired
	private WidgetService widgetService;

	@Override
	public void setActiveLink(ServletRequestAttributes servletRequestAttributes, HttpSession httpSession) {
		new FunctionCommon(servletRequestAttributes, httpSession);
		
	}
	
	private List<Integer> lstGroup1stCode;

	@Override
	public String initScreen(Model model, HttpSession httpSession) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		String contextRoot = httpSession.getServletContext().getContextPath();
		setActiveLink(attr, httpSession);
		String groupIdStr= attr.getRequest().getParameter("groupId");
		String groupName= attr.getRequest().getParameter("groupName");
		int groupId = 0;
		if (StringUtils.isNotEmpty(groupIdStr)) {
			groupId = Integer.parseInt(groupIdStr);
		}
		boolean isInsert1st = false;
		AppGroupDTO groupDto = widgetService.getGroupById(Long.valueOf(groupId));
		if (groupDto != null && groupDto.getId() != null) {
			isInsert1st = true;
			groupName = groupDto.getGroupName();
		}
		List<AppGroupDTO> lstGroup = new ArrayList<>();
		lstGroup = widgetService.getAllGroup(groupId);
		if (lstGroup != null && !lstGroup.isEmpty()) {
			lstGroup1stCode = lstGroup.stream()
					.map(e ->e.getLast1stCodeInt())
					.collect(Collectors.toList());
		} else {
			lstGroup1stCode = new ArrayList<Integer>();
			lstGroup1stCode.add(0);
		}
		model.addAttribute("title", groupName);
		model.addAttribute("titleRoot", "Sản phẩm - Dịch vụ");
		model.addAttribute("lstGroupItem", lstGroup);
		model.addAttribute("isInsert1st", isInsert1st);
		GroupItem groupItem = new GroupItem();
		groupItem.setGroupId(groupId);
		model.addAttribute("contextRoot", contextRoot);
		model.addAttribute("groupItem", groupItem);
		return ViewNameConstants.WIDGET_DASHBOARD;
	}
	
	@PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
	public String addNew(ModelMap map, HttpServletRequest request,@RequestBody ParamBean<GroupItem> paramBean) {
		GroupItem group1stParam = paramBean.getData();
		Long count = widgetService.insert1st(group1stParam, lstGroup1stCode);
		List<AppGroupDTO> lstGroup = new ArrayList<>();
		lstGroup = widgetService.getAllGroup(group1stParam.getGroupId());
		if (lstGroup != null && !lstGroup.isEmpty()) {
			lstGroup1stCode = lstGroup.stream()
					.map(e ->e.getLast1stCodeInt())
					.collect(Collectors.toList());
		} else {
			lstGroup1stCode = new ArrayList<Integer>();
			lstGroup1stCode.add(0);
		}
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else  {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstGroupItem", lstGroup);
		GroupItem groupItem = new GroupItem();
		groupItem.setGroupId(group1stParam.getGroupId());
		map.addAttribute("groupItem", groupItem);
		return "pages/widget/dashboard :: #refresh-dashboard";
	}
	
	@GetMapping("/show2ndPopup")
	public String show2ndPopup(ModelMap map, HttpServletRequest request) {
		String group1stCode = request.getParameter("group1stCode");
//		String modeScreen = request.getParameter("mode");
		GroupItem groupItem = new GroupItem();
		groupItem = widgetService.getGroupItemBy1stCode(group1stCode);
		map.addAttribute("groupItem", groupItem);
		return "pages/widget/group2ndAdd :: #group2ndAddDiv";
	}
	
	@PostMapping(value = "/addGroup2nd", consumes = "application/json", produces = "application/json")
	public String addGroup2nd(ModelMap map, HttpServletRequest request,@RequestBody ParamBean<GroupItem> paramBean) {
		GroupItem group2ndParam = paramBean.getData();
		List<AppGroupDTO> lstGroup = new ArrayList<>();
		List<Integer> lstGroup2ndCode = new ArrayList<>();
		List<GroupItemDTO> groupItemDtos = widgetService.getLstGroup2ND(group2ndParam.getGroup1stCode());
		if (groupItemDtos != null && !groupItemDtos.isEmpty()) {
			lstGroup2ndCode = groupItemDtos.stream()
					.map(e ->e.getLast2ndCodeInt())
					.collect(Collectors.toList());
		} else {
			lstGroup2ndCode = new ArrayList<Integer>();
			lstGroup2ndCode.add(0);
		}
		Long count = widgetService.insert2nd(group2ndParam, lstGroup2ndCode);
		lstGroup = widgetService.getAllGroup(group2ndParam.getGroupId());
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else  {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstGroupItem", lstGroup);
		GroupItem groupItem = new GroupItem();
		groupItem.setGroupId(group2ndParam.getGroupId());
		map.addAttribute("groupItem", groupItem);
		return "pages/widget/dashboard :: #refresh-dashboard";
	}

	public List<Integer> getLstGroup1stCode() {
		return lstGroup1stCode;
	}

	public void setLstGroup1stCode(List<Integer> lstGroup1stCode) {
		this.lstGroup1stCode = lstGroup1stCode;
	}

}
