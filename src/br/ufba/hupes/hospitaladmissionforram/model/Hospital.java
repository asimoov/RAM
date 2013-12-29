package br.ufba.hupes.hospitaladmissionforram.model;

import java.util.Collection;
import java.util.UUID;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "hospital")
public class Hospital {

	@Expose
	@DatabaseField(id = true)
    private long id;

	@Expose
    @DatabaseField
	private String name;

	@Expose
    @DatabaseField
	private String acronym;

	@Expose
    @DatabaseField
	private String country;

	@Expose
    @ForeignCollectionField(eager=true)
    private Collection<Research> researches;

	public Hospital() {
	}
	
	public Hospital(String name, String acronym, String county) {
		super();
		this.name = name;
		this.acronym = acronym;
		this.country = county;
	}

    public Hospital(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
		return country;
	}

	public void setCounty(String county) {
		this.country = county;
	}

	public Number getQuantity() {
		return getResearches().size();
	}

    public Number getQuantityOpen() {
        return 0;
    }

    public Collection<Research> getResearches() {
        return researches;
    }

    public void setResearches(Collection<Research> researches) {
        this.researches = researches;
    }

    public String getFullName(){
        return getAcronym() + " - " + getName();
    }
}
