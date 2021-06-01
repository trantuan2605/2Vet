package com.twovet.Navigation.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author namnv
 *
 */
@SuppressWarnings("serial")
public class MenuDTO implements Serializable{
	private int id;
	private String name;
	private String url;
	private int parentId;
	private String icon;
	private boolean activeClass;
	private boolean openClass;
	private List<MenuDTO> childs;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
	public boolean isActiveClass() {
		return activeClass;
	}
	public void setActiveClass(boolean activeClass) {
		this.activeClass = activeClass;
	}
	public boolean isOpenClass() {
		return openClass;
	}
	public void setOpenClass(boolean openClass) {
		this.openClass = openClass;
	}
	public List<MenuDTO> getChilds() {
		return childs;
	}
	public void setChilds(List<MenuDTO> childs) {
		this.childs = childs;
	}

}
