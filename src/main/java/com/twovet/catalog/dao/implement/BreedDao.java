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
import com.twovet.catalog.dto.BreedDTO;
import com.twovet.catalog.model.Breed;
import com.twovet.catalog.model.Customer;

/**
 * @author Tuantv
 *
 */
@Repository
public class BreedDao extends BaseDao implements IBreedDao {

	public BreedDao(EntityManagerFactory emf) {
		super(emf);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<BreedDTO> searchAdvance(Breed breed, int firstResult, int maxResult) {
		EntityManager em = emf.createEntityManager();
		List<BreedDTO> lstBreed = new ArrayList<BreedDTO>();
		Query query = null;
		try {
			query = this.querySearchAd(em, breed, false);
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
			List<Object[]> lst = query.getResultList();
			lstBreed = FunctionCommon.map(BreedDTO.class, lst);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return lstBreed;
	}

	/**
	 * 
	 * @param em
	 * @param breed
	 * @param isCount
	 * @return
	 * @throws Exception
	 */
	public Query querySearchAd(EntityManager em, Breed breed, boolean isCount) throws Exception {
		StringBuilder sql = new StringBuilder();
		Query query = null;
		if (isCount) {
			sql.append("SELECT count(*) ");
		} else {
			sql.append("SELECT ");
			sql.append("b.BREED_CODE, ");
			sql.append("b.BREED_NAME, ");
			sql.append("b.DESCRIPT,  ");
			sql.append("b.SPECTIFY,  ");
			sql.append("b.PATH,  ");
			sql.append("CAST(SUBSTRING(b.BREED_CODE,4, CHAR_LENGTH(b.BREED_CODE) -2 ) AS UNSIGNED) sequenceCode   ");
		}
		sql.append(" FROM breed b WHERE 1=1 ");
		if (StringUtils.isNotBlank(breed.getBreedCode())) {
			sql.append(" AND b.BREED_CODE like :breedCode");
		}
		if (StringUtils.isNotBlank(breed.getBreedName())) {
			sql.append(" AND b.BREED_NAME like :breedName");
		}
		sql.append(" ORDER BY b.id DESC");
		try {
			query = em.createNativeQuery(sql.toString());
			if (StringUtils.isNotBlank(breed.getBreedCode())) {
				query.setParameter("breedCode", "%" + breed.getBreedCode() + "%");
			}
			if (StringUtils.isNotBlank(breed.getBreedName())) {
				query.setParameter("breedName", "%" + breed.getBreedName() + "%");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return query;
	}

	@Override
	public Long insert(Breed breed) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(breed);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return breed.getId();
	}

	@Override
	public Long edit(Breed breed) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(breed);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return breed.getId();
	}

	@Override
	public Breed getDetailBreed(String breedCode) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Breed> query = em.createNamedQuery("Breed.findByBreedCode", Breed.class);
		query.setParameter("breedCode", breedCode);
		Breed result = query.getSingleResult();
		em.close();
		return result;
	}

	@Override
	public Long getTotalSearchAd(Breed breed) {
		EntityManager em = emf.createEntityManager();
		Query query;
		Long total = null;
		try {
			query = this.querySearchAd(em, breed, true);
			BigInteger count = (BigInteger) query.getSingleResult();
			total = count != null ? count.longValue() : 0L;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			em.close();
		}
		return total != null ? total : 0L;
	}

	@Override
	public List<Breed> getAll() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Breed> query = em.createNamedQuery("Breed.findAll", Breed.class);
		List<Breed> lstBreed = query.getResultList();
		em.close();
		return lstBreed;
	}

}
