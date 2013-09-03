package br.ufba.hupes.hospitaladmissionforram.model;

import java.util.Calendar;
import java.util.List;

public class Research {

	private String handbook;
    private String name;
	private String cns;

    private Calendar birthday;
    private Sex sex;

    private Double weight;
    private Double height;

    private Color color;
    private String unit;
    private String bed;

    private Calendar admission;

    private List<Suspect> suspects;
    private List<Cause> causes;
    private List<Comorbidity> comorbidities;

    public Research() {
    }

    public Research(String handbook, String name, String cns) {
        this.setHandbook(handbook);
        this.setName(name);
        this.setCns(cns);
    }

    public String getHandbook() {
        return handbook;
    }

    public void setHandbook(String handbook) {
        this.handbook = handbook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCns() {
        return cns;
    }

    public void setCns(String cns) {
        this.cns = cns;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public Calendar getAdmission() {
        return admission;
    }

    public void setAdmission(Calendar admission) {
        this.admission = admission;
    }

    public List<Suspect> getSuspects() {
        return suspects;
    }

    public void setSuspects(List<Suspect> suspects) {
        this.suspects = suspects;
    }

    public List<Cause> getCauses() {
        return causes;
    }

    public void setCauses(List<Cause> causes) {
        this.causes = causes;
    }

    public List<Comorbidity> getComorbidities() {
        return comorbidities;
    }

    public void setComorbidities(List<Comorbidity> comorbidities) {
        this.comorbidities = comorbidities;
    }
}
