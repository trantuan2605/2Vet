package com.twovet.catalog.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.twovet.base.common.Gender;

@Entity
@Table(name = "pet")
@NamedQueries({ 
	@NamedQuery(name = "Pet.findAll", query = "SELECT p FROM Pet p WHERE 1=1 and p.isDeleted !=1"),
//	@NamedQuery(name = "Pet.findByPetCode", query = "SELECT p FROM Pet p left join fetch p.customer c left join fetch p.specie WHERE 1=1 and p.petCode = :petCode "), 
	@NamedQuery(name = "Pet.findByPetCode", query = "SELECT p FROM Pet p left join fetch p.customer c WHERE 1=1 and p.petCode = :petCode "), 
	})
public class Pet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "pet_code")
	private String petCode;

	/*
	 * @Column(name = "cus_code") private String cusCode;
	 */

	@Column(name = "pet_type")
	private int petType;

	@Column(name = "pet_name")
	private String petName;

	@Column(name = "sex")
	private int sex;

	@Column(name = "pet_breed")
	private String petBreed;

	@Column(name = "certificate_birth")
	private String certificateBirth;

	@Column(name = "remark")
	private String remark;
	
	@Column(name = "is_deleted")
	private int isDeleted;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cus_code", referencedColumnName ="cus_code", nullable = false)
	private Customer customer;
	
//	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinColumn(name = "SPECIES_CODE", referencedColumnName ="SPECIES_CODE", insertable = false, updatable = false)
//	private String specie;
	@Column(name= "SPECIES_CODE")
	private String specCode;
	
	@Column(name= "color")
	private String color;
	
	@Column(name= "microchip_no")
	private String microchipNo;
	
	@Column(name= "vaccinated")
	private int vaccinated;
	
	@Column(name= "wormed")
	private int wormed;
	
	@Column(name= "sterilized")
	private int sterilized;
	
	@Column(name= "path")
	private String path;
	
	@Column(name= "dob")
	private Date dob;
	
	@Transient
	private String cusCodeParam;
	
	@Transient
	private MultipartFile fileImage;
	
	@Transient
	private Gender gender;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPetCode() {
		return petCode;
	}

	public void setPetCode(String petCode) {
		this.petCode = petCode;
	}

	/*
	 * public String getCusCode() { return cusCode; }
	 * 
	 * public void setCusCode(String cusCode) { this.cusCode = cusCode; }
	 */

	public int getPetType() {
		return petType;
	}

	public void setPetType(int petType) {
		this.petType = petType;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getPetBreed() {
		return petBreed;
	}

	public void setPetBreed(String petBreed) {
		this.petBreed = petBreed;
	}

	public String getCertificateBirth() {
		return certificateBirth;
	}

	public void setCertificateBirth(String certificateBirth) {
		this.certificateBirth = certificateBirth;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Customer getCustomer() {
		return customer;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getSpecCode() {
		return specCode;
	}

	public void setSpecCode(String specCode) {
		this.specCode = specCode;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMicrochipNo() {
		return microchipNo;
	}

	public void setMicrochipNo(String microchipNo) {
		this.microchipNo = microchipNo;
	}

	public int getVaccinated() {
		return vaccinated;
	}

	public void setVaccinated(int vaccinated) {
		this.vaccinated = vaccinated;
	}

	public int getWormed() {
		return wormed;
	}

	public void setWormed(int wormed) {
		this.wormed = wormed;
	}

	public int getSterilized() {
		return sterilized;
	}

	public void setSterilized(int sterilized) {
		this.sterilized = sterilized;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public MultipartFile getFileImage() {
		return fileImage;
	}

	public void setFileImage(MultipartFile fileImage) {
		this.fileImage = fileImage;
	}

	public String getCusCodeParam() {
		return cusCodeParam;
	}

	public void setCusCodeParam(String cusCodeParam) {
		this.cusCodeParam = cusCodeParam;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
