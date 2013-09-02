package br.ufba.hupes.hospitaladmissionforram.model;

public class Research {
	private String handbook;
    private String name;
	private String cns;

    public Research() {
    }

    public Research(String handbook, String name, String cns) {
        this.handbook = handbook;
        this.name = name;
        this.cns = cns;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHandbook() {
		return handbook;
	}

	public void setHandbook(String handbook) {
		this.handbook = handbook;
	}

	public String getCns() {
		return cns;
	}

	public void setCns(String cns) {
		this.cns = cns;
	}
}
