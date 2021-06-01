package com.twovet.catalog.dao.implement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.twovet.base.common.BaseDao;
import com.twovet.base.common.FunctionCommon;
import com.twovet.catalog.dao.IConclusionDao;
import com.twovet.catalog.dto.ConclusionDTO;
import com.twovet.catalog.dto.ExamClinicalCommonDTO;
import com.twovet.catalog.model.Conclusion;
import com.twovet.catalog.model.ExamClinical;

@Repository
public class ConclusionDao extends BaseDao implements IConclusionDao {

	public ConclusionDao(EntityManagerFactory emf) {
		super(emf);
	}

	@Override
	public Long insert(Conclusion conclusion) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(conclusion);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return conclusion.getId();
	}

	@Override
	public Long edit(Conclusion conclusion) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(conclusion);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return conclusion.getId();
	}

	@Override
	public Conclusion getDetailConclusion(String examCode) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Conclusion> query = em.createNamedQuery("Conclusion.findByCode", Conclusion.class);
		query.setParameter("examClinicalCode", examCode);
		try {
			Conclusion result = query.getSingleResult();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return new Conclusion();
	}

}
