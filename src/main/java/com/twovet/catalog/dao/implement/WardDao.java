package com.twovet.catalog.dao.implement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.twovet.base.common.BaseDao;
import com.twovet.catalog.dao.IWardDao;
import com.twovet.catalog.model.Ward;

@Repository
public class WardDao extends BaseDao implements IWardDao {

	public WardDao(EntityManagerFactory emf) {
		super(emf);
	}

	@Override
	public List<Ward> getLstWard(String districtId) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Ward> query = em.createNamedQuery("Ward.findByDistrictId", Ward.class);
		query.setParameter("districtId", districtId);
		List<Ward> result = query.getResultList();
		em.close();
		return result;
	}

}
