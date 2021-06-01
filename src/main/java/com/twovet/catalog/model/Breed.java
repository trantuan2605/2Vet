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
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "breed")
@NamedQueries({ @NamedQuery(name = "Breed.findAll", query = "SELECT b FROM Breed b WHERE 1=1 ORDER by b.id desc"),
		@NamedQuery(name = "Breed.findByBreedCode", query = "SELECT b FROM Breed b WHERE 1=1 and b.breedCode = :breedCode ORDER BY b.id desc"),
		@NamedQuery(name = "Breed.count", query = "SELECT count(b.id) FROM Breed b WHERE 1=1") })
public class Breed implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "BREED_CODE")
	private String breedCode;

	@Column(name = "BREED_NAME")
	private String breedName;

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

	public String getPathHidden() {
		return pathHidden;
	}

	public void setPathHidden(String pathHidden) {
		this.pathHidden = pathHidden;
	}

}
