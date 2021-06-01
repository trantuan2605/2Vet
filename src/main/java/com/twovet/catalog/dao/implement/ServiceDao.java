package com.twovet.catalog.dao.implement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.twovet.base.common.BaseDao;
import com.twovet.base.common.FunctionCommon;
import com.twovet.catalog.dao.IServiceDao;
import com.twovet.catalog.dto.ServiceDTO;
import com.twovet.catalog.model.Services;

@Repository
public class ServiceDao extends BaseDao implements IServiceDao{

	public ServiceDao(EntityManagerFactory emf) {
		super(emf);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ServiceDTO> getAllServices(String branchCode) {
		StringBuilder sql = new StringBuilder();
		Query query = null;
		if (StringUtils.isNotBlank(branchCode)) {
			sql.append("select tbb.*,  ");
			sql.append(" case when tba.branchCode is not null then 1 else 0 end checkedStatus, coalesce(tba.price, 0.0) price, coalesce(tba.id,' ') idBS, ");
			sql.append(" tba.branchCode from ( ");
			sql.append("  select s.id id, s.service_code serviceCode,  s.service_name serviceName, s.descript descript ");
			sql.append("   from services s    where s.is_deleted != 1 ");
			sql.append(" )tbb ");
			sql.append(" left join ");
			sql.append(" (select sb.service_code serviceCode, s.service_name serviceName,  b.branch_code branchCode,  sb.price price, sb.id ");
			sql.append(" from services_branch sb ");
			sql.append(" left join  branch b  on sb.branch_code = b.branch_code ");
			sql.append(" left join services s  on sb.service_code = s.service_code  and s.is_deleted = 0 ");
			sql.append(" where sb.branch_code =:branchCode   and sb.is_deleted = 0 ");
			sql.append("  )tba ");
			sql.append(" on tbb.serviceCode = tba.serviceCode ");
		} else {
			sql.append("select s.id id, s.service_code serviceCode, s.service_name serviceName, s.descript descript "); 
			sql.append("  from services s "); 
			sql.append(" where s.is_deleted != 1 ");
		}
		EntityManager em = emf.createEntityManager();
		query = em.createNativeQuery(sql.toString());
		if (StringUtils.isNotBlank(branchCode)) {
			query.setParameter("branchCode", branchCode);
		}
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.getResultList();
		List<ServiceDTO> result = FunctionCommon.map(ServiceDTO.class, list);
		if (result != null && !result.isEmpty()) {
			em.close();
			return result;
		}
		return new ArrayList<>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ServiceDTO getDetailService(String serviceCode) {
		StringBuilder sql = new StringBuilder();
		Query query = null;
		sql.append("select s.id id, s.service_code serviceCode, s.service_name serviceName, s.descript descript "); 
		sql.append("  from services s "); 
		sql.append(" where s.service_code =:serviceCode ");
		EntityManager em = emf.createEntityManager();
		query = em.createNativeQuery(sql.toString());
		query.setParameter("serviceCode", serviceCode);
		List<Object[]> list = query.getResultList();
		List<ServiceDTO> result = FunctionCommon.map(ServiceDTO.class, list);
		if (result != null && !result.isEmpty()) {
			em.close();
			return result.get(0);
		}
		return new ServiceDTO();
	}

	@Override
	public Long edit(Services service) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(service);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return service.getId();
	}

	@Override
	public Long insert(Services service) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(service);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return service.getId();
	}
}
