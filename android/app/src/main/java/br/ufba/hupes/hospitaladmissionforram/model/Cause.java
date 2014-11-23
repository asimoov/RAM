package br.ufba.hupes.hospitaladmissionforram.model;

import java.io.Serializable;

/**
 * Created by denis on 03/09/13.
 */

public class Cause implements Serializable {
	private static final long serialVersionUID = 1L;

	private String disease;

    private Cid cid;

    public Cause() {
    }
    
    public Cause(String disease, Cid cid) {
    	this.setDisease(disease);
    	this.cid = cid;
    }

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}
}
