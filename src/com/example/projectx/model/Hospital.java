package com.example.projectx.model;

public class Hospital {
	private String name;
	private String acronym;
	private String county;

	public Hospital() {
	}
	
	public Hospital(String name, String acronym, String county) {
		super();
		this.name = name;
		this.acronym = acronym;
		this.county = county;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public Number getQuantity() {
		return 0;
	}
}
