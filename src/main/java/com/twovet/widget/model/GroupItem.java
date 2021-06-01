package com.twovet.widget.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "group_item")
@NamedQueries({ 
	@NamedQuery(name = "GroupItem.findAll", query = "SELECT gi FROM GroupItem gi WHERE 1=1 ORDER by gi.id desc"),
	@NamedQuery(name = "GroupItem.findGroupItemByCode", query = "SELECT gi FROM GroupItem gi WHERE gi.group2ndCode = :group2ndCode GROUP BY gi.group2ndCode"),
	@NamedQuery(name = "GroupItem.findGroupItem2nd", query = "SELECT gi FROM GroupItem gi WHERE gi.group1stCode = :group1stCode AND (gi.group2ndCode is null OR CHAR_LENGTH(gi.group2ndCode) = 0) GROUP BY gi.group1stCode"),
	})
public class GroupItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "group_1st_code")
	private String group1stCode;

	@Column(name = "app_group_id")
	private int groupId;

	@Column(name = "group_1st_name")
	private String group1stName;
	
	@Column(name = "group_2nd_code")
	private String group2ndCode;
	
	@Column(name = "group_2nd_name")
	private String group2ndName;
	
	@Column(name = "group_3nd_code")
	private String group3ndCode;
	
	@Column(name = "group_3nd_name")
	private String group3ndName;
	
	@Column(name = "item_icon")
	private String itemIcon;
	
	@Column(name = "item_spec_1")
	private String itemSpec1;
	
	@Column(name = "item_spec_2")
	private String itemSpec2;
	
	@Column(name = "item_spec_3")
	private String itemSpec3;
	
	@Column(name = "item_spec_4")
	private String itemSpec4;
	
	@Column(name = "item_spec_5")
	private String itemSpec5;
	
	@Column(name = "item_spec_6")
	private String itemSpec6;
	
	@Column(name = "item_spec_7")
	private String itemSpec7;
	
	@Column(name = "item_spec_8")
	private String itemSpec8;

	@Column(name = "item_spec_9")
	private String itemSpec9;
	
	@Column(name = "item_spec_10")
	private String itemSpec10;
	
	@Column(name = "DESCRIPT")
	private String descript;
	
	@Column(name = "PATH")
	private String path;
	
	@Transient
	private MultipartFile fileImage;

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

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
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

	public String getGroup3ndName() {
		return group3ndName;
	}

	public void setGroup3ndName(String group3ndName) {
		this.group3ndName = group3ndName;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public MultipartFile getFileImage() {
		return fileImage;
	}

	public void setFileImage(MultipartFile fileImage) {
		this.fileImage = fileImage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
