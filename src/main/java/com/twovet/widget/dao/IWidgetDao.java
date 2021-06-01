package com.twovet.widget.dao;

import java.util.List;

import com.twovet.widget.dto.AppGroupDTO;
import com.twovet.widget.dto.GroupItemDTO;
import com.twovet.widget.model.AppGroup;
import com.twovet.widget.model.GroupItem;

public interface IWidgetDao {

	AppGroup getGroupById(Long groupId);
	
	GroupItem getGroupItem(String groupCode);
	
	GroupItem getGroupItemBy1stCode(String group1stCode);
	
	GroupItemDTO getDetailGroup3nd(String group3ndCode);
	
	List<AppGroupDTO> getLstGroup1ST(int appGroupId);
	
	List<GroupItemDTO> getLstGroup2ND(String group1stCode);
	
	List<GroupItemDTO> getLstGroup3ND(String group2ndCode);
	
	List<AppGroupDTO> getAllGroup(int appGroupId);
	
	List<AppGroupDTO> getAllWidget();
	
	Long edit(GroupItem groupItem);
	
	Long insert(GroupItem groupItem);
	
	Long insert1st(GroupItem groupItem);
	
	Long insert2nd(GroupItem groupItem);
	
	List<GroupItemDTO> getLstGroup3NDByAppGr(String appGroup);
	
	List<GroupItemDTO> getAllLstGroup3NDForExam();
}
