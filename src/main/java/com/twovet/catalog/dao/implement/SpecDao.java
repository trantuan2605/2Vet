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
import com.twovet.catalog.dao.ISpecDao;
import com.twovet.catalog.dto.SpecDTO;
import com.twovet.catalog.model.Spec;

@Repository

public class SpecDao extends BaseDao implements ISpecDao{

	public SpecDao(EntityManagerFactory emf) {
		super(emf);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Spec> getAllSpec() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Spec> query = em.createNamedQuery("Spec.findAll", Spec.class);
		List<Spec> result = query.getResultList();
		em.close();
		return result;
	}

	@Override
	public List<SpecDTO> searchAdvance(Spec spec, int firstResult, int maxResult) {
		EntityManager em = emf.createEntityManager();
		List<SpecDTO> result = new ArrayList<>();
		Query query = null;
		try {
			query = this.querySearchAd(em, spec, false);
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
			List<Object[]> list = query.getResultList();
			result = FunctionCommon.map(SpecDTO.class, list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return result;
	}
	
	public Query querySearchAd(EntityManager em, Spec spec, boolean isCount) throws Exception {
		StringBuilder sql = new StringBuilder();
		Query query = null;
		if (isCount) {
			sql.append("SELECT count(*) ");
		} else {
			sql.append("SELECT ");
			sql.append("s.SPECIES_CODE specCode, ");
			sql.append("s.SPECIES_NAME specName,  ");
			sql.append("s.DESCRIPT descript,  ");
			sql.append("s.BREED_CODE breedCode,  ");
			sql.append("b.BREED_NAME breedName,  ");
			sql.append("s.SPECTIFY spectify,  ");
			sql.append("s.PATH path,  ");
			sql.append("CAST(SUBSTRING(s.SPECIES_CODE,4, CHAR_LENGTH(s.SPECIES_CODE) -2 ) AS UNSIGNED) sequenceCode   ");
		}
		sql.append(" FROM species s ");
		sql.append(" LEFT JOIN breed b ON s.BREED_CODE = b.BREED_CODE ");
		sql.append(" WHERE 1=1 ");
		if (StringUtils.isNotBlank(spec.getSpecCode())) {
			sql.append(" AND s.SPECIES_CODE like :specCode");
		}
		if (StringUtils.isNotBlank(spec.getSpecName())) {
			sql.append(" AND s.SPECIES_NAME like :specName");
		}
		sql.append(" AND s.IS_DELETE <> 1");
		sql.append(" ORDER BY s.id DESC");
		try {
			query = em.createNativeQuery(sql.toString());
			if (StringUtils.isNotBlank(spec.getSpecCode())) {
				query.setParameter("specCode", "%" + spec.getSpecCode() + "%");
			}
			if (StringUtils.isNotBlank(spec.getSpecName())) {
				query.setParameter("specName", "%" + spec.getSpecName() + "%");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return query;
	}

	@Override
	public Spec getDetailSpec(String specCode) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Spec> query = em.createNamedQuery("Spec.findBySpecCode", Spec.class);
		query.setParameter("specCode", specCode);
		Spec result = query.getSingleResult();
		em.close();
		return result;
	}

	@Override
	public Long edit(Spec spec) {
		EntityManager em = emf.createEntityManager();
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE species SET SPECIES_NAME =:specName, BREED_CODE =:breedCode, DESCRIPT =:descript, SPECTIFY =:spectify, PATH =:path ");
		sql.append(" WHERE SPECIES_CODE =:specCode ");
		Long result = 0L;
		try {
			em.getTransaction().begin();
			Query query = em.createNativeQuery(sql.toString());
			query.setParameter("specName", spec.getSpecName());
			query.setParameter("breedCode",spec.getBreedCdStr());
			query.setParameter("descript", spec.getDescript());
			query.setParameter("specCode", spec.getSpecCode());
			query.setParameter("spectify", spec.getSpectify());
			query.setParameter("path", spec.getPath());
			int countUpdate = query.executeUpdate();
			em.getTransaction().commit();
			result = Long.valueOf(countUpdate);
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return result;
	}
	
	public Long getTotalSearchAd(Spec spec) {
		EntityManager em = emf.createEntityManager();
		Query query;
		Long total = null;
		try {
			query = this.querySearchAd(em, spec, true);
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
	public Long insert(Spec spec) {
		EntityManager em = emf.createEntityManager();
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO species (SPECIES_CODE, SPECIES_NAME, BREED_CODE, DESCRIPT, SPECTIFY, PATH) ");
		sql.append(" values (:specCode, :specName, :breedCode, :descript, :spectify, :path) ");
		
		
		Long result = 0L;
		try {
			em.getTransaction().begin();
			Query query = em.createNativeQuery(sql.toString());
			query.setParameter("specCode", spec.getSpecCode());
			query.setParameter("specName", spec.getSpecName());
			query.setParameter("breedCode",spec.getBreedCdStr());
			query.setParameter("descript", spec.getDescript());
			query.setParameter("spectify", spec.getSpectify());
			query.setParameter("path", spec.getPath());
			int countInsert = query.executeUpdate();
			em.getTransaction().commit();
			result = Long.valueOf(countInsert);
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return result;
	}

	
	@Override
	public List<Spec> getLstSpecByCode(String breedCode) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Spec> query = em.createNamedQuery("Spec.findByBreddCode", Spec.class);
		query.setParameter("breedCode", breedCode);
		List<Spec> result = query.getResultList();
		em.close();
		return result;
	}

}
