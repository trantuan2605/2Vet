/**
 * 
 */
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
import com.twovet.catalog.dao.IBreedDao;
import com.twovet.catalog.dao.ISuperSonicDao;
import com.twovet.catalog.dto.BreedDTO;
import com.twovet.catalog.dto.SuperSonicDTO;
import com.twovet.catalog.model.Breed;
import com.twovet.catalog.model.SuperSonic;

/**
 * @author Tuantv
 *
 */
@Repository
public class SuperSonicDao extends BaseDao implements ISuperSonicDao {

	public SuperSonicDao(EntityManagerFactory emf) {
		super(emf);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<SuperSonicDTO> searchAdvance(SuperSonic superSonic, int firstResult, int maxResult) {
		EntityManager em = emf.createEntityManager();
		List<SuperSonicDTO> lstSonic = new ArrayList<SuperSonicDTO>();
		Query query = null;
		try {
			query = this.querySearchAd(em, superSonic, false);
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
			List<Object[]> lst = query.getResultList();
			lstSonic = FunctionCommon.map(SuperSonicDTO.class, lst);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return lstSonic;
	}

	public Query querySearchAd(EntityManager em, SuperSonic superSonic, boolean isCount) throws Exception {
		StringBuilder sql = new StringBuilder();
		Query query = null;
		if (isCount) {
			sql.append("SELECT count(*) ");
		} else {
			sql.append("SELECT ");
			sql.append("b.SUPERSONIC_CODE, ");
			sql.append("b.SUPERSONIC_NAME, ");
			sql.append("b.DESCRIPT  ");
		}
		sql.append(" FROM supersonic b WHERE 1=1 ");
		if (StringUtils.isNotBlank(superSonic.getSuperSonicCode())) {
			sql.append(" AND b.SUPERSONIC_CODE like :superSonicCode");
		}
		if (StringUtils.isNotBlank(superSonic.getSuperSonicName())) {
			sql.append(" AND b.SUPERSONIC_NAME like :superSonicName");
		}
		sql.append(" AND b.IS_DELETE <> 1");
		sql.append(" ORDER BY b.id DESC");
		try {
			query = em.createNativeQuery(sql.toString());
			if (StringUtils.isNotBlank(superSonic.getSuperSonicCode())) {
				query.setParameter("superSonicCode", "%" + superSonic.getSuperSonicCode() + "%");
			}
			if (StringUtils.isNotBlank(superSonic.getSuperSonicName())) {
				query.setParameter("superSonicName", "%" + superSonic.getSuperSonicName() + "%");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return query;
	}

	@Override
	public Long insert(SuperSonic superSonic) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(superSonic);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return superSonic.getId();
	}

	@Override
	public Long edit(SuperSonic superSonic) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(superSonic);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return superSonic.getId();
	}

	@Override
	public SuperSonic getDetailSuperSonic(String superSonicCode) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<SuperSonic> query = em.createNamedQuery("SuperSonic.findBySuperSonicCode", SuperSonic.class);
		query.setParameter("superSonicCode", superSonicCode);
		SuperSonic result = query.getSingleResult();
		em.close();
		return result;
	}

	@Override
	public Long getTotalSearchAd(SuperSonic superSonic) {
		EntityManager em = emf.createEntityManager();
		Query query;
		Long total = null;
		try {
			query = this.querySearchAd(em, superSonic, true);
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
	public List<SuperSonic> getAll() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<SuperSonic> query = em.createNamedQuery("SuperSonic.findAll", SuperSonic.class);
		List<SuperSonic> lstSonic = query.getResultList();
		em.close();
		return lstSonic;
	}

}
