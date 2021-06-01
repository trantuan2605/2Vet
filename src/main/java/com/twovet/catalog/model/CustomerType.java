package com.twovet.catalog.model;

public class CustomerType {
	private int code;
	private String name;
	
	public CustomerType(int code, String name) {
		// TODO Auto-generated constructor stub
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
