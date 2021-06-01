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
@Table(name = "quick_test")
@NamedQueries({ 
	@NamedQuery(name = "QuickTest.findAll", query = "SELECT q FROM QuickTest q WHERE 1=1 and q.isDeleted !=1"),
	})
public class QuickTest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "quick_code")
	private String quickCode;

	@Column(name = "quick_name")
	private String quickName;

	@Column(name = "descript")
	private String descript;

	@Column(name = "is_deleted")
	private int isDeleted;

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getQuickCode() {
		return quickCode;
	}


	public void setQuickCode(String quickCode) {
		this.quickCode = quickCode;
	}


	public String getQuickName() {
		return quickName;
	}


	public void setQuickName(String quickName) {
		this.quickName = quickName;
	}


	public String getDescript() {
		return descript;
	}


	public void setDescript(String descript) {
		this.descript = descript;
	}


	public int getIsDeleted() {
		return isDeleted;
	}


	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
