package br.ufba.hupes.hospitaladmissionforram.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Expose
	String id;

	@Expose
	boolean admin;
	
	@Expose
	Hospital[] hospitals;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Hospital[] getHospitals() {
		return hospitals;
	}

	public void setHospitals(Hospital[] hospitals) {
		this.hospitals = hospitals;
	}
}