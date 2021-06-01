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
@Table(name = "vacination")
@NamedQueries({ 
	@NamedQuery(name = "Vacination.findAll", query = "SELECT v FROM Vacination v WHERE 1=1 and v.isDeleted !=1"),
	})
public class Vacination implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "vacine_code")
	private String vacineCode;

	@Column(name = "vacine_name")
	private String vacineName;

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

	public String getVacineCode() {
		return vacineCode;
	}

	public void setVacineCode(String vacineCode) {
		this.vacineCode = vacineCode;
	}

	public String getVacineName() {
		return vacineName;
	}

	public void setVacineName(String vacineName) {
		this.vacineName = vacineName;
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
