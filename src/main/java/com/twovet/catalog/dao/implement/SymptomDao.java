package com.twovet.catalog.dao.implement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.twovet.base.common.BaseDao;
import com.twovet.catalog.dao.ISymptomDao;
import com.twovet.catalog.model.Symptom;

@Repository
public class SymptomDao extends BaseDao implements ISymptomDao {

	public SymptomDao(EntityManagerFactory emf) {
		super(emf);
	}

	@Override
	public List<Symptom> getAll() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Symptom> query = em.createNamedQuery("Symptom.findAll", Symptom.class);
		List<Symptom> lstSymptom = query.getResultList();
		em.close();
		return lstSymptom;
	}

}
