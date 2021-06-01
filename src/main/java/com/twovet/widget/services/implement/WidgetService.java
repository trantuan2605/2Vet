package com.twovet.widget.services.implement;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.twovet.base.bean.TwoVetProperties;
import com.twovet.base.common.FileUploadUtil;
import com.twovet.base.common.FunctionCommon;
import com.twovet.widget.dao.implement.WidgetDao;
import com.twovet.widget.dto.AppGroupDTO;
import com.twovet.widget.dto.GroupItemDTO;
import com.twovet.widget.model.AppGroup;
import com.twovet.widget.model.GroupItem;
import com.twovet.widget.services.IWidgetService;

@Service
public class WidgetService implements IWidgetService{
	@Autowired
	private WidgetDao widgetDao;

	@Override
	public GroupItem getGroupItem(String groupCode) {
		// TODO Auto-generated method stub
		return widgetDao.getGroupItem(groupCode);
	}

	@Override
	public GroupItemDTO getDetailGroup3nd(String group3ndCode) {
		// TODO Auto-generated method stub
		return widgetDao.getDetailGroup3nd(group3ndCode);
	}

	@Override
	public List<AppGroupDTO> getLstGroup1ST(int appGroupId) {
		// TODO Auto-generated method stub
		return widgetDao.getLstGroup1ST(appGroupId);
	}

	@Override
	public List<GroupItemDTO> getLstGroup2ND(String group1stCode) {
		// TODO Auto-generated method stub
		return widgetDao.getLstGroup2ND(group1stCode);
	}

	@Override
	public List<GroupItemDTO> getLstGroup3ND(String group2ndCode) {
		// TODO Auto-generated method stub
		return widgetDao.getLstGroup3ND(group2ndCode);
	}

	@Override
	public List<AppGroupDTO> getAllWidget() {
		// TODO Auto-generated method stub
		return widgetDao.getAllWidget();
	}

	@Override
	public List<AppGroupDTO> getAllGroup(int appGroupId) {
		// TODO Auto-generated method stub
		return widgetDao.getAllGroup(appGroupId);
	}

	@Override
	public Long edit(GroupItem groupItem, HttpServletRequest request) {
		if (groupItem.getGroupId() ==2 && 
				groupItem.getFileImage().getSize() > 0) {
			MultipartFile multipartFile = groupItem.getFileImage();
			String path = FunctionCommon.uploadAndGetPath(multipartFile, request);
			groupItem.setPath(path);
		}
		return widgetDao.edit(groupItem);
	}

	@Override
	public Long insert(GroupItem groupItem, HttpServletRequest request) {
		// TODO Auto-generated method stub
		// upload image
		if (groupItem.getGroupId() == 2 && 
				groupItem.getFileImage().getSize() > 0) {
			String path = FunctionCommon.uploadAndGetPath(groupItem.getFileImage(), request);
			groupItem.setPath(path);
		}
		return widgetDao.insert(groupItem);
	}

	@Override
	public Long insert1st(GroupItem groupItem, List<Integer> lstGroup1stCode) {
		String charac1stCode = "";
		switch (groupItem.getGroupId()) {
		case 1:
			charac1stCode = "2VDV";
			break;
		case 2:
			charac1stCode = "2VHH";
			break;
		case 3:
			charac1stCode = "2VTH";
			break;
		default:
			break;
		}
		String format = "%02d";
		String last1stCode = FunctionCommon.getLastCodeVal(lstGroup1stCode, charac1stCode, format);
		groupItem.setGroup1stCode(last1stCode);
		return widgetDao.insert1st(groupItem);
	}

	@Override
	public GroupItem getGroupItemBy1stCode(String group1stCode) {
		// TODO Auto-generated method stub
		return widgetDao.getGroupItemBy1stCode(group1stCode);
	}

	@Override
	public Long insert2nd(GroupItem groupItem, List<Integer> lstGroup2ndCode) {
		String charac2ndCode = groupItem.getGroup1stCode();
		String format = "%02d";
		String last2ndCode = FunctionCommon.getLastCodeVal(lstGroup2ndCode, charac2ndCode, format);
		groupItem.setGroup2ndCode(last2ndCode);
		return widgetDao.insert2nd(groupItem);
	}

	@Override
	public AppGroupDTO getGroupById(Long groupId) {
		// TODO Auto-generated method stub
		AppGroup appGroup = widgetDao.getGroupById(groupId);
		ModelMapper mm = new ModelMapper();
		AppGroupDTO appGroupDto = mm.map(appGroup, AppGroupDTO.class);
		return appGroupDto;
	}

	@Override
	public List<GroupItemDTO> getLstGroup3NDByAppGr(String appGroup) {
		return widgetDao.getLstGroup3NDByAppGr(appGroup);
	}

	@Override
	public List<GroupItemDTO> getAllLstGroup3NDForExam() {
		return widgetDao.getAllLstGroup3NDForExam();
	}

}
