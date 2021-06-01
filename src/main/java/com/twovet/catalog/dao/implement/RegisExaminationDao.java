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
import com.twovet.base.constant.Constants;
import com.twovet.catalog.dao.IRegisExaminationDao;
import com.twovet.catalog.dto.RegistrationExaminationDTO;
import com.twovet.catalog.model.RegistrationExamination;

@Repository
public class RegisExaminationDao extends BaseDao implements IRegisExaminationDao{

	public RegisExaminationDao(EntityManagerFactory emf) {
		super(emf);
	}

	@Override
	public Long insert(RegistrationExamination registrationExamination) {
		EntityManager em = emf.createEntityManager();
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO registration_examination (pet_code, start_time, branch_code, doctor_code, symptom_descript, remark, end_time, all_day ) ");
		
		sql.append(" VALUES (:petCode, :startTime, :branchCode, :doctorCode, :symptomDescript, :remark, :endTime, :allDay ) ");
		Long result = 0L;
		try {
			em.getTransaction().begin();
			
			Query query = em.createNativeQuery(sql.toString());
			query.setParameter("petCode", registrationExamination.getPetCode());
			query.setParameter("startTime", registrationExamination.getStartTime());
			query.setParameter("branchCode", registrationExamination.getBranchCode());
			query.setParameter("doctorCode", registrationExamination.getDoctorCode());
			query.setParameter("symptomDescript", registrationExamination.getSymptomDescript());
			query.setParameter("remark", registrationExamination.getRemark());
			query.setParameter("endTime", registrationExamination.getEndTime());
			query.setParameter("allDay", registrationExamination.getAllDay());
			
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
	public Long edit(RegistrationExamination registrationExamination) {
		EntityManager em = emf.createEntityManager();
		StringBuilder sql = new StringBuilder();
		if (Constants.EXAMINA.DRAG_DROP.equals(registrationExamination.getEditType())) {
			sql.append("UPDATE registration_examination SET start_time =:startTime, end_time =:endTime, all_day =:allDay ");
		} else if (Constants.EXAMINA.CANCEL.equals(registrationExamination.getEditType())) {
			sql.append("UPDATE registration_examination SET pet_code =:petCode, start_time =:startTime, branch_code =:branchCode ");
			sql.append(" , doctor_code =:doctorCode, symptom_descript =:symptomDescript, remark =:remark, end_time =:endTime, status =:status ");
		} else if (Constants.EXAMINA.ACCEPTED.equals(registrationExamination.getEditType())) {
			sql.append("UPDATE registration_examination SET doctor_assign =:doctorAssign ");
		}
		
		sql.append(" WHERE id =:id ");
		Long result = 0L;
		try {
			em.getTransaction().begin();
			
			Query query = em.createNativeQuery(sql.toString());
			if (Constants.EXAMINA.DRAG_DROP.equals(registrationExamination.getEditType())) {
				query.setParameter("startTime", registrationExamination.getStartTime());
				query.setParameter("endTime", registrationExamination.getEndTime());
				query.setParameter("allDay", registrationExamination.getAllDay());
			} else if (Constants.EXAMINA.CANCEL.equals(registrationExamination.getEditType())) {
				query.setParameter("petCode", registrationExamination.getPetCode());
				query.setParameter("startTime", registrationExamination.getStartTime());
				query.setParameter("branchCode", registrationExamination.getBranchCode());
				query.setParameter("doctorCode", registrationExamination.getBranchCode());
				query.setParameter("symptomDescript", registrationExamination.getSymptomDescript());
				query.setParameter("remark", registrationExamination.getRemark());
				query.setParameter("endTime", registrationExamination.getEndTime());
				query.setParameter("status", registrationExamination.getStatus());
			} else if (Constants.EXAMINA.ACCEPTED.equals(registrationExamination.getEditType())) {
				query.setParameter("doctorAssign", registrationExamination.getDoctorAssign());
			}
			query.setParameter("id", registrationExamination.getId());
			
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
	public RegistrationExaminationDTO getDetailRegisExam(Long id) {
		StringBuilder sql = new StringBuilder();
		Query query = null;
		sql.append("SELECT\r\n" + 
				"	re.id id,\r\n" + 
				"	c.cus_code cusCode, \r\n" + 
				"	c.cus_name cusName,\r\n" + 
				"	COALESCE(re.pet_code,' ') petCode,\r\n" + 
				"	p.pet_name petName,\r\n" + 
				"	re.start_time startTime,\r\n" + 
				"	re.end_time endTime,\r\n" + 
				"	re.branch_code branchCode,\r\n" + 
				"	b.branch_name branchName,\r\n" + 
				"	re.doctor_code doctorCode,\r\n" + 
				"	d.doctor_name doctorName,\r\n" + 
				"	re.symptom_descript symptomDescript,\r\n" + 
				"	COALESCE(concat(c.cus_name,'_',p.pet_name), ' ') title,\r\n" + 
				"	re.remark remark, c.phone phone, d.majors major, d.experience experience, ");
		sql.append(" CASE  WHEN p.sex = 0 then 'Cái'  WHEN p.sex = 1 then 'Đực'  ELSE 'Chưa xác định' END  genderPet, re.doctor_assign doctorAssign, "); 
		sql.append("  d1.doctor_name doctorAssignName "); 
		sql.append(" FROM registration_examination re "); 
		sql.append(" LEFT JOIN pet p ON re.pet_code = p.pet_code "); 
		sql.append(" LEFT JOIN customer c ON c.cus_code = p.cus_code "); 
		sql.append(" LEFT JOIN branch b ON re.branch_code = b.branch_code "); 
		sql.append(" LEFT JOIN doctor d ON re.doctor_code = d.doctor_code "); 
		sql.append(" LEFT JOIN doctor d1 ON re.doctor_assign = d1.doctor_code "); 
		sql.append(" WHERE re.id =:id  AND (re.status <> 1 or re.status is null) ");
		sql.append(" GROUP BY re.id ");
		EntityManager em = emf.createEntityManager();
		query = em.createNativeQuery(sql.toString());
		query.setParameter("id", id);
		List<Object[]> list = query.getResultList();
		List<RegistrationExaminationDTO> result = FunctionCommon.map(RegistrationExaminationDTO.class, list);
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}
		em.close();
		return new RegistrationExaminationDTO();
	}

	@Override
	public List<RegistrationExaminationDTO> searchAdvance(RegistrationExamination regisExam, int firstResult,
			int maxResult) {
		EntityManager em = emf.createEntityManager();
		List<RegistrationExaminationDTO> result = new ArrayList<>();
		Query query = null;
		try {
			query = this.querySearchAd(em, regisExam, false);
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
			List<Object[]> list = query.getResultList();
			System.out.println("param: " + query.getParameters());
			result = FunctionCommon.map(RegistrationExaminationDTO.class, list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return result;
	}
	
	public Query querySearchAd(EntityManager em, RegistrationExamination regisExam, boolean isCount) {
		StringBuilder sql = new StringBuilder();
		Query query = null;
		if (isCount) {
			sql.append("select count(*) ");
		} else {
			sql.append("SELECT\r\n" + 
					"	re.id id,\r\n" + 
					"	c.cus_code cusCode, \r\n" + 
					"	c.cus_name cusName,\r\n" + 
					"	re.pet_code petCode,\r\n" + 
					"	p.pet_name petName,\r\n" + 
					"	re.start_time startTime,\r\n" + 
					"	re.end_time endTime,\r\n" + 
					"	re.branch_code branchCode,\r\n" + 
					"	b.branch_name branchName,\r\n" + 
					"	re.doctor_code doctorCode,\r\n" + 
					"	d.doctor_name doctorName,\r\n" + 
					"	re.symptom_descript symptomDescript,\r\n" + 
					"	COALESCE(concat(c.cus_name,'_',p.pet_name), ' ') title,\r\n" + 
					"	re.remark remark, re.all_day allDay, re.doctor_assign doctorAssign ");
			sql.append(" FROM registration_examination re "); 
			sql.append(" LEFT JOIN pet p ON re.pet_code = p.pet_code "); 
			sql.append(" LEFT JOIN customer c ON c.cus_code = p.cus_code "); 
			sql.append(" LEFT JOIN branch b ON re.branch_code = b.branch_code "); 
			sql.append(" LEFT JOIN doctor d ON re.doctor_code = d.doctor_code "); 
			sql.append(" WHERE (re.status != 1 OR re.status IS NULL)");
			if(StringUtils.isNotBlank(regisExam.getBranchCode())) {
				sql.append(" AND re.branch_code like :branchCode");
			}
			if(StringUtils.isNotBlank(regisExam.getDoctorCode())) {
				sql.append(" AND re.doctor_code like :doctorCode");
			}
			if(StringUtils.isNotBlank(regisExam.getStartTimeStr()) && StringUtils.isNotBlank(regisExam.getEndTimeStr())) {
				sql.append(" AND(re.start_time <=STR_TO_DATE(:endTime, '%m/%d/%Y %h:%i %p')) ");
				sql.append(" AND(re.start_time >=DATE_SUB(STR_TO_DATE(:endTime, '%m/%d/%Y %h:%i %p'), INTERVAL 2 MONTH))  ");
			}
			if (regisExam.isSearchByAssign() && !regisExam.isSearchByWaiting()) {
				sql.append(" AND (re.doctor_assign IS NOT NULL  AND CHAR_LENGTH(re.doctor_assign) > 0) ");
			}
			if (regisExam.isSearchByWaiting() && !regisExam.isSearchByAssign()) {
				sql.append(" AND (re.doctor_assign IS NULL OR CHAR_LENGTH(re.doctor_assign) = 0) ");
			}
			sql.append(" GROUP BY re.id ORDER BY re.id DESC");
			try {
				query = em.createNativeQuery(sql.toString());
				if (StringUtils.isNotBlank(regisExam.getBranchCode())) {
					query.setParameter("branchCode", "%"+regisExam.getBranchCode() + "%");
				}
				if (StringUtils.isNotBlank(regisExam.getDoctorCode())) {
					query.setParameter("doctorCode", "%"+regisExam.getDoctorCode() + "%");
				}
				if(StringUtils.isNotBlank(regisExam.getStartTimeStr()) && StringUtils.isNotBlank(regisExam.getEndTimeStr())) {
					query.setParameter("endTime", regisExam.getEndTimeStr());
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return query;
	}

//	@Override
//	public List<RegistrationExaminationDTO> getListStartEnd(RegistrationExamination registrationExamination) {
//		StringBuilder sql = new StringBuilder();
//		sql.append("SELECT re.id id, c.cus_code cusCode,  c.cus_name cusName, re.pet_code petCode, ");
//		sql.append(" p.pet_name petName, re.start_time startTime, re.end_time endTime, re.branch_code branchCode, ");
//		sql.append(" b.branch_name branchName, re.doctor_code doctorCode, d.doctor_name doctorName, re.symptom_descript symptomDescript, ");
//		sql.append(" COALESCE(concat(c.cus_name,'_',p.pet_name), ' ') title, re.remark remark FROM registration_examination re  ");
//		sql.append(" LEFT JOIN pet p ON re.pet_code = p.pet_code ");
//		sql.append(" LEFT JOIN customer c ON c.cus_code = p.cus_code ");
//		sql.append(" LEFT JOIN branch b ON re.branch_code = b.branch_code ");
//		sql.append(" LEFT JOIN doctor d ON re.doctor_code = d.doctor_code ");
//		sql.append(" WHERE (re.status != 1 OR re.status IS NULL) ");
//		sql.append(" AND(re.start_time <=STR_TO_DATE(:startTime, '%m/%d/%Y %h:%i %p')) ");
//		sql.append(" AND(re.end_time >=STR_TO_DATE(:endTime, '%m/%d/%Y %h:%i %p')) ");
//		sql.append(" GROUP BY re.id ORDER BY re.id DESC");
//		EntityManager em = emf.createEntityManager();
//		try {
//			Query query = em.createNativeQuery(sql.toString());
//			query.setParameter("startTime", registrationExamination.getStartTimeStr());
//			query.setParameter("endTime", registrationExamination.getEndTimeStr());
//			@SuppressWarnings("unchecked")
//			List<Object[]> list = query.getResultList();
//			List<RegistrationExaminationDTO> result = FunctionCommon.map(RegistrationExaminationDTO.class, list);
//			return result;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		finally {
//			em.close();
//		}
//		return new ArrayList<RegistrationExaminationDTO>();
//	}

}
