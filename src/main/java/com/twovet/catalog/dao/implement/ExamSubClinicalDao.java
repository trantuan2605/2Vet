package com.twovet.catalog.dao.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.twovet.base.common.BaseDao;
import com.twovet.base.common.FunctionCommon;
import com.twovet.catalog.dao.IExamSubClinicalDao;
import com.twovet.catalog.dto.ExamSubClinicalDTO;
import com.twovet.catalog.model.ExamSubClinical;

@Repository
public class ExamSubClinicalDao extends BaseDao implements IExamSubClinicalDao {

	public ExamSubClinicalDao(EntityManagerFactory emf) {
		super(emf);
	}

	@Override
	public Long insert(List<ExamSubClinical> examSub) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			for (ExamSubClinical examSubClinical : examSub) {
				em.persist(examSubClinical);
				em.flush();
				em.clear();
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return 1L;
	}

	@Override
	public Long edit(ExamSubClinical examSub) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(examSub);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return examSub.getId();
	}

	@Override
	public List<ExamSubClinicalDTO> getListProgress3Dto(String examClinicalCode) {
		StringBuilder sql = new StringBuilder();
		Query query = null;
		EntityManager em = emf.createEntityManager();
		List<ExamSubClinicalDTO> result = new ArrayList<>();
		try {
		sql.append("SELECT esc.id id, esc.exam_clinical_code examclinicalCode,  DATE_FORMAT(esc.execution_date, '%d/%m/%Y') executionDateStr, ");
		sql.append(" esc.branch_code branchCode, esc.doctor_code doctorCode, esc.remark remark, esc.file_name fileName, esc.path path, esc.service_code serviceCode, esc.ext_item_code extItemCode ");
		sql.append(" FROM exam_sub_clinical esc ");
		sql.append(" WHERE  esc.is_deleted <> 1 AND esc.exam_clinical_code = :examClinicalCode ");
		sql.append(" ORDER BY esc.execution_date DESC ");
		query = em.createNativeQuery(sql.toString());
		query.setParameter("examClinicalCode", examClinicalCode);
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.getResultList();
		result = FunctionCommon.map(ExamSubClinicalDTO.class, list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return result;
	}

	@Override
	public Long save(List<ExamSubClinical> examSub, List<ExamSubClinical> examSubDel) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Long result = 0L;
		try {
			if (examSub != null && !examSub.isEmpty()) {
				this.update(examSub, em);
			}
			if (examSubDel!= null && !examSubDel.isEmpty()) {
				this.delete(examSubDel, em);
			}
//			if (examSubUpd!= null && !examSubUpd.isEmpty()) {
//				this.update(examSubUpd, em);
//			}
			em.getTransaction().commit();
			result = 1L;
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			result = 0L;
		} finally {
			em.close();
		}
		return result;
	}
	
//	private void edit(List<ExamSubClinical> examSubUpd, EntityManager em) throws Exception{
//		try {
//			for (ExamSubClinical examSubClinical : examSubUpd) {
//				em.persist(examSubClinical);
//				em.flush();
//				em.clear();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		}
//	}
	
	private void delete(List<ExamSubClinical> examSubDel, EntityManager em) throws Exception{
		List<Long> lstId = examSubDel.stream().
				map(mapper -> mapper.getId()).
				collect(Collectors.toList());
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE exam_sub_clinical SET is_deleted = 1 ");
		sql.append(" WHERE id IN :ids ");
		try {
			Query query = em.createNativeQuery(sql.toString());
			query.setParameter("ids", lstId);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private void update(List<ExamSubClinical> examSub, EntityManager em) throws Exception{
		try {
			for (ExamSubClinical examSubClinical : examSub) {
				if (StringUtils.isBlank(examSubClinical.getExtItemCode())) {
					examSubClinical.setExtItemCode(null);
				}
				if (examSubClinical.getId() != null) {
					em.merge(examSubClinical);
				} else {
					em.persist(examSubClinical);
				}
				em.flush();
				em.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public ExamSubClinical getDetail(Long id) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<ExamSubClinical> query = em.createNamedQuery("ExamSubClinical.findById", ExamSubClinical.class);
		query.setParameter("id", id);
		ExamSubClinical result = query.getSingleResult();
		em.close();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExamSubClinicalDTO> getListExecuteDateByCode(ExamSubClinical examSub, int firstResult, int maxResult) {
		EntityManager em = emf.createEntityManager();
		List<ExamSubClinicalDTO> result = new ArrayList<>();
		Query query = null;
		try {
			query = this.querySearchExcecuteDate(em, examSub, false);
			if (firstResult != 0) {
				query.setFirstResult(firstResult);
			}
			if (maxResult != 0) {
				query.setMaxResults(maxResult);
			}
			List<Object[]> list = query.getResultList();
			result = FunctionCommon.map(ExamSubClinicalDTO.class, list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExamSubClinicalDTO> getListSeviceByCode(ExamSubClinical examSub, int firstResult, int maxResult) {
		EntityManager em = emf.createEntityManager();
		List<ExamSubClinicalDTO> result = new ArrayList<>();
		Query query = null;
		try {
			query = this.querySearchExcecuteService(em, examSub, false);
			if (firstResult != 0) {
				query.setFirstResult(firstResult);
			}
			if (maxResult != 0) {
				query.setMaxResults(maxResult);
			}
			List<Object[]> list = query.getResultList();
			result = FunctionCommon.map(ExamSubClinicalDTO.class, list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return result;
	}
	
	public Query querySearchExcecuteDate(EntityManager em, ExamSubClinical examSub, boolean isCount) throws Exception{
		StringBuilder sql = new StringBuilder();
		Query query = null;
		if (isCount) {
			sql.append("select count(*) ");
		} else {
			sql.append("SELECT DISTINCT esc.exam_clinical_code  examClinicalCode, DATE_FORMAT(esc.execution_date, '%d/%m/%Y') executionDateStr ");
			sql.append(" FROM ");
			sql.append(" exam_sub_clinical esc ");
		}
		
		sql.append(" WHERE 1=1 ");
		if (StringUtils.isNotBlank(examSub.getExamClinicalCode())) {
			sql.append(" AND esc.exam_clinical_code like :examClinicalCode");
		}
		sql.append(" ORDER BY esc.execution_date DESC ");
		try {
			query = em.createNativeQuery(sql.toString());
			if (StringUtils.isNotBlank(examSub.getExamClinicalCode())) {
				query.setParameter("examClinicalCode", "%"+examSub.getExamClinicalCode() + "%");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
		return query;
	}
	
	public Query querySearchExcecuteService(EntityManager em, ExamSubClinical examSub, boolean isCount) throws Exception{
		StringBuilder sql = new StringBuilder();
		Query query = null;
		if (isCount) {
			sql.append("select count(*) ");
		} else {
			sql.append("SELECT DISTINCT esc.exam_clinical_code  examClinicalCode, DATE_FORMAT(esc.execution_date, '%d/%m/%Y') executionDateStr, ");
			sql.append("grp.group_2nd_name serviceName,	esc.path path,	br.branch_name branchName,	d.doctor_name doctorName, ");
			sql.append("esc.remark remark, DATE_FORMAT(esc.create_time, '%d/%m/%Y') createTimeStr, ");
			sql.append("esc.file_name fileName,	esc.ext_item_code extItemCode ");
			sql.append(" FROM ");
			sql.append(" exam_sub_clinical esc ");
			sql.append(" LEFT JOIN branch br ON br.branch_code = esc.branch_code ");
			sql.append(" LEFT JOIN doctor d ON d.doctor_code = esc.doctor_code ");
			sql.append(" LEFT JOIN  group_item grp ON grp.group_2nd_code = esc.service_code ");
		}
		
		sql.append(" WHERE 1=1 ");
		if (StringUtils.isNotBlank(examSub.getExamClinicalCode())) {
			sql.append(" AND esc.exam_clinical_code like :examClinicalCode");
		}
		
		if (StringUtils.isNotBlank(examSub.getExecutionDateStr())) {
			sql.append(" AND DATE_FORMAT(esc.execution_date, '%d/%m/%Y') =:executionDate");
		}
		sql.append(" ORDER BY esc.execution_date DESC ");
		try {
			query = em.createNativeQuery(sql.toString());
			if (StringUtils.isNotBlank(examSub.getExamClinicalCode())) {
				query.setParameter("examClinicalCode", "%"+examSub.getExamClinicalCode() + "%");
			}
			if (StringUtils.isNotBlank(examSub.getExecutionDateStr())) {
				query.setParameter("executionDate", examSub.getExecutionDateStr());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
		return query;
	}
}
