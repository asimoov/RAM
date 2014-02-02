package br.ufba.hupes.hospitaladmissionforram.model;

import java.util.Collection;

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
	private String city;

	@Expose
    @ForeignCollectionField(eager=true)
    private Collection<Research> researches;

	public Hospital() {
	}
	
	public Hospital(String name, String acronym, String county) {
		super();
		this.name = name;
		this.acronym = acronym;
		this.city = county;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Number getQuantity() {
		return getResearches().size();
	}

    public Number getQuantityClosed() {
    	int x = 0;
    	Collection<Research> collection = getResearches();
    	for (Research research : collection) {
			if (!research.isOpen()) {
				x++;
			}
		}
		return x;
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
    
    @Override
    public boolean equals(Object o) {
    	if (o instanceof Hospital) {
    		return getId() == ((Hospital)o).getId();
		}
    	return super.equals(o);
    }
}
