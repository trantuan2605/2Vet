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

import com.example.model.AppUser;
import com.example.model.UserRole;
import com.twovet.base.common.BaseDao;
import com.twovet.base.common.FunctionCommon;
import com.twovet.base.constant.Constants;
import com.twovet.catalog.dao.IDoctorDao;
import com.twovet.catalog.dto.CustomerDTO;
import com.twovet.catalog.dto.DoctorDTO;
import com.twovet.catalog.model.Doctor;

@Repository
public class DoctorDao extends BaseDao implements IDoctorDao{

	public DoctorDao(EntityManagerFactory emf) {
		super(emf);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long insert(Doctor doctor) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(doctor);
			// insert table app_user
			AppUser user = new AppUser();
			user.setUsername(doctor.getDoctorCode());
			user.setEncryptedPassword(Constants.USER_DEFAULT.PASSWORD_DEFAULT);
			user.setEnabled(Constants.USER_DEFAULT.ENABLE_DEFAULT);
			user.setDeleteFlg(Constants.USER_DEFAULT.DELETE_FLAG_DEFAULT);
			user.setDoctor(doctor);
			em.persist(user);
			// insert table user_role
			UserRole userRole = new UserRole();
			userRole.setRoleId(Constants.ROLE.ROLE_ID_DEFAULT);
			userRole.setUserId(user.getId());
			em.persist(userRole);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return doctor.getId();
	}
	
	@Override
	public Doctor getDetailDoctor(String doctorCode) {
		// TODO Auto-generated method stub
		EntityManager em = emf.createEntityManager();
		TypedQuery<Doctor> query = em.createNamedQuery("Doctor.findByDoctorCode", Doctor.class);
		query.setParameter("doctorCode", doctorCode);
		Doctor result = query.getSingleResult();
		em.close();
		return result;
	}

	@Override
	public Long edit(Doctor doctor) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(doctor);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return doctor.getId();
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<DoctorDTO> searchAdvance(Doctor doctor, int firstResult, int maxResult) {
		EntityManager em = emf.createEntityManager();
		List<DoctorDTO> result = new ArrayList<>();
		Query query = null;
		try {
			query = this.querySearchAd(em, doctor, false);
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
			List<Object[]> list = query.getResultList();
			result = FunctionCommon.map(DoctorDTO.class, list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return result;
	}
	
	public Query querySearchAd(EntityManager em, Doctor doctor, boolean isCount) throws Exception{
		StringBuilder sql = new StringBuilder();
		Query query = null;
		if (isCount) {
			sql.append("select count(*) ");
		} else {
			sql.append("select d.doctor_code doctorCode, d.doctor_name doctorName, d.address address, d.phone phone, d.cmnd cmnd, ");
			sql.append(" d.email email, d.experience experience, DATE_FORMAT(d.dob, '%d/%m/%Y') dobStr, d.descript descript, d.gender gender, d.majors majors, ");
			sql.append(" case when d.gender= 1 then 'Nam' when d.gender = 0 then 'Nữ' else 'Chưa xác định' end genderStr, ");
			sql.append(" cast(substring(d.doctor_code,5, char_length(d.doctor_code) -2 ) as unsigned) sequenceCode ");
		}
		sql.append(" from doctor d where 1=1 ");
		if (StringUtils.isNotBlank(doctor.getDoctorCode())) {
			sql.append(" and d.doctor_code like :doctorCode");
		}
		if (StringUtils.isNotBlank(doctor.getDoctorName())) {
			sql.append(" and d.doctor_name like :doctorName");
		}
		if (StringUtils.isNotBlank(doctor.getPhone())) {
			sql.append(" and d.phone like :phone");
		}
		if (StringUtils.isNotBlank(doctor.getCmnd())) {
			sql.append(" and d.cmnd like :cmnd");
		}
		sql.append(" order by d.id desc");
		try {
			query = em.createNativeQuery(sql.toString());
			if (StringUtils.isNotBlank(doctor.getDoctorCode())) {
				query.setParameter("doctorCode", "%"+doctor.getDoctorCode() + "%");
			}
			if (StringUtils.isNotBlank(doctor.getDoctorName())) {
				query.setParameter("doctorName", "%"+doctor.getDoctorName() + "%");
			}
			if (StringUtils.isNotBlank(doctor.getPhone())) {
				query.setParameter("phone", "%"+doctor.getPhone() + "%");
			}
			if (StringUtils.isNotBlank(doctor.getCmnd())) {
				query.setParameter("cmnd", "%"+doctor.getCmnd() + "%");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
		return query;
	}
	
	public Long getTotalSearchAd(Doctor doctor) {
		EntityManager em = emf.createEntityManager();
		Query query;
		Long total = null;
		try {
			query = this.querySearchAd(em, doctor, true);
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
	public List<DoctorDTO> getDoctorNotInSchedule(String start, String end) {
		EntityManager em = emf.createEntityManager();
		List<DoctorDTO> result = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT d.doctor_code doctorCode, d.doctor_name doctorName, d.address address, d.phone phone, d.cmnd cmnd, ");
		sql.append(" d.email email, d.experience experience, DATE_FORMAT(d.dob, '%d/%m/%Y') dobStr, d.descript descript, d.gender gender, d.majors majors, ");
		sql.append(" CASE WHEN d.gender= 1 then 'Nam' WHEN d.gender = 0 THEN 'Nữ' ELSE 'Chưa xác định' END genderStr ");
		sql.append(" FROM doctor d WHERE 1=1 ");
		sql.append(" AND ");
		sql.append(" d.doctor_code NOT IN ( ");
		sql.append("    SELECT re.doctor_code doctorCode FROM registration_examination re  WHERE (re.status != 1 OR re.status IS NULL) ");
		sql.append("    AND(re.start_time <=STR_TO_DATE(:startTime, '%m/%d/%Y %h:%i %p')) ");
		sql.append("    AND(re.end_time >=STR_TO_DATE(:endTime, '%m/%d/%Y %h:%i %p')) ");
		sql.append("  GROUP BY re.id ORDER BY re.id DESC)");
		Query query = null;
		try {
			query = em.createNativeQuery(sql.toString());
			query.setParameter("startTime", start);
			query.setParameter("endTime", end);
			@SuppressWarnings("unchecked")
			List<Object[]> list = query.getResultList();
			result = FunctionCommon.map(DoctorDTO.class, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return result;
	}

	@Override
	public DoctorDTO getDetailDoctorExt(String doctorCode) {
		EntityManager em = emf.createEntityManager();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT do.doctor_code doctorCode, do.doctor_name doctorName, ");
		sql.append(" w.name wardName, w.wardid wardId,d.name districtName, d.districtid districtId, ");
		sql.append(" p.name provinceName, p.provinceid provinceId ");
		sql.append("FROM doctor do ");
		sql.append("LEFT JOIN ward w on do.ward_id = w.wardid ");
		sql.append("LEFT JOIN district d on w.districtid = d.districtid ");
		sql.append("LEFT JOIN province p on p.provinceid = d.provinceid ");
		sql.append("WHERE do.doctor_code =:doctorCode ");
		try {
			Query query = em.createNativeQuery(sql.toString());
			query.setParameter("doctorCode", doctorCode);
			Object[] list = (Object[]) query.getSingleResult();
			DoctorDTO result = FunctionCommon.map(DoctorDTO.class, list);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}

	@Override
	public List<DoctorDTO> getLstDoctorByBranch(String branchCode) {
		EntityManager em = emf.createEntityManager();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT do.doctor_code doctorCode, do.doctor_name doctorName ");
		sql.append("FROM doctor do ");
		sql.append("WHERE do.branch_code =:branchCode ");
		try {
			List<DoctorDTO> result = new ArrayList<>();
			Query query = em.createNativeQuery(sql.toString());
			query.setParameter("branchCode", branchCode);
			List<Object[]> list = query.getResultList();
			result = FunctionCommon.map(DoctorDTO.class, list);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}

}
