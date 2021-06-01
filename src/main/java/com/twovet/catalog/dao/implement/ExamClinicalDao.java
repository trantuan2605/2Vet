package com.twovet.catalog.dao.implement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.twovet.base.common.BaseDao;
import com.twovet.base.common.FunctionCommon;
import com.twovet.base.constant.Constants;
import com.twovet.catalog.dao.IExamClinicalDao;
import com.twovet.catalog.dto.ExamClinicalCommonDTO;
import com.twovet.catalog.dto.ExamClinicalDTO;
import com.twovet.catalog.model.ExamClinical;

@Repository
public class ExamClinicalDao extends BaseDao implements IExamClinicalDao {

	public ExamClinicalDao(EntityManagerFactory emf) {
		super(emf);
	}

	@Override
	public List<ExamClinical> getAll() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<ExamClinical> query = em.createNamedQuery("ExamClinical.findAll", ExamClinical.class);
		List<ExamClinical> lstExamClinical = query.getResultList();
		em.close();
		return lstExamClinical;
	}

	@Override
	public List<ExamClinicalCommonDTO> getExamClinical(ExamClinical exam, int firstResult, int maxResult) {
		StringBuilder sql = new StringBuilder();
		Query query = null;
		EntityManager em = emf.createEntityManager();
		List<ExamClinicalCommonDTO> result = new ArrayList<>();
		try {
		sql.append("SELECT  ec.id id,  p.sex sex,  p.pet_code petCode,  p.pet_name petName, ");
		sql.append("  c.cus_code cusCode,  c.cus_name cusName, c.phone phone, c.address address, ec.process_num processNum, ");
		sql.append("  CASE  WHEN ec.process_num =  0 THEN 'TT sơ bộ' ");
		sql.append("        WHEN ec.process_num =  1 THEN 'GĐ lâm sàng' ");
		sql.append("        WHEN ec.process_num =  2 THEN 'GĐ cận lâm sàng' ");
		sql.append("        WHEN ec.process_num =  3 THEN 'GĐ Kết luận' end processStr, ");
		sql.append("  CAST(SUBSTRING(ec.exam_clinical_code,4, CHAR_LENGTH(ec.exam_clinical_code) -2 ) AS UNSIGNED) sequenceCode, ec.exam_clinical_code examCode, DATE_FORMAT(ec.hospitalize_date, '%d/%m/%Y') hospitalizeDate, ec.pet_age petAge, DATE_FORMAT(ec.create_time, '%d/%m/%Y') createTime ");
		sql.append(" FROM exam_clinical ec ");
		sql.append(" LEFT JOIN pet p ON ec.pet_code = p.pet_code ");
		sql.append(" LEFT JOIN customer c ON c.cus_code = p.cus_code ");
		sql.append(" ORDER BY id DESC ");
		query = em.createNativeQuery(sql.toString());
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.getResultList();
		result = FunctionCommon.map(ExamClinicalCommonDTO.class, list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return result;
	}

	@Override
	public ExamClinical insert(ExamClinical exam) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(exam);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return  exam;
	}

	@Override
	public Long edit(ExamClinical exam) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(exam);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return exam.getId();
	}

	@Override
	public ExamClinical getDetailExam(String examCode) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<ExamClinical> query = em.createNamedQuery("ExamClinical.findByCode", ExamClinical.class);
		query.setParameter("examClinicalCode", examCode);
		ExamClinical result = query.getSingleResult();
		em.close();
		return result;
	}
	
	@Override
	public Long updateProcessNum(ExamClinical exam) {
		EntityManager em = emf.createEntityManager();
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE exam_clinical SET process_num =:processNum ");
		sql.append(" WHERE exam_clinical_code =:examClinicalCode ");
		Long result = 0L;
		try {
			em.getTransaction().begin();
			Query query = em.createNativeQuery(sql.toString());
			query.setParameter("processNum", Constants.PROCESS_NUM.STEP_3);
			query.setParameter("examClinicalCode", exam.getExamClinicalCode());
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

	@Override
	public ExamClinicalDTO getExamViewDetail(String examCode) {
		StringBuilder sql = new StringBuilder();
		Query query = null;
		
		sql.append("SELECT ec.pet_code petCode, ec.pet_age petAge, p.pet_name petName, ");
		sql.append(" p.cus_code cusCode, ctm.cus_name cusName, ct.cus_type_name cusTypeName, "); 
		sql.append(" ctm.address address, ctm.phone phone, b.BREED_NAME breedName, ");
		sql.append(" case when p.sex = 1 then 'Đực' when p.sex=0 then 'Cái' else 'Không xác định' end sexStr, p.color color, ");
		sql.append(" case when p.sterilized = 1 then 'Đã  triệt sản' else 'Chưa triệt sản' end sterilizedStr, ");
		sql.append(" DATE_FORMAT(ec.create_time, '%d/%m/%Y') createTimeStr, DATE_FORMAT(ec.hospitalize_date, '%d/%m/%Y') hospitalizeDateStr, ec.exam_clinical_code examClinicalCode,");
		sql.append(" ec.service_code_list serviceCodeList,	ec.body_temp bodyTemp,	v.vacine_name vacineName,");
		sql.append(" brf.branch_name branchFirstName,	br.branch_name branchName,");
		sql.append(" d.doctor_name doctorName,	ec.deposit deposit,	ec.symptom_code_list symptomCodeList, 	ec.symptom_extra symptomExtra,");
		sql.append(" ec.diagnose diagnose, ec.symptom symptom,	ec.remark");
		
		sql.append(" FROM exam_clinical ec "); 
		sql.append(" LEFT JOIN exam_sub_clinical esc ON ec.exam_clinical_code = esc.exam_clinical_code "); 
		sql.append(" LEFT JOIN conclusion c ON c.exam_clinical_code = esc.exam_clinical_code ");
		sql.append(" LEFT JOIN pet p ON p.pet_code = ec.pet_code "); 
		sql.append(" LEFT JOIN customer ctm ON ctm.cus_code = p.cus_code "); 
		sql.append(" LEFT JOIN cus_type ct ON ctm.cus_type = ct.cus_type_code "); 
		sql.append(" LEFT JOIN breed b ON b.BREED_CODE = p.pet_breed "); 
		sql.append(" LEFT JOIN vacination v ON v.vacine_code = ec.vacine_code "); 
		sql.append(" LEFT JOIN branch brf ON brf.branch_code = ec.branch_first_code "); 
		sql.append(" LEFT JOIN branch br ON br.branch_code = ec.branch_code "); 
		sql.append(" LEFT JOIN doctor d ON d.doctor_code = ec.doctor_code "); 
		
		sql.append(" WHERE ec.exam_clinical_code =:examCode");
		EntityManager em = emf.createEntityManager();
		query = em.createNativeQuery(sql.toString());
		query.setParameter("examCode", examCode);
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.getResultList();
		List<ExamClinicalDTO> result = FunctionCommon.map(ExamClinicalDTO.class, list);
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return new ExamClinicalDTO();
	}
}  

