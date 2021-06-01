package com.twovet.catalog.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

@Entity
@Table(name = "branch")
@NamedQueries({ @NamedQuery(name = "Branch.findAll", query = "SELECT b FROM Branch b WHERE 1=1 ORDER by b.id desc"),
		@NamedQuery(name = "Branch.findByBranchCode", query = "SELECT b FROM Branch b WHERE 1=1 and b.branchCode = :branchCode order by b.id desc"),
		@NamedQuery(name = "Branch.count", query = "SELECT count(b.id) FROM Branch b WHERE 1=1") })
public class Branch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "branch_code")
	private String branchCode;

	@Column(name = "branch_name")
	private String branchName;

	@Column(name = "address")
	private String address;

	@Column(name = "phone")
	private String phone;

	@Column(name = "descript")
	private String descript;

	@Column(name = "ward_id")
	private String wardId;

	@Column(name = "manager_id")
	private String managerId;

	@Column(name = "sub_manager_id")
	private String subManagerId;

	@Column(name = "doe")
	private Date doe;

	@Column(name = "path")
	private String path;

	@Transient
	List<ServicesBranch> lstServiceBranch;

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

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
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

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public List<ServicesBranch> getLstServiceBranch() {
		return lstServiceBranch;
	}

	public void setLstServiceBranch(List<ServicesBranch> lstServiceBranch) {
		this.lstServiceBranch = lstServiceBranch;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getWardId() {
		return wardId;
	}

	public void setWardId(String wardId) {
		this.wardId = wardId;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getSubManagerId() {
		return subManagerId;
	}

	public void setSubManagerId(String subManagerId) {
		this.subManagerId = subManagerId;
	}

	public Date getDoe() {
		return doe;
	}

	public void setDoe(Date doe) {
		this.doe = doe;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public MultipartFile getFileImage() {
		return fileImage;
	}

	public void setFileImage(MultipartFile fileImage) {
		this.fileImage = fileImage;
	}

	public String getPathHidden() {
		return pathHidden;
	}

	public void setPathHidden(String pathHidden) {
		this.pathHidden = pathHidden;
	}
	

}
