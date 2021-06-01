package com.twovet.catalog.dto;

import java.math.BigInteger;

public class PetDTO {
	private Long id;
	private String petCode;
	private int petType;
	private String petName;
	private int sex;
	private String breedCode;
	private String breedName;
	private String certificateBirth;
	private String remark;
	private String cusName;
	private String cusCode;
	private String specCode;
	private String specName;
	private String sexStr;
	private BigInteger sequenceCode;
	private String wormedStr;
	private int wormed;
	private String vaccinatedStr;
	private int vaccinated;
	private String sterilizedStr;
	private int sterilized;
	private String color;
	private String dobStr;
	private String microchipNo;
	private String path;
	private String cusPhone;
	private String cusAddress;
	private int cusType;
	private String cusTypeName;

	public PetDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public PetDTO(String petCode, String petName, Integer sex, String certificateBirth, String remark, String cusName, 
			String cusCode, String specName, String sexStr, String breedName, String wormedStr, String vaccinatedStr, 
			String sterilizedStr, String color, String dobStr, String microchipNo, Integer vaccinated, Integer wormed, Integer sterilized, BigInteger sequenceCode) {
		this.petCode = petCode;
		this.petName = petName;
		this.sex = sex;
		this.certificateBirth = certificateBirth;
		this.remark = remark;
		this.cusName = cusName;
		this.cusCode = cusCode;
		this.specName = specName;
		this.sexStr = sexStr;
		this.breedName = breedName;
		this.wormedStr = wormedStr;
		this.vaccinatedStr = vaccinatedStr;
		this.sterilizedStr = sterilizedStr;
		this.color = color;
		this.dobStr = dobStr;
		this.microchipNo = microchipNo;
		this.vaccinated = vaccinated;
		this.wormed = wormed;
		this.sterilized = sterilized;
		this.sequenceCode = sequenceCode;
	}
	
	// Constructor for get detail
	public PetDTO(String petCode, String petName, Integer petType, Integer sex, String specCode, 
			String remark, String certificateBirth, String cusCode, String cusName, String breedCode, String sexStr, String breedName,
			String specName,String color, String dobStr, String microchipNo, String path, Integer vaccinated, Integer wormed, Integer sterilized, String cusPhone, String cusAddress, Integer cusType, String cusTypeName) {
		this.petCode = petCode;
		this.petName = petName;
		this.sex = sex;
		this.certificateBirth = certificateBirth;
		this.remark = remark;
		this.cusName = cusName;
		this.cusCode = cusCode;
		this.breedCode = breedCode;
		this.petType = petType;
		this.specCode = specCode;
		this.sexStr = sexStr;
		this.breedName = breedName;
		this.specName = specName;
		this.color = color;
		this.dobStr = dobStr;
		this.microchipNo = microchipNo;
		this.path = path;
		this.vaccinated = vaccinated;
		this.wormed = wormed;
		this.sterilized = sterilized;
		this.cusPhone = cusPhone;
		this.cusAddress = cusAddress;
		this.cusType = cusType;
		this.cusTypeName = cusTypeName;
	}
	
	public PetDTO(Integer id, String certificateBirth, String petCode, String petName, Integer petType, 
			String remark, Integer sex, String specCode, String breedCode, String specName, String breedName, 
			String sexStr, String wormedStr, String vaccinatedStr, String sterilizedStr, String color, String dobStr, 
			String microchipNo, Integer vaccinated, Integer wormed, Integer sterilized) {
		this.id = Long.valueOf(id);
		this.petCode = petCode;
		this.petName = petName;
		this.sex = sex;
		this.certificateBirth = certificateBirth;
		this.remark = remark;
		this.breedCode = breedCode;
		this.petType = petType;
		this.specCode = specCode;
		this.specName = specName;
		this.breedName = breedName;
		this.sexStr = sexStr;
		this.wormedStr = wormedStr;
		this.vaccinatedStr = vaccinatedStr;
		this.sterilizedStr = sterilizedStr;
		this.color = color;
		this.dobStr = dobStr;
		this.microchipNo = microchipNo;
		this.vaccinated = vaccinated;
		this.wormed = wormed;
		this.sterilized = sterilized;
	}
	
	// constructor for get list sequencecode
	public PetDTO(Integer id, String certificateBirth, String petCode, String petName, Integer petType, 
			String remark, Integer sex, String specCode, String breedCode, String specName, String breedName, String sexStr, BigInteger sequenceCode) {
		this.id = Long.valueOf(id);
		this.petCode = petCode;
		this.petName = petName;
		this.sex = sex;
		this.certificateBirth = certificateBirth;
		this.remark = remark;
		this.breedCode = breedCode;
		this.petType = petType;
		this.specCode = specCode;
		this.specName = specName;
		this.breedName = breedName;
		this.sexStr = sexStr;
		this.sequenceCode = sequenceCode;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPetCode() {
		return petCode;
	}

	public void setPetCode(String petCode) {
		this.petCode = petCode;
	}

	public int getPetType() {
		return petType;
	}

	public void setPetType(int petType) {
		this.petType = petType;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
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

	public String getCertificateBirth() {
		return certificateBirth;
	}

	public void setCertificateBirth(String certificateBirth) {
		this.certificateBirth = certificateBirth;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCusCode() {
		return cusCode;
	}

	public void setCusCode(String cusCode) {
		this.cusCode = cusCode;
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

	public String getSexStr() {
		return sexStr;
	}

	public void setSexStr(String sexStr) {
		this.sexStr = sexStr;
	}

	public BigInteger getSequenceCode() {
		return sequenceCode;
	}

	public void setSequenceCode(BigInteger sequenceCode) {
		this.sequenceCode = sequenceCode;
	}

	public final String getWormedStr() {
		return wormedStr;
	}

	public final void setWormedStr(String wormedStr) {
		this.wormedStr = wormedStr;
	}

	public final String getVaccinatedStr() {
		return vaccinatedStr;
	}

	public final void setVaccinatedStr(String vaccinatedStr) {
		this.vaccinatedStr = vaccinatedStr;
	}

	public final String getSterilizedStr() {
		return sterilizedStr;
	}

	public final void setSterilizedStr(String sterilizedStr) {
		this.sterilizedStr = sterilizedStr;
	}

	public final String getColor() {
		return color;
	}

	public final void setColor(String color) {
		this.color = color;
	}

	public final String getDobStr() {
		return dobStr;
	}

	public final void setDobStr(String dobStr) {
		this.dobStr = dobStr;
	}

	public final String getMicrochipNo() {
		return microchipNo;
	}

	public final void setMicrochipNo(String microchipNo) {
		this.microchipNo = microchipNo;
	}

	public final int getWormed() {
		return wormed;
	}

	public final void setWormed(int wormed) {
		this.wormed = wormed;
	}

	public final int getVaccinated() {
		return vaccinated;
	}

	public final void setVaccinated(int vaccinated) {
		this.vaccinated = vaccinated;
	}

	public final int getSterilized() {
		return sterilized;
	}

	public final void setSterilized(int sterilized) {
		this.sterilized = sterilized;
	}

	public final String getPath() {
		return path;
	}

	public final void setPath(String path) {
		this.path = path;
	}

	public String getCusPhone() {
		return cusPhone;
	}

	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}

	public String getCusAddress() {
		return cusAddress;
	}

	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
	}

	public int getCusType() {
		return cusType;
	}

	public void setCusType(Integer cusType) {
		this.cusType = cusType;
	}

	public String getCusTypeName() {
		return cusTypeName;
	}

	public void setCusTypeName(String cusTypeName) {
		this.cusTypeName = cusTypeName;
	}
	

}
