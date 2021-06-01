package com.twovet.catalog.dao.implement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.twovet.base.common.BaseDao;
import com.twovet.base.common.FunctionCommon;
import com.twovet.catalog.dao.IVacinationDao;
import com.twovet.catalog.dto.VacinationDTO;
import com.twovet.catalog.model.Vacination;

@Repository
public class VacinationDao extends BaseDao implements IVacinationDao{

	public VacinationDao(EntityManagerFactory emf) {
		super(emf);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Vacination> getAllVacinations() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Vacination> query = em.createNamedQuery("Vacination.findAll", Vacination.class);
		List<Vacination> result = query.getResultList();
		em.close();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public VacinationDTO getDetailVacine(String vacineCode) {
		StringBuilder sql = new StringBuilder();
		Query query = null;
		sql.append("select v.id id, v.vacine_code vacineCode, v.vacine_name vacineName, v.descript descript "); 
		sql.append(" from vacination v "); 
		sql.append(" where v.vacine_code =:vacineCode");
		EntityManager em = emf.createEntityManager();
		query = em.createNativeQuery(sql.toString());
		query.setParameter("vacineCode", vacineCode);
		List<Object[]> list = query.getResultList();
		List<VacinationDTO> result = FunctionCommon.map(VacinationDTO.class, list);
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return new VacinationDTO();
	}

	@Override
	public Long edit(Vacination vacine) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(vacine);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return vacine.getId();
	}

	@Override
	public Long insert(Vacination vacine) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(vacine);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return vacine.getId();
	}
}
