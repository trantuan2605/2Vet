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
import com.twovet.catalog.dao.IStoolDao;
import com.twovet.catalog.dto.StoolDTO;
import com.twovet.catalog.model.Stool;
import com.twovet.catalog.model.Urine;

@Repository
public class StoolDao extends BaseDao implements IStoolDao {

	public StoolDao(EntityManagerFactory emf) {
		super(emf);
	}

	@Override
	public List<Stool> getAllStools() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Stool> query = em.createNamedQuery("Stool.findAll", Stool.class);
		List<Stool> result = query.getResultList();
		em.close();
		return result;
	}

	@Override
	public List<StoolDTO> searchAdvance(Stool stool, int firstResult, int maxResult) {
		EntityManager em = emf.createEntityManager();
		List<StoolDTO> result = new ArrayList<>();
		Query query = null;
		try {
			query = this.querySearchAd(em, stool, false);
			if (firstResult != 0) {
				query.setFirstResult(firstResult);
			}
			if (maxResult != 0) {
				query.setMaxResults(maxResult);
			}
			List<Object[]> list = query.getResultList();
			result = FunctionCommon.map(StoolDTO.class, list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return result;
	}

	public Query querySearchAd(EntityManager em, Stool stool, boolean isCount) throws Exception {
		StringBuilder sql = new StringBuilder();
		Query query = null;
		if (isCount) {
			sql.append("SELECT count(*) ");
		} else {
			sql.append("SELECT ");
			sql.append("b.STOOL_CODE, ");
			sql.append("b.STOOL_NAME, ");
			sql.append("b.DESCRIPT  ");
		}
		sql.append(" FROM stool_test b WHERE 1=1 ");
		if (StringUtils.isNotBlank(stool.getStoolCode())) {
			sql.append(" AND b.STOOL_CODE like :stoolCode");
		}
		if (StringUtils.isNotBlank(stool.getStoolName())) {
			sql.append(" AND b.STOOL_NAME like :stoolName");
		}
		sql.append(" AND b.IS_DELETE <> 1");
		sql.append(" ORDER BY b.id DESC");
		try {
			query = em.createNativeQuery(sql.toString());
			if (StringUtils.isNotBlank(stool.getStoolCode())) {
				query.setParameter("stoolCode", "%" +stool.getStoolCode() + "%");
			}
			if (StringUtils.isNotBlank(stool.getStoolName())) {
				query.setParameter("stoolName", "%" + stool.getStoolName() + "%");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return query;
	}

	@Override
	public Stool getDetailStool(String stoolCode) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Stool> query = em.createNamedQuery("Stool.findByStoolCode", Stool.class);
		query.setParameter("stoolCode", stoolCode);
		Stool result = query.getSingleResult();
		em.close();
		return result;
	}

	@Override
	public Long edit(Stool stool) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(stool);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return stool.getId();
	}

	@Override
	public Long insert(Stool stool) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(stool);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return stool.getId();
	}

	@Override
	public Long getTotalSearchAd(Stool stool) {
		EntityManager em = emf.createEntityManager();
		Query query;
		Long total = null;
		try {
			query = this.querySearchAd(em, stool, true);
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
