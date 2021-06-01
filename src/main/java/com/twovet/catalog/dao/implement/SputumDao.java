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
import com.twovet.catalog.dao.ISputumDao;
import com.twovet.catalog.dto.SputumDTO;
import com.twovet.catalog.model.Sputum;

@Repository
public class SputumDao extends BaseDao implements ISputumDao {

	public SputumDao(EntityManagerFactory emf) {
		super(emf);
	}

	@Override
	public List<Sputum> getAllSputums() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Sputum> query = em.createNamedQuery("Sputum.findAll", Sputum.class);
		List<Sputum> result = query.getResultList();
		em.close();
		return result;
	}

	@Override
	public List<SputumDTO> searchAdvance(Sputum sputum, int firstResult, int maxResult) {
		EntityManager em = emf.createEntityManager();
		List<SputumDTO> result = new ArrayList<>();
		Query query = null;
		try {
			query = this.querySearchAd(em, sputum, false);
			if (firstResult != 0) {
				query.setFirstResult(firstResult);
			}
			if (maxResult != 0) {
				query.setMaxResults(maxResult);
			}
			List<Object[]> list = query.getResultList();
			result = FunctionCommon.map(SputumDTO.class, list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return result;
	}

	public Query querySearchAd(EntityManager em, Sputum sputum, boolean isCount) throws Exception {
		StringBuilder sql = new StringBuilder();
		Query query = null;
		if (isCount) {
			sql.append("SELECT count(*) ");
		} else {
			sql.append("SELECT ");
			sql.append("b.SPUTUM_CODE, ");
			sql.append("b.SPUTUM_NAME, ");
			sql.append("b.DESCRIPT  ");
		}
		sql.append(" FROM sputum_test b WHERE 1=1 ");
		if (StringUtils.isNotBlank(sputum.getSputumCode())) {
			sql.append(" AND b.SPUTUM_CODE like :sputumCode");
		}
		if (StringUtils.isNotBlank(sputum.getSputumName())) {
			sql.append(" AND b.SPUTUM_NAME like :sputumName");
		}
		sql.append(" AND b.IS_DELETE <> 1");
		sql.append(" ORDER BY b.id DESC");
		try {
			query = em.createNativeQuery(sql.toString());
			if (StringUtils.isNotBlank(sputum.getSputumCode())) {
				query.setParameter("sputumCode", "%" + sputum.getSputumCode() + "%");
			}
			if (StringUtils.isNotBlank(sputum.getSputumName())) {
				query.setParameter("sputumName", "%" + sputum.getSputumName() + "%");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return query;
	}

	@Override
	public Sputum getDetailSputum(String sputumCode) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Sputum> query = em.createNamedQuery("Sputum.findBySputumCode", Sputum.class);
		query.setParameter("sputumCode", sputumCode);
		Sputum result = query.getSingleResult();
		em.close();
		return result;
	}

	@Override
	public Long edit(Sputum sputum) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(sputum);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return sputum.getId();
	}

	@Override
	public Long insert(Sputum sputum) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(sputum);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return sputum.getId();
	}

	@Override
	public Long getTotalSearchAd(Sputum sputum) {
		EntityManager em = emf.createEntityManager();
		Query query;
		Long total = null;
		try {
			query = this.querySearchAd(em, sputum, true);
			BigInteger count = (BigInteger) query.getSingleResult();
			total = count != null ? count.longValue() : 0L;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return total != null ? total : 0L;
	}

}
