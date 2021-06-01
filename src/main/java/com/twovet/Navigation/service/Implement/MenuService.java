package com.twovet.Navigation.service.Implement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twovet.Navigation.dao.Implement.MenuDao;
import com.twovet.Navigation.dto.MenuDTO;
import com.twovet.Navigation.service.IMenuService;

@Service
public class MenuService implements IMenuService{
	@Autowired
	private MenuDao menuDao;

	@Override
	public List<MenuDTO> getAllMenu() {
		List<MenuDTO> lstMenu = menuDao.getAllMenu();
		List<MenuDTO> lstMenuTmp = menuDao.getAllMenu();
		for (MenuDTO menuDTO : lstMenu) {
			List<MenuDTO> childs = new ArrayList<>();
			for (int i = 0; i < lstMenuTmp.size(); i++) {
				if (lstMenuTmp.get(i).getParentId() == menuDTO.getId()) {
					childs.add(lstMenuTmp.get(i));
				}
			}
			menuDTO.setChilds(childs);
		}
		List<MenuDTO> result = lstMenu.stream()
				.filter(x -> x.getParentId() == 0)
				.collect(Collectors.toList());
		return result;
	}

}
