package br.ufba.hupes.hospitaladmissionforram.model;

import java.util.UUID;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by denis on 03/09/13.
 */

@DatabaseTable
public class Cause {

	@DatabaseField(id = true)
	private UUID id = UUID.randomUUID();

    @DatabaseField
	private String disease;

    private Cid cid;

    @DatabaseField(foreign = true)
    private RAM ram;

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
