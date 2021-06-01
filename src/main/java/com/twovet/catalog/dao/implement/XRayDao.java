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
import com.twovet.catalog.dao.IXRayDao;
import com.twovet.catalog.dto.XRayDTO;
import com.twovet.catalog.model.XRay;

/**
 * @author Tuantv
 *
 */
@Repository
public class XRayDao extends BaseDao implements IXRayDao {

	public XRayDao(EntityManagerFactory emf) {
		super(emf);
	}

	@Override
	public List<XRayDTO> searchAdvance(XRay xRay, int firstResult, int maxResult) {
		EntityManager em = emf.createEntityManager();
		List<XRayDTO> lstXRay = new ArrayList<XRayDTO>();
		Query query = null;
		try {
			query = this.querySearchAd(em, xRay, false);
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
			List<Object[]> lst = query.getResultList();
			lstXRay = FunctionCommon.map(XRayDTO.class, lst);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return lstXRay;
	}

	public Query querySearchAd(EntityManager em, XRay xRay, boolean isCount) throws Exception {
		StringBuilder sql = new StringBuilder();
		Query query = null;
		if (isCount) {
			sql.append("SELECT count(*) ");
		} else {
			sql.append("SELECT ");
			sql.append("b.XQ_CODE, ");
			sql.append("b.XQ_NAME, ");
			sql.append("b.DESCRIPT  ");
		}
		sql.append(" FROM xq b WHERE 1=1 ");
		if (StringUtils.isNotBlank(xRay.getxQCode())) {
			sql.append(" AND b.XQ_CODE like :xQCode");
		}
		if (StringUtils.isNotBlank(xRay.getxQName())) {
			sql.append(" AND b.XQ_NAME like :xQName");
		}
		sql.append(" AND b.IS_DELETE <> 1");
		sql.append(" ORDER BY b.id DESC");
		try {
			query = em.createNativeQuery(sql.toString());
			if (StringUtils.isNotBlank(xRay.getxQCode())) {
				query.setParameter("xQCode", "%" + xRay.getxQCode() + "%");
			}
			if (StringUtils.isNotBlank(xRay.getxQName())) {
				query.setParameter("xQName", "%" + xRay.getxQName() + "%");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return query;
	}

	@Override
	public Long insert(XRay xRay) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(xRay);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return xRay.getId();
	}

	@Override
	public Long edit(XRay xRay) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(xRay);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return xRay.getId();
	}

	@Override
	public XRay getDetailXRay(String xQCode) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<XRay> query = em.createNamedQuery("XRay.findByXqCode", XRay.class);
		query.setParameter("xQCode", xQCode);
		XRay result = query.getSingleResult();
		em.close();
		return result;
	}

	@Override
	public Long getTotalSearchAd(XRay xRay) {
		EntityManager em = emf.createEntityManager();
		Query query;
		Long total = null;
		try {
			query = this.querySearchAd(em, xRay, true);
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
	public List<XRay> getAll() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<XRay> query = em.createNamedQuery("XRay.findAll", XRay.class);
		List<XRay> lstXRay = query.getResultList();
		em.close();
		return lstXRay;
	}

}
