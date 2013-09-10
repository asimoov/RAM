package br.ufba.hupes.hospitaladmissionforram.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@DatabaseTable(tableName = "research")
public class Research {

    @DatabaseField(id = true)
    private UUID id = UUID.randomUUID();

    @DatabaseField
	private String handbook;

    @DatabaseField
    private String name;

    @DatabaseField
	private String cns;

    @DatabaseField
    private Date birthday;

    @DatabaseField
    private Sex sex;

    @DatabaseField
    private Double weight;

    @DatabaseField
    private Double height;

    @DatabaseField
    private Color color;

    @DatabaseField
    private String unit;

    @DatabaseField
    private String bed;

    @DatabaseField
    private Date admission;

    @DatabaseField(foreign = true)
    private Hospital hospital;

    private List<Suspect> suspects;
    private List<Cause> causes;
    private List<Comorbidity> comorbidities;

    public Research() {
    }

    public Research(String handbook, String name, String bed, String cns, Hospital hospital) {
        this.setHandbook(handbook);
        this.setName(name);
        this.setCns(cns);
        this.setBed(bed);
        this.setHospital(hospital);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
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

    public Date getAdmission() {
        return admission;
    }

    public void setAdmission(Date admission) {
        this.admission = admission;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
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
