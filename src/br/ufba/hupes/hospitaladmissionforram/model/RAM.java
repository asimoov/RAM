package br.ufba.hupes.hospitaladmissionforram.model;

import java.util.List;
import java.util.UUID;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class RAM {

	@DatabaseField(id = true)
	private UUID id = UUID.randomUUID();

	@DatabaseField(foreign=true)
    private Cause cause;
	
	@DatabaseField(foreign=true)
    private Cause comorbidity;
	
	@DatabaseField
    private String otherCauses;
	
	@DatabaseField(foreign=true)
	private Research research;
	
    private List<Medication> suspects;
    
    public List<Medication> getSuspects() {
        return suspects;
    }

    public void setSuspects(List<Medication> suspects) {
        this.suspects = suspects;
    }

    public Cause getCause() {
        return cause;
    }

    public void setCause(Cause cause) {
        this.cause = cause;
    }

    public Cause getComorbidity() {
        return comorbidity;
    }

    public void setComorbidity(Cause comorbidity) {
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
}