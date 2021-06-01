package com.twovet.catalog.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.google.api.client.util.DateTime;

@Entity
@Table(name = "doctor")
@NamedQueries({ @NamedQuery(name = "Doctor.findAll", query = "SELECT d FROM Doctor d WHERE 1=1 ORDER by d.id desc"),
		@NamedQuery(name = "Doctor.findByDoctorCode", query = "SELECT d FROM Doctor d WHERE 1=1 and d.doctorCode = :doctorCode order by d.id desc"),
		@NamedQuery(name = "Doctor.count", query = "SELECT count(d.id) FROM Doctor d WHERE 1=1") })
public class Doctor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "doctor_code")
	private String doctorCode;

	@Column(name = "doctor_name")
	private String doctorName;

	@Column(name = "gender")
	private Integer gender;

	@Column(name = "cmnd")
	private String cmnd;

	@Column(name = "address")
	private String address;

	@Column(name = "dob")
	private Date dob;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@Column(name = "majors")
	private String majors;

	@Column(name = "experience")
	private String experience;

	@Column(name = "descript")
	private String descript;

	@Column(name = "ward_id")
	private String wardId;

	@Column(name = "path")
	private String path;

	@Column(name = "dow")
	private Date dow;

	@Column(name = "bank_no")
	private String bankNo;

	@Column(name = "position")
	private String position;

	@Column(name = "level")
	private String level;

	@Column(name = "country")
	private String country;

	@Column(name = "branch_code")
	private String branchCode;

	@Column(name = "is_manager")
	private int isManager;

	@Column(name = "is_sub_manager")
	private int isSubManager;

	@Transient
	private MultipartFile fileImage;
	@Transient
	private String pathHidden;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDoctorCode() {
		return doctorCode;
	}

	public void setDoctorCode(String doctorCode) {
		this.doctorCode = doctorCode;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
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

	public String getMajors() {
		return majors;
	}

	public void setMajors(String majors) {
		this.majors = majors;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getWardId() {
		return wardId;
	}

	public void setWardId(String wardId) {
		this.wardId = wardId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getDow() {
		return dow;
	}

	public void setDow(Date dow) {
		this.dow = dow;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public MultipartFile getFileImage() {
		return fileImage;
	}

	public void setFileImage(MultipartFile fileImage) {
		this.fileImage = fileImage;
	}

	public final String getBranchCode() {
		return branchCode;
	}

	public final void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public final String getPathHidden() {
		return pathHidden;
	}

	public final void setPathHidden(String pathHidden) {
		this.pathHidden = pathHidden;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getIsManager() {
		return isManager;
	}

	public void setIsManager(int isManager) {
		this.isManager = isManager;
	}

	public int getIsSubManager() {
		return isSubManager;
	}

	public void setIsSubManager(int isSubManager) {
		this.isSubManager = isSubManager;
	}

}
