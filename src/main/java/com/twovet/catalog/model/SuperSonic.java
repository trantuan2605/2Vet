package com.twovet.catalog.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "supersonic")
@NamedQueries({
		@NamedQuery(name = "SuperSonic.findAll", query = "SELECT b FROM SuperSonic b WHERE 1=1 ORDER BY b.id desc"),
		@NamedQuery(name = "SuperSonic.findBySuperSonicCode", query = "SELECT b FROM SuperSonic b WHERE 1=1 and b.superSonicCode = :superSonicCode ORDER BY b.id desc"),
		@NamedQuery(name = "SuperSonic.count", query = "SELECT count(b.id) FROM SuperSonic b WHERE 1=1") })
public class SuperSonic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "SUPERSONIC_CODE")
	private String superSonicCode;

	@Column(name = "SUPERSONIC_NAME")
	private String superSonicName;

	@Column(name = "DESCRIPT")
	private String descript;

	@Column(name = "IS_DELETE", columnDefinition = "int  default 0")
	private int isDelete;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSuperSonicCode() {
		return superSonicCode;
	}

	public void setSuperSonicCode(String superSonicCode) {
		this.superSonicCode = superSonicCode;
	}

	public String getSuperSonicName() {
		return superSonicName;
	}

	public void setSuperSonicName(String superSonicName) {
		this.superSonicName = superSonicName;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

}
