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
import com.twovet.catalog.dao.IUrineDao;
import com.twovet.catalog.dto.UrineDTO;
import com.twovet.catalog.model.Urine;

@Repository
public class UrineDao extends BaseDao implements IUrineDao {

	public UrineDao(EntityManagerFactory emf) {
		super(emf);
	}

	@Override
	public List<Urine> getAllUrines() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Urine> query = em.createNamedQuery("Urine.findAll", Urine.class);
		List<Urine> result = query.getResultList();
		em.close();
		return result;
	}

	@Override
	public List<UrineDTO> searchAdvance(Urine urine, int firstResult, int maxResult) {
		EntityManager em = emf.createEntityManager();
		List<UrineDTO> result = new ArrayList<>();
		Query query = null;
		try {
			query = this.querySearchAd(em, urine, false);
			if (firstResult != 0) {
				query.setFirstResult(firstResult);
			}
			if (maxResult != 0) {
				query.setMaxResults(maxResult);
			}
			List<Object[]> list = query.getResultList();
			result = FunctionCommon.map(UrineDTO.class, list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return result;
	}

	public Query querySearchAd(EntityManager em, Urine urine, boolean isCount) throws Exception {
		StringBuilder sql = new StringBuilder();
		Query query = null;
		if (isCount) {
			sql.append("SELECT count(*) ");
		} else {
			sql.append("SELECT ");
			sql.append("b.URINE_CODE, ");
			sql.append("b.URINE_NAME, ");
			sql.append("b.DESCRIPT  ");
		}
		sql.append(" FROM urine_test b WHERE 1=1 ");
		if (StringUtils.isNotBlank(urine.getUrineCode())) {
			sql.append(" AND b.URINE_CODE like :urineCode");
		}
		if (StringUtils.isNotBlank(urine.getUrineName())) {
			sql.append(" AND b.URINE_NAME like :urineName");
		}
		sql.append(" AND b.IS_DELETE <> 1");
		sql.append(" ORDER BY b.id DESC");
		try {
			query = em.createNativeQuery(sql.toString());
			if (StringUtils.isNotBlank(urine.getUrineCode())) {
				query.setParameter("urineCode", "%" + urine.getUrineCode() + "%");
			}
			if (StringUtils.isNotBlank(urine.getUrineName())) {
				query.setParameter("urineName", "%" + urine.getUrineName() + "%");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return query;
	}

	@Override
	public Urine getDetailUrine(String urineCode) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Urine> query = em.createNamedQuery("Urine.findByUrineCode", Urine.class);
		query.setParameter("urineCode", urineCode);
		Urine result = query.getSingleResult();
		em.close();
		return result;
	}

	@Override
	public Long edit(Urine urine) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(urine);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return urine.getId();
	}

	@Override
	public Long insert(Urine urine) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(urine);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return urine.getId();
	}

	@Override
	public Long getTotalSearchAd(Urine urine) {
		EntityManager em = emf.createEntityManager();
		Query query;
		Long total = null;
		try {
			query = this.querySearchAd(em, urine, true);
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
