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
@Table(name = "stool_test")
@NamedQueries({ @NamedQuery(name = "Stool.findAll", query = "SELECT b FROM Stool b WHERE 1=1 ORDER BY b.id desc"),
		@NamedQuery(name = "Stool.findByStoolCode", query = "SELECT b FROM Stool b WHERE 1=1 and b.stoolCode = :stoolCode ORDER BY b.id desc"),
		@NamedQuery(name = "Stool.count", query = "SELECT count(b.id) FROM Stool b WHERE 1=1") })
public class Stool implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "STOOL_CODE")
	private String stoolCode;

	@Column(name = "STOOL_NAME")
	private String stoolName;

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

	public String getStoolCode() {
		return stoolCode;
	}

	public void setStoolCode(String stoolCode) {
		this.stoolCode = stoolCode;
	}

	public String getStoolName() {
		return stoolName;
	}

	public void setStoolName(String stoolName) {
		this.stoolName = stoolName;
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
