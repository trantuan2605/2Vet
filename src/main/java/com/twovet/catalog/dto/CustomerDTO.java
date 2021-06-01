package com.twovet.catalog.dto;

import java.math.BigInteger;
import java.util.List;

public class CustomerDTO {
	private Long id;
	private String cusCode;
	private String cusName;
	private String address;
	private String phone;
	private String email;
	private String identityNo;
	private String taxCode;
	private int cusType;
	private String remark;
	private String cusTypeName;
	private List<PetDTO> petDtos;
	private BigInteger sequenceCode;
	private String wardId;
	private String wardName;
	private String districtId;
	private String districtName;
	private String provinceId;
	private String provinceName;
	
	public CustomerDTO() {
		// TODO Auto-generated constructor stub
	}
	
	// Create contructor to map result of native query to pojo
	public CustomerDTO(String cusCode, String cusName, String address,
				String phone, String identityNo, String email, String taxCode, Integer cusType, BigInteger sequenceCode) {
		// TODO Auto-generated constructor stub
		this.cusCode = cusCode;
		this.cusName = cusName;
		this.address = address;
		this.phone = phone;
		this.identityNo = identityNo;
		this.email = email;
		this.taxCode = taxCode;
		this.cusType = cusType;
		this.sequenceCode = sequenceCode;
	}
	
	// Create constructor to map customerExt
	public CustomerDTO(String cusCode, String cusName, Integer id, String wardName,
			String wardId, String districtName, String districtId, String provinceName, String provinceId) {
		this.cusCode = cusCode;
		this.cusName = cusName;
		this.id = Long.valueOf(id);
		this.wardName = wardName;
		this.wardId = wardId;
		this.districtName = districtName;
		this.districtId = districtId;
		this.provinceName = provinceName;
		this.provinceId = provinceId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCusCode() {
		return cusCode;
	}

	public void setCusCode(String cusCode) {
		this.cusCode = cusCode;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public int getCusType() {
		return cusType;
	}

	public void setCusType(int cusType) {
		this.cusType = cusType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<PetDTO> getPetDtos() {
		return petDtos;
	}

	public void setPetDtos(List<PetDTO> petDtos) {
		this.petDtos = petDtos;
	}

	public String getCusTypeName() {
		return cusTypeName;
	}

	public void setCusTypeName(String cusTypeName) {
		this.cusTypeName = cusTypeName;
	}

	public BigInteger getSequenceCode() {
		return sequenceCode;
	}

	public void setSequenceCode(BigInteger sequenceCode) {
		this.sequenceCode = sequenceCode;
	}

	public final String getWardId() {
		return wardId;
	}

	public final void setWardId(String wardId) {
		this.wardId = wardId;
	}

	public final String getWardName() {
		return wardName;
	}

	public final void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public final String getDistrictId() {
		return districtId;
	}

	public final void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public final String getDistrictName() {
		return districtName;
	}

	public final void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public final String getProvinceId() {
		return provinceId;
	}

	public final void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public final String getProvinceName() {
		return provinceName;
	}

	public final void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
}
