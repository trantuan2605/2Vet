package com.twovet.catalog.dao.implement;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.twovet.base.common.BaseDao;
import com.twovet.base.common.FunctionCommon;
import com.twovet.catalog.dao.ISkinTestDao;
import com.twovet.catalog.dto.SkinTestDTO;
import com.twovet.catalog.model.SkinTest;

@Repository
public class SkinTestDao extends BaseDao implements ISkinTestDao{

	
	public SkinTestDao(EntityManagerFactory emf) {
		super(emf);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<SkinTestDTO> searchAdvance(SkinTest skinTest, int firstResult, int maxResult) {
		EntityManager em = emf.createEntityManager();
		List<SkinTestDTO> lstSkinTest = new ArrayList<SkinTestDTO>();
		Query query = null;
		try {
			query = this.querySearchAd(em, skinTest, false);
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
			List<Object[]> lst = query.getResultList();
			lstSkinTest = FunctionCommon.map(SkinTestDTO.class, lst);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return lstSkinTest;
	}
	
	public Query querySearchAd(EntityManager em, SkinTest skinTest, boolean isCount) throws Exception {
		StringBuilder sql = new StringBuilder();
		Query query = null;
		if (isCount) {
			sql.append("SELECT count(*) ");
		} else {
			sql.append("SELECT ");
			sql.append("b.SKINTEST_CODE, ");
			sql.append("b.SKINTEST_NAME, ");
			sql.append("b.DESCRIPT  ");
		}
		sql.append(" FROM skintest b WHERE 1=1 ");
		if (StringUtils.isNotBlank(skinTest.getSkinTestCode())) {
			sql.append(" AND b.SKINTEST_CODE like :skinTestCode");
		}
		if (StringUtils.isNotBlank(skinTest.getSkinTestName())) {
			sql.append(" AND b.SKINTEST_NAME like :skinTestName");
		}
		sql.append(" AND b.IS_DELETE <> 1");
		sql.append(" ORDER BY b.id DESC");
		try {
			query = em.createNativeQuery(sql.toString());
			if (StringUtils.isNotBlank(skinTest.getSkinTestCode())) {
				query.setParameter("skinTestCode", "%" + skinTest.getSkinTestCode() + "%");
			}
			if (StringUtils.isNotBlank(skinTest.getSkinTestName())) {
				query.setParameter("skinTestName", "%" + skinTest.getSkinTestName() + "%");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return query;
	}

	@Override
	public Long insert(SkinTest skinTest) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(skinTest);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return skinTest.getId();
	}

	@Override
	public Long edit(SkinTest skinTest) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(skinTest);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return skinTest.getId();
	}

	@Override
	public SkinTest getDetailSkinTest(String skinTestCode) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<SkinTest> query = em.createNamedQuery("SkinTest.findBySkinTestCode", SkinTest.class);
		query.setParameter("skinTestCode", skinTestCode);
		SkinTest result = query.getSingleResult();
		em.close();
		return result;
	}

	@Override
	public Long getTotalSearchAd(SkinTest skinTest) {
		EntityManager em = emf.createEntityManager();
		Query query;
		Long total = null;
		try {
			query = this.querySearchAd(em, skinTest, true);
			BigInteger count = (BigInteger) query.getSingleResult();
			total = count != null ? count.longValue() : 0L;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return total != null ? total : 0L;
	}

	@Override
	public List<SkinTest> getAll() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<SkinTest> query = em.createNamedQuery("SkinTest.findAll", SkinTest.class);
		List<SkinTest> lstSkin = query.getResultList();
		em.close();
		return lstSkin;
	}

}
