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
@Table(name = "urine_test")
@NamedQueries({ @NamedQuery(name = "Urine.findAll", query = "SELECT b FROM Urine b WHERE 1=1 ORDER BY b.id desc"),
		@NamedQuery(name = "Urine.findByUrineCode", query = "SELECT b FROM Urine b WHERE 1=1 and b.urineCode = :urineCode ORDER BY b.id desc"),
		@NamedQuery(name = "Urine.count", query = "SELECT count(b.id) FROM Urine b WHERE 1=1") })
public class Urine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "URINE_CODE")
	private String urineCode;

	@Column(name = "URINE_NAME")
	private String urineName;

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

	public String getUrineCode() {
		return urineCode;
	}

	public void setUrineCode(String urineCode) {
		this.urineCode = urineCode;
	}

	public String getUrineName() {
		return urineName;
	}

	public void setUrineName(String urineName) {
		this.urineName = urineName;
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
