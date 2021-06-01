package com.twovet.catalog.dao.implement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.twovet.base.common.BaseDao;
import com.twovet.base.common.FunctionCommon;
import com.twovet.catalog.dao.IQuickTestDao;
import com.twovet.catalog.dto.QuickTestDTO;
import com.twovet.catalog.model.QuickTest;

@Repository
public class QuickTestDao extends BaseDao implements IQuickTestDao{

	public QuickTestDao(EntityManagerFactory emf) {
		super(emf);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<QuickTest> getAllQuickTest() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<QuickTest> query = em.createNamedQuery("QuickTest.findAll", QuickTest.class);
		List<QuickTest> result = query.getResultList();
		em.close();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public QuickTestDTO getDetailQuickTest(String quickCode) {
		StringBuilder sql = new StringBuilder();
		Query query = null;
		sql.append("select q.id id, q.quick_code quickCode, q.quick_name quickName, q.descript descript "); 
		sql.append(" from quick_test q "); 
		sql.append(" where q.quick_code =:quickCode");
		EntityManager em = emf.createEntityManager();
		query = em.createNativeQuery(sql.toString());
		query.setParameter("quickCode", quickCode);
		List<Object[]> list = query.getResultList();
		List<QuickTestDTO> result = FunctionCommon.map(QuickTestDTO.class, list);
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return new QuickTestDTO();
	}

	@Override
	public Long edit(QuickTest quickTest) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(quickTest);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return quickTest.getId();
	}

	@Override
	public Long insert(QuickTest quickTest) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(quickTest);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return quickTest.getId();
	}
}
