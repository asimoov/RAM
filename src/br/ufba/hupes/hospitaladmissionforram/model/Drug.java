package br.ufba.hupes.hospitaladmissionforram.model;

/**
 * Created by denis on 03/09/13.
 */
public class Drug {
    private String name;
    private String atc;

    public Drug() {
    }

    public Drug(String name, String atc) {
        this.name = name;
        this.atc = atc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAtc() {
        return atc;
    }

    public void setAtc(String atc) {
        this.atc = atc;
    }
}
