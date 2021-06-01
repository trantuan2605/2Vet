package com.twovet.widget.model;

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
@Table(name = "app_group")
@NamedQueries({ 
	@NamedQuery(name = "AppGroup.findAll", query = "SELECT ag FROM AppGroup ag WHERE 1=1 ORDER by ag.id desc"),
	@NamedQuery(name = "AppGroup.findById", query = "SELECT ag FROM AppGroup ag WHERE 1=1 AND ag.id =:groupId"),
	})
public class AppGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "group_name")
	private String groupName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
