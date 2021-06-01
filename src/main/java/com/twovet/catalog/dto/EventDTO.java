package com.twovet.catalog.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class EventDTO {

	private Long id;
	private String title;
	private String branchName;
	@JsonSerialize
	private Date start;
	private Date end;
	private boolean allDay;
	private String backgroundColor;
	private String borderColor;
	private String textColor;
	private List<BranchDTO> branchs;
	private List<CustomerDTO> customers;
	private List<PetDTO> pets;
	private List<DoctorDTO> doctors;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public List<BranchDTO> getBranchs() {
		return branchs;
	}

	public void setBranchs(List<BranchDTO> branchs) {
		this.branchs = branchs;
	}

	public List<CustomerDTO> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerDTO> customers) {
		this.customers = customers;
	}

	public List<PetDTO> getPets() {
		return pets;
	}

	public void setPets(List<PetDTO> pets) {
		this.pets = pets;
	}

	public List<DoctorDTO> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<DoctorDTO> doctors) {
		this.doctors = doctors;
	}

	public boolean isAllDay() {
		return allDay;
	}

	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

}
