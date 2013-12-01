package br.ufba.hupes.hospitaladmissionforram.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

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
    private String sex;

    @DatabaseField
    private Long weight;

    @DatabaseField
    private Long height;

    @DatabaseField
    private String color;

    @DatabaseField
    private String unit;

    @DatabaseField
    private String bed;

    @DatabaseField
    private Date admission;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Hospital hospital;

    private List<Medication> suspects;

    private Cause cause;

    private Cause comorbidity;

    private List<Medication> medications;

    private Integer status;

    @DatabaseField
    private Date updateAt;

    @DatabaseField
    private Date syncedAt;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Date getSyncedAt() {
        return syncedAt;
    }

    public void setSyncedAt(Date syncedAt) {
        this.syncedAt = syncedAt;
    }

    public boolean isOpen() {
        return true;
    }
}
