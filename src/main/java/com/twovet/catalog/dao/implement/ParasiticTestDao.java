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
import com.twovet.catalog.dao.IParasiticTestDao;
import com.twovet.catalog.dto.ParasiticDTO;
import com.twovet.catalog.model.Parasitic;

@Repository
public class ParasiticTestDao extends BaseDao implements IParasiticTestDao {

	public ParasiticTestDao(EntityManagerFactory emf) {
		super(emf);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ParasiticDTO> searchAdvance(Parasitic parasitic, int firstResult, int maxResult) {
		EntityManager em = emf.createEntityManager();
		List<ParasiticDTO> lstParasitic = new ArrayList<ParasiticDTO>();
		Query query = null;
		try {
			query = this.querySearchAd(em, parasitic, false);
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
			List<Object[]> lst = query.getResultList();
			lstParasitic = FunctionCommon.map(ParasiticDTO.class, lst);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return lstParasitic;
	}

	public Query querySearchAd(EntityManager em, Parasitic parasitic, boolean isCount) throws Exception {
		StringBuilder sql = new StringBuilder();
		Query query = null;
		if (isCount) {
			sql.append("SELECT count(*) ");
		} else {
			sql.append("SELECT ");
			sql.append("b.PARASITIC_TEST_CODE, ");
			sql.append("b.PARASITIC_TEST_NAME, ");
			sql.append("b.DESCRIPT  ");
		}
		sql.append(" FROM parasitic_test b WHERE 1=1 ");
		if (StringUtils.isNotBlank(parasitic.getParasiticTestCode())) {
			sql.append(" AND b.PARASITIC_TEST_CODE like :parasiticTestCode");
		}
		if (StringUtils.isNotBlank(parasitic.getParasiticTestName())) {
			sql.append(" AND b.PARASITIC_TEST_NAME like :parasiticTestName");
		}
		sql.append(" AND b.IS_DELETE <> 1");
		sql.append(" ORDER BY b.id DESC");
		try {
			query = em.createNativeQuery(sql.toString());
			if (StringUtils.isNotBlank(parasitic.getParasiticTestCode())) {
				query.setParameter("parasiticTestCode", "%" + parasitic.getParasiticTestCode() + "%");
			}
			if (StringUtils.isNotBlank(parasitic.getParasiticTestName())) {
				query.setParameter("parasiticTestName", "%" + parasitic.getParasiticTestName() + "%");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return query;
	}

	@Override
	public Long insert(Parasitic parasitic) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(parasitic);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return parasitic.getId();
	}

	@Override
	public Long edit(Parasitic parasitic) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(parasitic);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return parasitic.getId();
	}

	@Override
	public Parasitic getDetailParasitic(String parasiticCode) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Parasitic> query = em.createNamedQuery("Parasitic.findByParasiticTestCode", Parasitic.class);
		query.setParameter("parasiticTestCode", parasiticCode);
		Parasitic result = query.getSingleResult();
		em.close();
		return result;
	}

	@Override
	public Long getTotalSearchAd(Parasitic parasitic) {
		EntityManager em = emf.createEntityManager();
		Query query;
		Long total = null;
		try {
			query = this.querySearchAd(em, parasitic, true);
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
	public List<Parasitic> getAll() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Parasitic> query = em.createNamedQuery("Parasitic.findAll", Parasitic.class);
		List<Parasitic> lstParasitic = query.getResultList();
		em.close();
		return lstParasitic;
	}

}
