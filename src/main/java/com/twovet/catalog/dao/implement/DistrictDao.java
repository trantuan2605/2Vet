package com.twovet.catalog.dao.implement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.twovet.base.common.BaseDao;
import com.twovet.catalog.dao.IDistrictDao;
import com.twovet.catalog.model.District;

@Repository
public class DistrictDao extends BaseDao implements IDistrictDao {

	public DistrictDao(EntityManagerFactory emf) {
		super(emf);
	}

	@Override
	public List<District> getLstDistrict(String provinceId) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<District> query = em.createNamedQuery("District.findByProvinceId", District.class);
		query.setParameter("provinceId", provinceId);
		List<District> result = query.getResultList();
		em.close();
		return result;
	}

}
