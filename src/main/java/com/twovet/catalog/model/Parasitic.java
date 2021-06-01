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
@Table(name = "parasitic_test")
@NamedQueries({
		@NamedQuery(name = "Parasitic.findAll", query = "SELECT b FROM Parasitic b WHERE 1=1 ORDER BY b.id desc"),
		@NamedQuery(name = "Parasitic.findByParasiticTestCode", query = "SELECT b FROM Parasitic b WHERE 1=1 and b.parasiticTestCode = :parasiticTestCode ORDER BY b.id desc"),
		@NamedQuery(name = "Parasitic.count", query = "SELECT count(b.id) FROM Parasitic b WHERE 1=1") })
public class Parasitic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "PARASITIC_TEST_CODE")
	private String parasiticTestCode;

	@Column(name = "PARASITIC_TEST_NAME")
	private String parasiticTestName;

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

	public String getParasiticTestCode() {
		return parasiticTestCode;
	}

	public void setParasiticTestCode(String parasiticTestCode) {
		this.parasiticTestCode = parasiticTestCode;
	}

	public String getParasiticTestName() {
		return parasiticTestName;
	}

	public void setParasiticTestName(String parasiticTestName) {
		this.parasiticTestName = parasiticTestName;
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
