package com.twovet.Navigation.dao.Implement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import com.twovet.Navigation.dao.IMenuDao;
import com.twovet.Navigation.dto.MenuDTO;
import com.twovet.Navigation.model.Menu;
import com.twovet.base.common.BaseDao;

@Repository
public class MenuDao extends BaseDao implements IMenuDao {
//	@Autowired
//	private EntityManagerFactory emf;

	public MenuDao(EntityManagerFactory emf) {
		super(emf);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<MenuDTO> getMenuNavigators() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MenuDTO> getAllMenu() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Menu> query = em.createNamedQuery("Menu.findAll", Menu.class);
		List<Menu> lst = query.getResultList();
		em.close();
		ModelMapper modelMapper = new ModelMapper();
		List<MenuDTO> result = lst.stream()
				.map(menu -> modelMapper.map(menu, MenuDTO.class))
				.collect(Collectors.toList());
		return result;
	}

}
