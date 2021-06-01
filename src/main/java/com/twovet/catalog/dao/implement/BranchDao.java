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
import com.twovet.catalog.dao.IBranchDao;
import com.twovet.catalog.dto.BranchDTO;
import com.twovet.catalog.dto.DoctorDTO;
import com.twovet.catalog.dto.ServiceDTO;
import com.twovet.catalog.model.Branch;
import com.twovet.catalog.model.ServicesBranch;

@Repository
public class BranchDao extends BaseDao implements IBranchDao{

	public BranchDao(EntityManagerFactory emf) {
		super(emf);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long insert(Branch branch) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(branch);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return branch.getId();
	}
	
	/**
	 * Get lst manager
	 * @param branchCode
	 * @return
	 */
	public List<DoctorDTO> getLstManager(String branchCode) {
		EntityManager em = emf.createEntityManager();
		Branch branchParam = new Branch();
		branchParam.setBranchCode(branchCode);
		List<DoctorDTO> result = new ArrayList<>();
		Query query = null;
		try {
			query = this.queryLstManager(em, branchParam, false);
			List<Object[]> list = query.getResultList();
			result = FunctionCommon.map(DoctorDTO.class, list);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			em.close();
		}
		return result;
	}
	
	@Override
	public BranchDTO getDetailBranch(String branchCode) {
		EntityManager em = emf.createEntityManager();
		Branch branchParam = new Branch();
		branchParam.setBranchCode(branchCode);
		List<BranchDTO> result = new ArrayList<>();
		List<DoctorDTO> lstManager = this.getLstManager(branchCode);
		Query query = null;
		try {
			query = this.querySearchAd(em, branchParam, false);
			List<Object[]> list = query.getResultList();
			result = FunctionCommon.map(BranchDTO.class, list);
			if (result != null && !result.isEmpty()) {
				BranchDTO branchRS = result.get(0);
				if(lstManager != null && !lstManager.isEmpty()) {
					for (DoctorDTO dto : lstManager) {
						if(dto.getIsManager() == 1) {
							branchRS.setManagerName(dto.getDoctorName());
							branchRS.setPhoneManager(dto.getPhone());
						}
						if(dto.getIsSubManager() == 1) {
							branchRS.setSubManagerName(dto.getDoctorName());
							branchRS.setPhoneManager(dto.getPhone());
						}
					}
				}
				return branchRS;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			em.close();
		}
		return new BranchDTO();
	}

	@Override
	public Long edit(Branch branch) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(branch);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return branch.getId();
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<BranchDTO> searchAdvance(Branch branch, int firstResult, int maxResult) {
		EntityManager em = emf.createEntityManager();
		List<BranchDTO> result = new ArrayList<>();
		Query query = null;
		try {
			query = this.querySearchAd(em, branch, false);
			if (firstResult != 0) {
				query.setFirstResult(firstResult);
			}
			if (maxResult != 0) {
				query.setMaxResults(maxResult);
			}
			List<Object[]> list = query.getResultList();
			result = FunctionCommon.map(BranchDTO.class, list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return result;
	}
	
	public Query querySearchAd(EntityManager em, Branch branch, boolean isCount) throws Exception{
		StringBuilder sql = new StringBuilder();
		Query query = null;
		if (isCount) {
			sql.append("select count(*) ");
		} else {
			sql.append("SELECT b.id, b.branch_code branchCode, b.branch_name branchName, b.address address, b.phone phone, b.descript descript ");
			sql.append(" , b.ward_id wardId, w.name wardName, d.districtid districtId, d.name districtName, p.provinceid provinceId, p.name provinceName ");
			sql.append(" , b.path, DATE_FORMAT(b.doe, '%d/%m/%Y') doeStr");
			sql.append(" , CAST(SUBSTRING(b.branch_code,5, CHAR_LENGTH(b.branch_code) -2 ) AS UNSIGNED) sequenceCode ");
		}
		sql.append(" FROM branch b ");
		sql.append(" LEFT JOIN ward w on w.wardid = b.ward_id ");
		sql.append(" LEFT JOIN district d on w.districtid = d.districtid ");
		sql.append(" LEFT JOIN province p on p.provinceid = d.provinceid ");
		
		
		sql.append(" WHERE 1=1 ");
		if (StringUtils.isNotBlank(branch.getBranchCode())) {
			sql.append(" AND b.branch_code LIKE :branchCode");
		}
		sql.append(" ORDER BY b.id DESC");
		try {
			query = em.createNativeQuery(sql.toString());
			if (StringUtils.isNotBlank(branch.getBranchCode())) {
				query.setParameter("branchCode", "%"+branch.getBranchCode() + "%");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
		return query;
	}
	
	public Query queryLstManager(EntityManager em, Branch branch, boolean isCount) throws Exception{
		StringBuilder sql = new StringBuilder();
		Query query = null;
		if (isCount) {
			sql.append("select count(*) ");
		} else {
			sql.append("SELECT ");
			sql.append(" d.doctor_code doctorCode, ");
			sql.append(" d.doctor_name doctorName,");
			sql.append(" d.is_manager isManager, ");
			sql.append(" d.is_sub_manager isSubManager, ");
			sql.append(" d.phone phone ");
		}
		sql.append(" FROM branch b ");
		sql.append(" LEFT JOIN doctor d ON b.branch_code = d.branch_code  ");
		sql.append(" WHERE 1 = 1");
		sql.append(" AND (d.is_manager = 1 OR d.is_sub_manager = 1) ");
		
		if (StringUtils.isNotBlank(branch.getBranchCode())) {
			sql.append(" AND b.branch_code LIKE :branchCode");
		}
		
		sql.append(" ORDER BY d.id DESC");
		try {
			query = em.createNativeQuery(sql.toString());
			if (StringUtils.isNotBlank(branch.getBranchCode())) {
				query.setParameter("branchCode", "%"+branch.getBranchCode() + "%");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
		return query;
	}
	
	
	public Long getTotalSearchAd(Branch branch) {
		EntityManager em = emf.createEntityManager();
		Query query;
		Long total = null;
		try {
			query = this.querySearchAd(em, branch, true);
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
	public List<ServiceDTO> getListBranchService(String branchCode) {
		StringBuilder sql = new StringBuilder();
		Query query = null;
		sql.append("select sb.service_code serviceCode, s.service_name serviceName, b.branch_code branchCode, sb.price price "); 
		sql.append("  from services_branch sb "); 
		sql.append("  left join branch b on sb.branch_code = b.branch_code "); 
		sql.append("  left join services s on sb.service_code = s.service_code and s.is_deleted = 0 "); 
		sql.append(" where sb.branch_code =:branchCode and sb.is_deleted = 0 ");
		EntityManager em = emf.createEntityManager();
		query = em.createNativeQuery(sql.toString());
		query.setParameter("branchCode", branchCode);
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.getResultList();
		List<ServiceDTO> result = FunctionCommon.map(ServiceDTO.class, list);
		if (result != null && !result.isEmpty()) {
			em.close();
			return result;
		}
		return new ArrayList<>();
	}

	@Override
	public boolean saveServicesBranch(List<ServicesBranch> lstParam) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			for (ServicesBranch servicesBranch : lstParam) {
				em.merge(servicesBranch);
			}
			em.flush();
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			return false;
		} finally {
			em.close();
		}
	}

	@Override
	public List<Branch> getAllBranchs() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Branch> query = em.createNamedQuery("Branch.findAll", Branch.class);
		List<Branch> result = query.getResultList();
		em.close();
		return result;
	}

}
