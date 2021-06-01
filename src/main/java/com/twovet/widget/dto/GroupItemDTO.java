package com.twovet.widget.dto;

import java.math.BigInteger;

public class GroupItemDTO {

	private Long id;
	private String group1stCode;
	private String group1stName;
	private String group2ndCode;
	private String group2ndName;
	private String group3ndCode;
	private String group3ndName;
	private int appGroupId;
	private String itemIcon;
	private String itemSpec1;
	private String itemSpec2;
	private String itemSpec3;
	private String itemSpec4;
	private String itemSpec5;
	private String itemSpec6;
	private String itemSpec7;
	private String itemSpec8;
	private String itemSpec9;
	private String itemSpec10;
	private String descript;
	private int last3ndCodeInt;
	private int last2ndCodeInt;
	private String path;

	public GroupItemDTO() {
		// TODO Auto-generated constructor stub
	}
	
	// constructor for show groupItem on screen widget dashboard
//	public GroupItemDTO(Integer id, String groupCode, Integer appGroupId, String groupItemName, String groupName, String itemIcon) {
//		this.id = Long.valueOf(id);
//		this.groupCode = groupCode;
//		this.appGroupId = appGroupId;
//		this.groupItemName = groupItemName;
//		this.groupName = groupName;
//		this.itemIcon = itemIcon;
//	}
//	
//	// constructor for show detail item on screen widget/detail
//	public GroupItemDTO(String itemCode, String itemName, String itemSpec1, String itemSpec2, String itemSpec3, String itemSpec4,  
//			String itemSpec5, String itemSpec6, String itemSpec7, String itemSpec8, String itemSpec9, String itemSpec10, String descript) {
//		this.itemCode = itemCode;
//		this.itemName = itemName;
//		this.itemSpec1 = itemSpec1;
//		this.itemSpec2 = itemSpec2;
//		this.itemSpec3 = itemSpec3;
//		this.itemSpec4 = itemSpec4;
//		this.itemSpec5 = itemSpec5;
//		this.itemSpec6 = itemSpec6;
//		this.itemSpec7 = itemSpec7;
//		this.itemSpec8 = itemSpec8;
//		this.itemSpec9 = itemSpec9;
//		this.itemSpec10 = itemSpec10;
//		this.descript = descript;
//	}
//	
//	// constructor for show itemCrud screen
//	public GroupItemDTO(Integer appGroupId, String groupName, String groupCode, String groupItemName, String itemCode,
//			String itemName, String itemSpec1, String itemSpec2, String itemSpec3, String itemSpec4, String itemSpec5,
//			String itemSpec6, String itemSpec7, String itemSpec8, String itemSpec9, String itemSpec10, String descript) {
//		this.appGroupId = appGroupId;
//		this.groupName = groupName;
//		this.groupCode = groupCode;
//		this.groupItemName = groupItemName;
//		this.itemCode = itemCode;
//		this.itemName = itemName;
//		this.itemSpec1 = itemSpec1;
//		this.itemSpec2 = itemSpec2;
//		this.itemSpec3 = itemSpec3;
//		this.itemSpec4 = itemSpec4;
//		this.itemSpec5 = itemSpec5;
//		this.itemSpec6 = itemSpec6;
//		this.itemSpec7 = itemSpec7;
//		this.itemSpec8 = itemSpec8;
//		this.itemSpec9 = itemSpec9;
//		this.itemSpec10 = itemSpec10;
//		this.descript = descript;
//	}
	
	// Constructor for group2nd in add 2nd screen
	public GroupItemDTO(String group1stCode, String group1stName, String group2ndCode, String group2ndName, BigInteger last2ndCodeInt) {
		this.group1stCode = group1stCode;
		this.group1stName = group1stName;
		this.group2ndCode = group2ndCode;
		this.group2ndName = group2ndName;
		this.last2ndCodeInt = last2ndCodeInt.intValue();
	}
	
	// Constructor for group2nd
	public GroupItemDTO(String group1stCode, String group1stName, String group2ndCode, String group2ndName) {
		this.group1stCode = group1stCode;
		this.group1stName = group1stName;
		this.group2ndCode = group2ndCode;
		this.group2ndName = group2ndName;
	}
	
	// Constructor for list group3nd
	public GroupItemDTO(String group1stCode, String group1stName, String group2ndCode, String group2ndName,
			String group3ndCode, String group3ndName, String itemSpec1, String itemSpec2, String itemSpec3, 
			String itemSpec4, String itemSpec5, String itemSpec6, String itemSpec7, String itemSpec8,
			String itemSpec9, String itemSpec10, String descript, Integer appGroupId, BigInteger last3ndCodeInt) {
		this.group1stCode = group1stCode;
		this.group1stName = group1stName;
		this.group2ndCode = group2ndCode;
		this.group2ndName = group2ndName;
		this.group3ndCode = group3ndCode;
		this.group3ndName = group3ndName;
		this.itemSpec1 = itemSpec1;
		this.itemSpec2 = itemSpec2;
		this.itemSpec3 = itemSpec3;
		this.itemSpec4 = itemSpec4;
		this.itemSpec5 = itemSpec5;
		this.itemSpec6 = itemSpec6;
		this.itemSpec7 = itemSpec7;
		this.itemSpec8 = itemSpec8;
		this.itemSpec9 = itemSpec9;
		this.itemSpec10 = itemSpec10;
		this.descript = descript;
		this.appGroupId = appGroupId;
		this.last3ndCodeInt =last3ndCodeInt.intValue();
	}
	
	// Constructor for get detail group3nd
	public GroupItemDTO(Integer id, Integer appGroupId, String group1stCode, String group1stName, String group2ndCode,
			String group2ndName, String group3ndCode, String group3ndName, String itemSpec1, String itemSpec2, String itemSpec3, 
			String itemSpec4, String itemSpec5, String itemSpec6, String itemSpec7, String itemSpec8,
			String itemSpec9, String itemSpec10, String descript, String path) {
		this.id = Long.valueOf(id);
		this.appGroupId = appGroupId;
		this.group1stCode = group1stCode;
		this.group1stName = group1stName;
		this.group2ndCode = group2ndCode;
		this.group2ndName = group2ndName;
		this.group3ndCode = group3ndCode;
		this.group3ndName = group3ndName;
		this.itemSpec1 = itemSpec1;
		this.itemSpec2 = itemSpec2;
		this.itemSpec3 = itemSpec3;
		this.itemSpec4 = itemSpec4;
		this.itemSpec5 = itemSpec5;
		this.itemSpec6 = itemSpec6;
		this.itemSpec7 = itemSpec7;
		this.itemSpec8 = itemSpec8;
		this.itemSpec9 = itemSpec9;
		this.itemSpec10 = itemSpec10;
		this.descript = descript;
		this.descript = descript;
		this.path = path;
	}
	
	// Constructor for get all list item 4 Exam
	public GroupItemDTO(String group1stCode, String group1stName, String group2ndCode, String group2ndName,
			String group3ndCode, String group3ndName, String descript, Integer appGroupId) {
		this.group1stCode = group1stCode;
		this.group1stName = group1stName;
		this.group2ndCode = group2ndCode;
		this.group2ndName = group2ndName;
		this.group3ndCode = group3ndCode;
		this.group3ndName = group3ndName;
		this.descript = descript;
		this.appGroupId = appGroupId;
	}
	

	public GroupItemDTO(String group3ndCode, String group3ndName) {
		super();
		this.group3ndCode = group3ndCode;
		this.group3ndName = group3ndName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroup1stCode() {
		return group1stCode;
	}

	public void setGroup1stCode(String group1stCode) {
		this.group1stCode = group1stCode;
	}

	public String getGroup1stName() {
		return group1stName;
	}

	public void setGroup1stName(String group1stName) {
		this.group1stName = group1stName;
	}

	public String getGroup2ndCode() {
		return group2ndCode;
	}

	public void setGroup2ndCode(String group2ndCode) {
		this.group2ndCode = group2ndCode;
	}

	public String getGroup2ndName() {
		return group2ndName;
	}

	public void setGroup2ndName(String group2ndName) {
		this.group2ndName = group2ndName;
	}

	public String getGroup3ndCode() {
		return group3ndCode;
	}

	public void setGroup3ndCode(String group3ndCode) {
		this.group3ndCode = group3ndCode;
	}

	public String getGroup3ndName() {
		return group3ndName;
	}

	public void setGroup3ndName(String group3ndName) {
		this.group3ndName = group3ndName;
	}

	public int getAppGroupId() {
		return appGroupId;
	}

	public void setAppGroupId(int appGroupId) {
		this.appGroupId = appGroupId;
	}

	public String getItemIcon() {
		return itemIcon;
	}

	public void setItemIcon(String itemIcon) {
		this.itemIcon = itemIcon;
	}

	public String getItemSpec1() {
		return itemSpec1;
	}

	public void setItemSpec1(String itemSpec1) {
		this.itemSpec1 = itemSpec1;
	}

	public String getItemSpec2() {
		return itemSpec2;
	}

	public void setItemSpec2(String itemSpec2) {
		this.itemSpec2 = itemSpec2;
	}

	public String getItemSpec3() {
		return itemSpec3;
	}

	public void setItemSpec3(String itemSpec3) {
		this.itemSpec3 = itemSpec3;
	}

	public String getItemSpec4() {
		return itemSpec4;
	}

	public void setItemSpec4(String itemSpec4) {
		this.itemSpec4 = itemSpec4;
	}

	public String getItemSpec5() {
		return itemSpec5;
	}

	public void setItemSpec5(String itemSpec5) {
		this.itemSpec5 = itemSpec5;
	}

	public String getItemSpec6() {
		return itemSpec6;
	}

	public void setItemSpec6(String itemSpec6) {
		this.itemSpec6 = itemSpec6;
	}

	public String getItemSpec7() {
		return itemSpec7;
	}

	public void setItemSpec7(String itemSpec7) {
		this.itemSpec7 = itemSpec7;
	}

	public String getItemSpec8() {
		return itemSpec8;
	}

	public void setItemSpec8(String itemSpec8) {
		this.itemSpec8 = itemSpec8;
	}

	public String getItemSpec9() {
		return itemSpec9;
	}

	public void setItemSpec9(String itemSpec9) {
		this.itemSpec9 = itemSpec9;
	}

	public String getItemSpec10() {
		return itemSpec10;
	}

	public void setItemSpec10(String itemSpec10) {
		this.itemSpec10 = itemSpec10;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public int getLast3ndCodeInt() {
		return last3ndCodeInt;
	}

	public void setLast3ndCodeInt(int last3ndCodeInt) {
		this.last3ndCodeInt = last3ndCodeInt;
	}

	public int getLast2ndCodeInt() {
		return last2ndCodeInt;
	}

	public void setLast2ndCodeInt(int last2ndCodeInt) {
		this.last2ndCodeInt = last2ndCodeInt;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
