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
import com.twovet.catalog.dao.ICustomerDao;
import com.twovet.catalog.dto.CustomerDTO;
import com.twovet.catalog.model.Customer;

@Repository
public class CustomerDao extends BaseDao implements ICustomerDao{

	public CustomerDao(EntityManagerFactory emf) {
		super(emf);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Customer> listAllCustomer() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Customer> query = em.createNamedQuery("Customer.findAll", Customer.class);
		List<Customer> result = query.getResultList();
		em.close();
		return result;
	}

	@Override
	public Long getTotal() {
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("Customer.count");
		Long total = (Long) query.getSingleResult();
		em.close();
		// TODO Auto-generated method stub
		return total != null ? total : 0L;
	}

	@Override
	public List<Customer> listCustomer(int firstResult, int maxResult) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Customer> query = em.createNamedQuery("Customer.findAll", Customer.class);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		List<Customer> result = query.getResultList();
		em.close();
		return result;
	}

	@Override
	public Long insert(Customer customer) {
		// TODO Auto-generated method stub
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(customer);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return customer.getId();
	}
	
	@Override
	public Customer getDetailCustomer(String cusCode) {
		// TODO Auto-generated method stub
		EntityManager em = emf.createEntityManager();
		TypedQuery<Customer> query = em.createNamedQuery("Customer.findByCusCode", Customer.class);
		query.setParameter("cusCode", cusCode);
		Customer result = query.getSingleResult();
		em.close();
		return result;
	}

	@Override
	public Long edit(Customer customer) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(customer);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return customer.getId();
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<CustomerDTO> searchAdvance(Customer customer, int firstResult, int maxResult) {
		EntityManager em = emf.createEntityManager();
		List<CustomerDTO> result = new ArrayList<>();
		Query query = null;
		try {
			query = this.querySearchAd(em, customer, false);
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
			List<Object[]> list = query.getResultList();
			result = FunctionCommon.map(CustomerDTO.class, list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return result;
	}
	
	public Query querySearchAd(EntityManager em, Customer customer, boolean isCount) throws Exception{
		StringBuilder sql = new StringBuilder();
		Query query = null;
		if (isCount) {
			sql.append("select count(*) ");
		} else {
			sql.append("select c.cus_code cusCode, c.cus_name cusName, c.address address, c.phone phone, c.identity_no identityNo, ");
			sql.append(" c.email email, c.tax_code taxtCode, c.cus_type cusTyp, ");
			sql.append(" CAST(SUBSTRING(c.cus_code,4, CHAR_LENGTH(c.cus_code) -2 ) AS UNSIGNED) sequenceCode ");
		}
		sql.append(" from customer c where 1=1 ");
		if (StringUtils.isNotBlank(customer.getCusCode())) {
			sql.append(" and c.cus_code like :cusCode");
		}
		if (StringUtils.isNotBlank(customer.getCusName())) {
			sql.append(" and c.cus_name like :cusName");
		}
		if (0 != customer.getCusType()) {
			sql.append(" and c.cus_type  =:cusType");
		}
		if (StringUtils.isNotBlank(customer.getPhone())) {
			sql.append(" and c.phone like :phone");
		}
		if (StringUtils.isNotBlank(customer.getIdentityNo())) {
			sql.append(" and c.identity_no like :identity");
		}
		sql.append(" order by c.id desc");
		try {
			query = em.createNativeQuery(sql.toString());
			if (StringUtils.isNotBlank(customer.getCusCode())) {
				query.setParameter("cusCode", "%"+customer.getCusCode() + "%");
			}
			if (StringUtils.isNotBlank(customer.getCusName())) {
				query.setParameter("cusName", "%"+customer.getCusName() + "%");
			}
			if (0 != customer.getCusType()) {
				query.setParameter("cusType", customer.getCusType());
			}
			if (StringUtils.isNotBlank(customer.getPhone())) {
				query.setParameter("phone", "%"+customer.getPhone() + "%");
			}
			if (StringUtils.isNotBlank(customer.getIdentityNo())) {
				query.setParameter("identity", "%"+customer.getIdentityNo() + "%");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
		return query;
	}
	
	public Long getTotalSearchAd(Customer customer) {
		EntityManager em = emf.createEntityManager();
		Query query;
		Long total = null;
		try {
			query = this.querySearchAd(em, customer, true);
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
	public CustomerDTO getDetailCustomerExt(String cusCode) {
		// TODO Auto-generated method stub
		EntityManager em = emf.createEntityManager();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT c.cus_code cusCode, c.cus_name cusName, c.id id, ");
		sql.append(" w.name wardName, w.wardid wardId,d.name districtName, d.districtid districtId, ");
		sql.append(" p.name provinceName, p.provinceid provinceId ");
		sql.append("FROM customer c ");
		sql.append("LEFT JOIN ward w on c.ward_id = w.wardid ");
		sql.append("LEFT JOIN district d on w.districtid = d.districtid ");
		sql.append("LEFT JOIN province p on p.provinceid = d.provinceid ");
		sql.append("WHERE c.cus_code =:cusCode ");
		try {
			Query query = em.createNativeQuery(sql.toString());
			query.setParameter("cusCode", cusCode);
			Object[] list = (Object[]) query.getSingleResult();
			CustomerDTO result = FunctionCommon.map(CustomerDTO.class, list);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}

}
