package com.twovet.Navigation.dao;

import java.util.List;

import com.twovet.Navigation.dto.MenuDTO;
import com.twovet.Navigation.model.Menu;

public interface IMenuDao{
	public List<MenuDTO> getMenuNavigators();
	public List<MenuDTO> getAllMenu();
	
}
