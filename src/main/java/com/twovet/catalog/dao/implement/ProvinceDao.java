package com.twovet.catalog.dao.implement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.twovet.base.common.BaseDao;
import com.twovet.catalog.dao.IProvinceDao;
import com.twovet.catalog.model.Province;

@Repository
public class ProvinceDao extends BaseDao implements IProvinceDao {

	public ProvinceDao(EntityManagerFactory emf) {
		super(emf);
	}

	@Override
	public List<Province> getAll() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Province> query = em.createNamedQuery("Province.findAll", Province.class);
		List<Province> lstProvince = query.getResultList();
		em.close();
		return lstProvince;
	}

}
