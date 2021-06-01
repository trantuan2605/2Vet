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
@Table(name = "SPUTUM_TEST")
@NamedQueries({ @NamedQuery(name = "Sputum.findAll", query = "SELECT b FROM Sputum b WHERE 1=1 ORDER BY b.id desc"),
		@NamedQuery(name = "Sputum.findBySputumCode", query = "SELECT b FROM Sputum b WHERE 1=1 and b.sputumCode = :sputumCode ORDER BY b.id desc"),
		@NamedQuery(name = "Sputum.count", query = "SELECT count(b.id) FROM Sputum b WHERE 1=1") })
public class Sputum implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "SPUTUM_CODE")
	private String sputumCode;

	@Column(name = "SPUTUM_NAME")
	private String sputumName;

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

	public String getSputumCode() {
		return sputumCode;
	}

	public void setSputumCode(String sputumCode) {
		this.sputumCode = sputumCode;
	}

	public String getSputumName() {
		return sputumName;
	}

	public void setSputumName(String sputumName) {
		this.sputumName = sputumName;
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
