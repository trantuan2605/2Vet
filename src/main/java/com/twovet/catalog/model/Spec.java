package com.twovet.catalog.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "species")
@NamedQueries({ @NamedQuery(name = "Spec.findAll", query = "SELECT s FROM Spec s WHERE 1=1 ORDER by s.id desc"),
		@NamedQuery(name = "Spec.findBySpecCode", query = "SELECT s FROM Spec s WHERE 1=1 and s.specCode = :specCode ORDER BY s.id desc"),
		@NamedQuery(name = "Spec.findByBreddCode", query = "SELECT s FROM Spec s WHERE 1=1 and s.breed.breedCode = :breedCode ORDER BY s.id desc"),
		@NamedQuery(name = "Spec.count", query = "SELECT count(s.id) FROM Spec s WHERE 1=1") })
public class Spec implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "SPECIES_CODE")
	private String specCode;

	@Column(name = "SPECIES_NAME")
	private String specName;

	@Column(name = "SPECTIFY")
	private String spectify;

	@Column(name = "DESCRIPT")
	private String descript;

	@Column(name = "PATH")
	private String path;

	@Transient
	private MultipartFile fileImage;
	
	@Transient
	private String pathHidden;

	@Column(name = "IS_DELETE")
	private Boolean isDelete;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BREED_CODE", referencedColumnName = "BREED_CODE", nullable = false)
	private Breed breed;

	@Transient
	private String breedCdStr;

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

	public Breed getBreed() {
		return breed;
	}

	public void setBreed(Breed breed) {
		this.breed = breed;
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

	public MultipartFile getFileImage() {
		return fileImage;
	}

	public void setFileImage(MultipartFile fileImage) {
		this.fileImage = fileImage;
	}

	public String getBreedCdStr() {
		return breedCdStr;
	}

	public void setBreedCdStr(String breedCdStr) {
		this.breedCdStr = breedCdStr;
	}

	public String getPathHidden() {
		return pathHidden;
	}

	public void setPathHidden(String pathHidden) {
		this.pathHidden = pathHidden;
	}
	

}
