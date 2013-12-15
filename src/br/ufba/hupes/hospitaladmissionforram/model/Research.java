package br.ufba.hupes.hospitaladmissionforram.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

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

	@DatabaseField(foreign=true, foreignAutoRefresh = true)
	private RAM ram;

	private ArrayList<Medication> listMedications;
	
	@ForeignCollectionField(eager=true)
	private ForeignCollection<Medication> medications;

	@DatabaseField
	private Integer status;

	@DatabaseField
	private boolean anotherLocation;

	@DatabaseField
	private boolean exposicaoPrevia;

	@DatabaseField
	private boolean desenvolveuReacao;

	@DatabaseField
	private String reacoesAdversas;

	@DatabaseField
	private String plantasMedicinais;

	@DatabaseField
	private String freqPlantasMedicinais;

	@DatabaseField
	private String tabagismo;

	@DatabaseField
	private String tempoTabagismo;

	@DatabaseField
	private String etilismo;

	@DatabaseField
	private String tempoEtilismo;

	@DatabaseField
	private boolean usaCrack;
	
	@DatabaseField
	private boolean usaCocaina;
	
	@DatabaseField
	private boolean usaMaconha;
	
	@DatabaseField
	private boolean usaLSD;
	
	@DatabaseField
	private String treatment;
	
	@DatabaseField
	private String result;
	
	@DatabaseField
	private String sequels;

	@DatabaseField
	private String dischargeDate;
	
	@DatabaseField
	private String gravity;

	@DatabaseField
	private String algNaranjo;
	@DatabaseField
	private String algOMS;
	@DatabaseField
	private String algUE;
	@DatabaseField
	private String algRUCAM;
	
	@DatabaseField
	private Date updateAt;

	@DatabaseField
	private Date syncedAt;

	public Research() {
	}

	public Research(String handbook, String name, String bed, String cns,
			Hospital hospital) {
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

	public ArrayList<Medication> getMedications() {
		return listMedications;
	}

	public void setMedications(ArrayList<Medication> medications) {
		this.listMedications = medications;
	}

	public RAM getRam() {
		return ram;
	}

	public void setRam(RAM ram) {
		this.ram = ram;
	}

	public boolean isAnotherLocation() {
		return anotherLocation;
	}

	public void setAnotherLocation(boolean anotherLocation) {
		this.anotherLocation = anotherLocation;
	}

	public boolean isDesenvolveuReacao() {
		return desenvolveuReacao;
	}

	public void setDesenvolveuReacao(boolean desenvolveuReacao) {
		this.desenvolveuReacao = desenvolveuReacao;
	}

	public boolean isExposicaoPrevia() {
		return exposicaoPrevia;
	}

	public void setExposicaoPrevia(boolean exposicaoPrevia) {
		this.exposicaoPrevia = exposicaoPrevia;
	}

	public String getReacoesAdversas() {
		return reacoesAdversas;
	}

	public void setReacoesAdversas(String reacoesAdversas) {
		this.reacoesAdversas = reacoesAdversas;
	}

	public String getPlantasMedicinais() {
		return plantasMedicinais;
	}

	public void setPlantasMedicinais(String plantasMedicinais) {
		this.plantasMedicinais = plantasMedicinais;
	}

	public String getFreqPlantasMedicinais() {
		return freqPlantasMedicinais;
	}

	public void setFreqPlantasMedicinais(String freqPlantasMedicinais) {
		this.freqPlantasMedicinais = freqPlantasMedicinais;
	}

	public String getTabagismo() {
		return tabagismo;
	}

	public void setTabagismo(String tabagismo) {
		this.tabagismo = tabagismo;
	}

	public String getTempoTabagismo() {
		return tempoTabagismo;
	}

	public void setTempoTabagismo(String tempoTabagismo) {
		this.tempoTabagismo = tempoTabagismo;
	}

	public String getEtilismo() {
		return etilismo;
	}

	public void setEtilismo(String etilismo) {
		this.etilismo = etilismo;
	}

	public String getTempoEtilismo() {
		return tempoEtilismo;
	}

	public void setTempoEtilismo(String tempoEtilismo) {
		this.tempoEtilismo = tempoEtilismo;
	}

	public boolean isUsaCrack() {
		return usaCrack;
	}

	public void setUsaCrack(boolean usaCrack) {
		this.usaCrack = usaCrack;
	}

	public boolean isUsaCocaina() {
		return usaCocaina;
	}

	public void setUsaCocaina(boolean usaCocaina) {
		this.usaCocaina = usaCocaina;
	}

	public boolean isUsaMaconha() {
		return usaMaconha;
	}

	public void setUsaMaconha(boolean usaMaconha) {
		this.usaMaconha = usaMaconha;
	}

	public boolean isUsaLSD() {
		return usaLSD;
	}

	public void setUsaLSD(boolean usaLSD) {
		this.usaLSD = usaLSD;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getSequels() {
		return sequels;
	}

	public void setSequels(String sequels) {
		this.sequels = sequels;
	}

	public String getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public String getGravity() {
		return gravity;
	}

	public void setGravity(String gravity) {
		this.gravity = gravity;
	}

	public String getAlgNaranjo() {
		return algNaranjo;
	}

	public void setAlgNaranjo(String algNaranjo) {
		this.algNaranjo = algNaranjo;
	}

	public String getAlgOMS() {
		return algOMS;
	}

	public void setAlgOMS(String algOMS) {
		this.algOMS = algOMS;
	}

	public String getAlgUE() {
		return algUE;
	}

	public void setAlgUE(String algUE) {
		this.algUE = algUE;
	}

	public String getAlgRUCAM() {
		return algRUCAM;
	}

	public void setAlgRUCAM(String alguRUCAM) {
		this.algRUCAM = alguRUCAM;
	}
}
