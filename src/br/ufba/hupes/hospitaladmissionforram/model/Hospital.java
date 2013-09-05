package br.ufba.hupes.hospitaladmissionforram.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@DatabaseTable(tableName = "hospital")
public class Hospital {

    @DatabaseField(id = true)
    private UUID id = UUID.randomUUID();

    @DatabaseField
	private String name;

    @DatabaseField
	private String acronym;

    @DatabaseField
	private String county;

    @ForeignCollectionField
    private Collection<Research> researches;

	public Hospital() {
	}
	
	public Hospital(String name, String acronym, String county) {
		super();
		this.name = name;
		this.acronym = acronym;
		this.county = county;
	}

    public Hospital(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public Number getQuantity() {
		return 0;
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
