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
@Table(name = "xq")
@NamedQueries({ @NamedQuery(name = "XRay.findAll", query = "SELECT b FROM XRay b WHERE 1=1 ORDER by b.id desc"),
		@NamedQuery(name = "XRay.findByXqCode", query = "SELECT b FROM XRay b WHERE 1=1 and b.xQCode = :xQCode ORDER BY b.id desc"),
		@NamedQuery(name = "XRay.count", query = "SELECT count(b.id) FROM XRay b WHERE 1=1") })
public class XRay implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "XQ_CODE")
	private String xQCode;

	@Column(name = "XQ_NAME")
	private String xQName;

	@Column(name = "DESCRIPT")
	private String descript;

	@Column(name = "IS_DELETE")
	private int isDelete;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getxQCode() {
		return xQCode;
	}

	public void setxQCode(String xQCode) {
		this.xQCode = xQCode;
	}

	public String getxQName() {
		return xQName;
	}

	public void setxQName(String xQName) {
		this.xQName = xQName;
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
