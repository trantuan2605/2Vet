package com.twovet.widget.dto;

import java.math.BigInteger;
import java.util.List;

public class AppGroupDTO {
	private Long id;
	private String groupName;
	private String group1stCode;
	private int last1stCodeInt;
	private List<GroupItemDTO> lstGroupItemDTO;
	private int sizeRows;
	
	public AppGroupDTO() {
		
	}
	
	// constructor for get group1st
	public AppGroupDTO(String group1stCode, Integer groupId, String group1stName, BigInteger last1stCodeInt) {
		this.group1stCode = group1stCode;
		this.id = Long.valueOf(groupId);
		this.groupName = group1stName;
		this.last1stCodeInt = last1stCodeInt.intValue();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<GroupItemDTO> getLstGroupItemDTO() {
		return lstGroupItemDTO;
	}

	public void setLstGroupItemDTO(List<GroupItemDTO> lstGroupItemDTO) {
		this.lstGroupItemDTO = lstGroupItemDTO;
	}

	public int getSizeRows() {
		return sizeRows;
	}

	public void setSizeRows(int sizeRows) {
		this.sizeRows = sizeRows;
	}

	public String getGroup1stCode() {
		return group1stCode;
	}

	public void setGroup1stCode(String group1stCode) {
		this.group1stCode = group1stCode;
	}

	public int getLast1stCodeInt() {
		return last1stCodeInt;
	}

	public void setLast1stCodeInt(int last1stCodeInt) {
		this.last1stCodeInt = last1stCodeInt;
	}
	

}
