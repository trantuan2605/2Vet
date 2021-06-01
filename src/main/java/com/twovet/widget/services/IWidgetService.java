package com.twovet.widget.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.twovet.widget.dto.AppGroupDTO;
import com.twovet.widget.dto.GroupItemDTO;
import com.twovet.widget.model.GroupItem;

public interface IWidgetService {

	AppGroupDTO getGroupById(Long groupId);
	
	List<AppGroupDTO> getAllWidget();
	
	GroupItem getGroupItem(String groupCode);
	
	GroupItem getGroupItemBy1stCode(String group1stCode);
	
	GroupItemDTO getDetailGroup3nd(String group3ndCode);
	
	List<AppGroupDTO> getLstGroup1ST(int appGroupId);
	
	List<GroupItemDTO> getLstGroup2ND(String group1stCode);
	
	List<GroupItemDTO> getLstGroup3ND(String group2ndCode);
	
	List<AppGroupDTO> getAllGroup(int appGroupId);
	
	Long edit(GroupItem groupItem, HttpServletRequest request);
	
	Long insert(GroupItem groupItem, HttpServletRequest request);
	
	Long insert1st(GroupItem groupItem, List<Integer> lstGroup1stCode);
	
	Long insert2nd(GroupItem groupItem, List<Integer> lstGroup2ndCode);
	
	List<GroupItemDTO> getLstGroup3NDByAppGr(String appGroup);
	
	List<GroupItemDTO> getAllLstGroup3NDForExam();
}
