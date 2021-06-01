package com.twovet.catalog.dto;

import java.math.BigInteger;

public class ServiceDTO {
	private Long id;
	private String serviceCode;
	private String serviceName;
	private String branchCode;
	private String branchName;
	private Double price;
	private String descript;
	private int checkedStt;
	private String idBS;

	public ServiceDTO() {
		// TODO Auto-generated constructor stub
	}
	
	// constructor for get all service within bracnhcode
	public ServiceDTO(Integer id, String serviceCode, String serviceName, String descript, BigInteger checkedStt, Double price, String idBS, String branchCode ) {
		this.id = Long.valueOf(id);
		this.serviceCode = serviceCode;
		this.serviceName = serviceName;
		this.descript = descript;
		this.checkedStt = checkedStt != null ? checkedStt.intValue() : 0;
		this.price = price;
		this.idBS = idBS;
		this.branchCode = branchCode;
	}
	
	// constructor for get all service within bracnhcode Integer
		public ServiceDTO(Integer id, String serviceCode, String serviceName, String descript, Integer checkedStt, Double price, String idBS, String branchCode ) {
			this.id = Long.valueOf(id);
			this.serviceCode = serviceCode;
			this.serviceName = serviceName;
			this.descript = descript;
			this.checkedStt = checkedStt != null ? checkedStt.intValue() : 0;
			this.price = price;
			this.idBS = idBS;
			this.branchCode = branchCode;
		}
	
	// constructor for get All service without branchCode
	public ServiceDTO(Integer id, String serviceCode, String serviceName, String descript) {
		this.id = Long.valueOf(id);
		this.serviceCode = serviceCode;
		this.serviceName = serviceName;
		this.descript = descript;
	}
	
	// constructor for get branch service
	public ServiceDTO(String serviceCode, String serviceName, String branchCode, Double price) {
		this.serviceCode = serviceCode;
		this.serviceName = serviceName;
		this.branchCode = branchCode;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public int getCheckedStt() {
		return checkedStt;
	}

	public void setCheckedStt(int checkedStt) {
		this.checkedStt = checkedStt;
	}

	public String getIdBS() {
		return idBS;
	}

	public void setIdBS(String idBS) {
		this.idBS = idBS;
	}
	
}
