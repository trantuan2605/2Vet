package com.twovet.catalog.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.twovet.base.common.Gender;

@Entity
@Table(name = "customer")
@NamedQueries({ 
//	@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c LEFT JOIN Pet p ON p.isDeleted != 1 WHERE 1=1 ORDER by c.id desc"),
	@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c WHERE 1=1 ORDER by c.id desc"),
	@NamedQuery(name = "Customer.findByCusCode", query = "SELECT c FROM Customer c WHERE 1=1 and c.cusCode = :cusCode order by c.id desc"), 
	@NamedQuery(name = "Customer.count", query = "SELECT count(c.id) FROM Customer c WHERE 1=1")
	})
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "cus_code")
	private String cusCode;

	@Column(name = "cus_name")
	private String cusName;

	@Column(name = "address")
	private String address;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@Column(name = "identity_no")
	private String identityNo;

	@Column(name = "cus_type")
	private int cusType;

	@Column(name = "tax_code")
	private String taxCode;

	@Column(name = "remark")
	private String remark;
	
	@Column(name = "ward_id")
	private String wardId;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "customer", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted != 1")
	private List<Pet> lstPet;
	
	@Transient
	private List<CustomerType> customerTypes;
	@Transient
	private Gender gender;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCusCode() {
		return cusCode;
	}

	public void setCusCode(String cusCode) {
		this.cusCode = cusCode;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public int getCusType() {
		return cusType;
	}

	public void setCusType(int cusType) {
		this.cusType = cusType;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Pet> getLstPet() {
		return lstPet;
	}

	public void setLstPet(List<Pet> lstPet) {
		this.lstPet = lstPet;
	}

	public List<CustomerType> getCustomerTypes() {
		return customerTypes;
	}

	public void setCustomerTypes(List<CustomerType> customerTypes) {
		this.customerTypes = customerTypes;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public final String getWardId() {
		return wardId;
	}

	public final void setWardId(String wardId) {
		this.wardId = wardId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
