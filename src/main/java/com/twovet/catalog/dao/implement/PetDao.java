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
import com.twovet.catalog.dao.IPetDao;
import com.twovet.catalog.dto.PetDTO;
import com.twovet.catalog.model.Pet;

@Repository
public class PetDao extends BaseDao implements IPetDao{

	public PetDao(EntityManagerFactory emf) {
		super(emf);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Pet> getAllPets() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Pet> query = em.createNamedQuery("Pet.findAll", Pet.class);
		List<Pet> result = query.getResultList();
		em.close();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PetDTO> searchAdvance(Pet customer, int firstResult, int maxResult) {
		EntityManager em = emf.createEntityManager();
		List<PetDTO> result = new ArrayList<>();
		Query query = null;
		try {
			query = this.querySearchAd(em, customer, false);
			if (firstResult != 0) {
				query.setFirstResult(firstResult);
			}
			if (maxResult != 0) {
				query.setMaxResults(maxResult);
			}
			List<Object[]> list = query.getResultList();
			result = FunctionCommon.map(PetDTO.class, list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return result;
	}
	
	public Query querySearchAd(EntityManager em, Pet pet, boolean isCount) throws Exception{
		StringBuilder sql = new StringBuilder();
		Query query = null;
		if (isCount) {
			sql.append("select count(*) ");
		} else {
			sql.append("select p.pet_code petCode, p.pet_name petName, p.sex sex, ");
			sql.append(" p.certificate_birth certificateBirth, p.remark remark, c.cus_name cusName , c.cus_code cusCode, sp.species_name, ");
			sql.append(" case when p.sex = 1 then 'Đực' when p.sex=0 then 'Cái' else 'Không xác định' end sexStr,  b.breed_name breedName, ");
			sql.append(" case when p.wormed = 1 then 'Đã  tẩy giun' else 'Chưa tẩy giun' end wormedStr, ");
			sql.append(" case when p.vaccinated = 1 then 'Đã  tiêm chủng' else 'Chưa tiêm chủng' end vaccinatedStr, ");
			sql.append(" case when p.sterilized = 1 then 'Đã  triệt sản' else 'Chưa triệt sản' end sterilizedStr, ");
			sql.append(" p.color color, DATE_FORMAT(p.dob, '%d/%m/%Y') dobStr, p.microchip_no microchipNo, ");
			sql.append(" p.vaccinated vaccinated, p.wormed wormed, p.sterilized sterilized, ");
			sql.append(" cast(SUBSTRING(p.pet_code,4, CHAR_LENGTH(p.pet_code) -2 ) AS UNSIGNED) sequenceCode ");
		}
		sql.append(" from pet p ");
		sql.append(" left join customer c ");
		sql.append(" on p.cus_code = c.cus_code ");
		sql.append(" left join species sp ");
		sql.append(" on sp.species_code = p.species_code ");
		sql.append(" left join breed b  ");
		sql.append(" on b.breed_code = sp.breed_code ");
		sql.append(" where 1=1 ");
		if (StringUtils.isNotBlank(pet.getPetCode())) {
			sql.append(" and p.pet_code like :petCode");
		}
		if (StringUtils.isNotBlank(pet.getPetName())) {
			sql.append(" and p.pet_name like :petName");
		}
		sql.append(" and p.is_deleted <> 1");
		sql.append(" order by p.cus_code desc, p.id desc ");
		try {
			query = em.createNativeQuery(sql.toString());
			if (StringUtils.isNotBlank(pet.getPetCode())) {
				query.setParameter("petCode", "%"+pet.getPetCode() + "%");
			}
			if (StringUtils.isNotBlank(pet.getPetName())) {
				query.setParameter("petName", "%"+pet.getPetName() + "%");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
		return query;
	}
	
	public Long getTotalSearchAd(Pet pet) {
		EntityManager em = emf.createEntityManager();
		Query query;
		Long total = null;
		try {
			query = this.querySearchAd(em, pet, true);
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

//	@Override
//	public Pet getDetailPet(String petCode) {
//		EntityManager em = emf.createEntityManager();
//		TypedQuery<Pet> query = em.createNamedQuery("Pet.findByPetCode", Pet.class);
//		query.setParameter("petCode", petCode);
//		Pet result = query.getSingleResult();
//		em.close();
//		return result;
//	}
	@SuppressWarnings("unchecked")
	@Override
	public PetDTO getDetailPet(String petCode) {
		StringBuilder sql = new StringBuilder();
		Query query = null;
		sql.append("select p.pet_code petCode, p.pet_name petName, p.pet_type petType, p.sex sex, p.species_code specCode, ");
		sql.append(" p.remark remark, p.certificate_birth certificateBirth , c.cus_code cusCode, c.cus_name cusName, b.breed_code breedCode, "); 
		sql.append(" case when p.sex = 1 then 'Đực'  when p.sex = 0 then 'Cái ' else 'Không xác định' end sexStr, b.breed_name breedName, sp.species_name specName, ");
		sql.append(" p.color color, DATE_FORMAT(p.dob, '%d/%m/%Y') dobStr, p.microchip_no microchipNo, ");
		sql.append(" p.path path, p.vaccinated vaccinated, p.wormed wormed, p.sterilized sterilized, ");
		sql.append(" c.phone cusPhone, c.address cusAddress, ct.cus_type_code cusType, ct.cus_type_name cusTypeName ");
		sql.append(" from pet p "); 
		sql.append(" left join customer c on p.cus_code = c.cus_code "); 
		sql.append(" left join cus_type ct on ct.cus_type_code = c.cus_type ");
		sql.append(" left join species sp on p.species_code = sp.species_code "); 
		sql.append(" left join breed b on b.breed_code = sp.breed_code "); 
		sql.append(" where p.pet_code =:petCode");
		EntityManager em = emf.createEntityManager();
		query = em.createNativeQuery(sql.toString());
		query.setParameter("petCode", petCode);
		List<Object[]> list = query.getResultList();
		List<PetDTO> result = FunctionCommon.map(PetDTO.class, list);
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return new PetDTO();
	}

	@Override
	public Long edit(Pet pet) {
		EntityManager em = emf.createEntityManager();
		StringBuilder sql = new StringBuilder();
		sql.append("update pet set pet_name =:petName, sex =:sex, remark =:remark, species_code =:specCode, ");
		sql.append(" dob =:dob, color =:color, microchip_no =:microchipNo, vaccinated =:vaccinated, ");
		sql.append(" wormed =:wormed, sterilized =:sterilized ");
		if (StringUtils.isNotBlank(pet.getPath())) {
			sql.append(" , path =:path ");
		}
		sql.append(" where pet_code =:petCode ");
		Long result = 0L;
		try {
			em.getTransaction().begin();
			Query query = em.createNativeQuery(sql.toString());
			query.setParameter("petName", pet.getPetName());
			query.setParameter("sex", pet.getSex());
			query.setParameter("remark", pet.getRemark());
			query.setParameter("specCode", pet.getSpecCode());
			query.setParameter("dob", pet.getDob());
			query.setParameter("color", pet.getColor());
			query.setParameter("microchipNo", pet.getMicrochipNo());
			query.setParameter("vaccinated", pet.getVaccinated());
			query.setParameter("wormed", pet.getWormed());
			query.setParameter("sterilized", pet.getSterilized());
			if (StringUtils.isNotBlank(pet.getPath())) {
				query.setParameter("path", pet.getPath());
			}
			query.setParameter("petCode", pet.getPetCode());
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
	public Long insert(Pet pet) {
		EntityManager em = emf.createEntityManager();
		StringBuilder sql = new StringBuilder();
		sql.append("insert into pet (pet_code, cus_code, pet_type, pet_name, sex, ");
		sql.append( "remark, is_deleted, species_code, color, microchip_no, vaccinated, wormed, sterilized, path, dob ) ");
		sql.append(" values (:petCode, :cusCode, :petType, :petName, :sex, ");
		sql.append(" :remark, :isDeleted, :specCode, :color, :microchipNo, ");
		sql.append(" :vaccinated, :wormed, :sterilized, :path, :dob )");
		Long result = 0L;
		try {
			em.getTransaction().begin();
			Query query = em.createNativeQuery(sql.toString());
			query.setParameter("petCode", pet.getPetCode());
			query.setParameter("cusCode", pet.getCusCodeParam());
			query.setParameter("petName", pet.getPetName());
			query.setParameter("petType", pet.getPetType());
			query.setParameter("sex", pet.getSex());
			query.setParameter("remark", pet.getRemark());
			query.setParameter("isDeleted", pet.getIsDeleted());
			query.setParameter("specCode", pet.getSpecCode());
			query.setParameter("color", pet.getColor());
			query.setParameter("microchipNo", pet.getMicrochipNo());
			query.setParameter("vaccinated", pet.getVaccinated());
			query.setParameter("wormed", pet.getWormed());
			query.setParameter("sterilized", pet.getSterilized());
			query.setParameter("path", pet.getPath());
			query.setParameter("dob", pet.getDob());
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

	@SuppressWarnings("unchecked")
	@Override
	public List<PetDTO> getLstDetailPet(String cusCode) {
		StringBuilder sql = new StringBuilder();
		Query query = null;
		sql.append("SELECT p.id id, p.certificate_birth certificateBirth, p.pet_code petCode, p.pet_name petName, p.pet_type petType, ");
		sql.append("     p.remark petRemark, p.sex sex, p.species_code specCode, b.breed_code breedCode, sp.species_name spceName, b.breed_name breedName, ");
		sql.append("     CASE WHEN p.sex=1 THEN 'Đực' WHEN p.sex=0 THEN 'Cái' ELSE 'Chưa xác định' END sexStr, ");
		sql.append("     CASE WHEN p.wormed = 1 THEN 'Đã  tẩy giun' else 'Chưa tẩy giun' END wormedStr, ");
		sql.append("     CASE WHEN p.vaccinated = 1 THEN 'Đã  tiêm chủng' else 'Chưa tiêm chủng' END vaccinatedStr, ");
		sql.append("     CASE WHEN p.sterilized = 1 THEN 'Đã  triệt sản' else 'Chưa triệt sản' END sterilizedStr, ");
		sql.append("     p.color color, DATE_FORMAT(p.dob, '%d/%m/%Y') dobStr, p.microchip_no microchipNo, ");
		sql.append("     p.vaccinated vaccinated, p.wormed wormed, p.sterilized sterilized ");
		sql.append(" FROM pet p "); 
		sql.append(" LEFT JOIN species sp ON sp.species_code = p.species_code "); 
		sql.append(" LEFT JOIN breed b  on b.breed_code = sp.breed_code ");
		sql.append( " WHERE p.cus_code=:cusCode AND (  p.is_deleted != 1)");  
		EntityManager em = emf.createEntityManager();
		query = em.createNativeQuery(sql.toString());
		query.setParameter("cusCode", cusCode);
		List<Object[]> list = query.getResultList();
		List<PetDTO> result = FunctionCommon.map(PetDTO.class, list);
		if (result != null && !result.isEmpty()) {
			return result;
		}
		return new ArrayList<>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PetDTO> getLstSequenceCode() {
		StringBuilder sql = new StringBuilder();
		Query query = null;
		sql.append("SELECT p.id id, p.certificate_birth certificateBirth, p.pet_code petCode, p.pet_name petName, p.pet_type petType, ");
		sql.append("     p.remark petRemark, p.sex sex, p.species_code specCode, b.breed_code breedCode, sp.species_name spceName, b.breed_name breedName, ");
		sql.append("     CASE WHEN p.sex=1 THEN 'Đực' WHEN p.sex=0 THEN 'Cái' ELSE 'Chưa xác định' END sexStr, ");
		sql.append("     CAST(SUBSTRING(p.pet_code,4, CHAR_LENGTH(p.pet_code) -2 ) AS UNSIGNED) sequenceCode ");
		sql.append(" FROM pet p "); 
		sql.append(" LEFT JOIN species sp ON sp.species_code = p.species_code "); 
		sql.append(" LEFT JOIN breed b  on b.breed_code = sp.breed_code ");
		sql.append( " WHERE 1 = 1");  
		EntityManager em = emf.createEntityManager();
		query = em.createNativeQuery(sql.toString());
		List<Object[]> list = query.getResultList();
		List<PetDTO> result = FunctionCommon.map(PetDTO.class, list);
		if (result != null && !result.isEmpty()) {
			return result;
		}
		return new ArrayList<>();
	}

}
