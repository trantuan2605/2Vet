package com.twovet.catalog.dto;

import java.math.BigInteger;

public class BreedDTO {

	private Long id;

	private String breedCode;

	private String breedName;

	private String descript;

	private String spectify;

	private String path;

	private BigInteger sequenceCode;

	public BreedDTO() {
		super();
	}

	public BreedDTO(Long id, String breedCode, String breedName, String descript, String specity, String path) {
		super();
		this.id = id;
		this.breedCode = breedCode;
		this.breedName = breedName;
		this.descript = descript;
		this.spectify = specity;
		this.path = path;
	}

	public BreedDTO(String breedCode, String breedName, String descript, String spectify, String path,
			BigInteger sequenceCode) {
		this.breedCode = breedCode;
		this.breedName = breedName;
		this.descript = descript;
		this.spectify = spectify;
		this.path = path;
		this.sequenceCode = sequenceCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getSpectify() {
		return spectify;
	}

	public void setSpectify(String specity) {
		this.spectify = specity;
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
		return "BreedDTO [id=" + id + ", breedCode=" + breedCode + ", breedName=" + breedName + ", descript=" + descript
				+ "]";
	}

}
