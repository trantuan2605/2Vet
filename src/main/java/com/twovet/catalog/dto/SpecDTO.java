package com.twovet.catalog.dto;

import java.math.BigInteger;

public class SpecDTO {

	private Long id;
	private String specCode;
	private String specName;
	private String descript;
	private Boolean isDelete;
	private String breedCode;
	private String breedName;
	private String spectify;
	private String path;
	private BigInteger sequenceCode;

	public SpecDTO(Long id, String specCode, String specName, String descript, Boolean isDelete, String breedCode,
			String breedName, String spectify, String path) {
		super();
		this.id = id;
		this.specCode = specCode;
		this.specName = specName;
		this.descript = descript;
		this.isDelete = isDelete;
		this.breedCode = breedCode;
		this.breedName = breedName;
		this.spectify = spectify;
		this.path = path;
	}

	public SpecDTO(String specCode, String specName, String descript, String breedCode, String breedName,
			String spectify, String path, BigInteger sequenceCode) {
		super();
		this.specCode = specCode;
		this.specName = specName;
		this.descript = descript;
		this.breedCode = breedCode;
		this.breedName = breedName;
		this.spectify = spectify;
		this.path = path;
		this.sequenceCode = sequenceCode;
	}

	public SpecDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSpecCode() {
		return specCode;
	}

	public void setSpecCode(String specCode) {
		this.specCode = specCode;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getBreedCode() {
		return breedCode;
	}

	public void setBreedCode(String breedCode) {
		this.breedCode = breedCode;
	}

	public String getBreedName() {
		return breedName;
	}

	public void setBreedName(String breedName) {
		this.breedName = breedName;
	}

	public String getSpectify() {
		return spectify;
	}

	public void setSpectify(String spectify) {
		this.spectify = spectify;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public BigInteger getSequenceCode() {
		return sequenceCode;
	}

	public void setSequenceCode(BigInteger sequenceCode) {
		this.sequenceCode = sequenceCode;
	}

	@Override
	public String toString() {
		return "SpecDTO [id=" + id + ", specCode=" + specCode + ", specName=" + specName + ", descript=" + descript
				+ ", isDelete=" + isDelete + ", breedCode=" + breedCode + ", breedName=" + breedName + ", getId()="
				+ getId() + ", getSpecCode()=" + getSpecCode() + ", getSpecName()=" + getSpecName() + ", getDescript()="
				+ getDescript() + ", getIsDelete()=" + getIsDelete() + ", getBreedCode()=" + getBreedCode()
				+ ", getBreedName()=" + getBreedName() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
