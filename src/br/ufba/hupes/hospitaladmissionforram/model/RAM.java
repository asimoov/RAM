package br.ufba.hupes.hospitaladmissionforram.model;

import java.util.ArrayList;
import java.util.UUID;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class RAM {

	@Expose
	@DatabaseField(id = true)
	private String id = UUID.randomUUID().toString();

	@Expose
	@DatabaseField
    private String cause;
	
	@Expose
	@DatabaseField
    private String comorbidity;

	@Expose
	@DatabaseField
    private String otherCauses;

	@Expose
	@DatabaseField
    private String initialDate;

	@Expose
	@DatabaseField
    private String finalDate;
	
	@DatabaseField(foreign=true)
	private Research research;

	@Expose
	@DatabaseField(dataType=DataType.SERIALIZABLE)
    private ArrayList<Medication> suspects;
    
    public ArrayList<Medication> getSuspects() {
        return suspects;
    }

    public void setSuspects(ArrayList<Medication> suspects) {
        this.suspects = suspects;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getComorbidity() {
        return comorbidity;
    }

    public void setComorbidity(String comorbidity) {
        this.comorbidity = comorbidity;
    }

	public String getOtherCauses() {
		return otherCauses;
	}

	public void setOtherCauses(String anotherCauses) {
		this.otherCauses = anotherCauses;
	}

	public Research getResearch() {
		return research;
	}

	public void setResearch(Research research) {
		this.research = research;
	}

	public String getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(String finalDate) {
		this.finalDate = finalDate;
	}

	public String getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(String initialDate) {
		this.initialDate = initialDate;
	}
}