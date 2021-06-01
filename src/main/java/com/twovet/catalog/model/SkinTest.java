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
@Table(name = "skintest")
@NamedQueries({ @NamedQuery(name = "SkinTest.findAll", query = "SELECT b FROM SkinTest b WHERE 1=1 ORDER BY b.id desc"),
		@NamedQuery(name = "SkinTest.findBySkinTestCode", query = "SELECT b FROM SkinTest b WHERE 1=1 and b.skinTestCode = :skinTestCode ORDER BY b.id desc"),
		@NamedQuery(name = "SkinTest.count", query = "SELECT count(b.id) FROM SkinTest b WHERE 1=1") })
public class SkinTest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "SKINTEST_CODE")
	private String skinTestCode;

	@Column(name = "SKINTEST_NAME")
	private String skinTestName;

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

	public String getSkinTestCode() {
		return skinTestCode;
	}

	public void setSkinTestCode(String skinTestCode) {
		this.skinTestCode = skinTestCode;
	}

	public String getSkinTestName() {
		return skinTestName;
	}

	public void setSkinTestName(String skinTestName) {
		this.skinTestName = skinTestName;
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
