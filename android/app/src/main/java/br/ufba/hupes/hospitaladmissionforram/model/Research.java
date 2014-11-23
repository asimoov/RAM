package br.ufba.hupes.hospitaladmissionforram.model;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.UUID;

@DatabaseTable(tableName = "research")
public class Research {

	@Expose
	@DatabaseField(id = true)
	private String id;

	@Expose
	@DatabaseField
	private String handbook;

	@Expose
	@DatabaseField
	private String name;

	@Expose
	@DatabaseField
	private String cns;

	@Expose
	@DatabaseField
	private String birthday;

	@Expose
	@DatabaseField
	private String sex;

	@Expose
	@DatabaseField
	private float weight;

	@Expose
	@DatabaseField
	private float height;

	@Expose
	@DatabaseField
	private String color;

	@Expose
	@DatabaseField
	private String unit;
 
	@Expose
	@DatabaseField
	private String bed;

	@Expose
	@DatabaseField
	private String admission;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Hospital hospital;

	@Expose
	private long hospital_id;

	@Expose
	@DatabaseField(foreign=true, foreignAutoRefresh = true)
	private RAM ram;

	@Expose
	@DatabaseField(dataType=DataType.SERIALIZABLE)
	private ArrayList<Medication> medications;

	@Expose
	@DatabaseField
	private int status;

	@Expose
	@DatabaseField
	private boolean anotherLocation;

	@Expose
	@DatabaseField
    private String anotherLocationDescription;

	@Expose
	@DatabaseField
	private boolean exposicaoPrevia;

	@Expose
	@DatabaseField
	private boolean desenvolveuReacao;

    @Expose
    @DatabaseField
    private String reacoesAdversas;

    @Expose
    @DatabaseField
    private String medsReacoesAdversas;

	@Expose
	@DatabaseField
	private String plantasMedicinais;

	@Expose
	@DatabaseField
	private String freqPlantasMedicinais;

	@Expose
	@DatabaseField
	private String tabagismo;

	@Expose
	@DatabaseField
	private String tempoTabagismo;

	@Expose
	@DatabaseField
	private String etilismo;

	@Expose
	@DatabaseField
	private String tempoEtilismo;

	@Expose
	@DatabaseField
	private boolean usaCrack;
	
	@Expose
	@DatabaseField
	private boolean usaCocaina;
	
	@Expose
	@DatabaseField
	private boolean usaMaconha;
	
	@Expose
	@DatabaseField
	private boolean usaLSD;
	
	@Expose
	@DatabaseField
	private String treatment;

	@Expose
	@DatabaseField
	private String result;

	@Expose
	@DatabaseField
	private String deathDate;
	
	@Expose
	@DatabaseField
	private String sequels;

	@Expose
	@DatabaseField
	private String dischargeDate;
	
	@Expose
	@DatabaseField
	private String gravity;

	@Expose
	@DatabaseField
	private String algNaranjo;
	@Expose
	@DatabaseField
	private String algOMS;
	@Expose
	@DatabaseField
	private String algUE;
	@Expose
	@DatabaseField
	private String algRUCAM;
	
	@Expose
	@DatabaseField
	private String updatedAt; //from server
	
	@Expose
	@DatabaseField
	private String createdAt; //from server

	@Expose
	@DatabaseField
	private boolean sent;
	
	public Research() {
		 id = UUID.randomUUID().toString();
	}

	public Research(String handbook, String name, String bed, String cns,
			Hospital hospital) {
		this.setHandbook(handbook);
		this.setName(name);
		this.setCns(cns);
		this.setBed(bed);
		this.setHospital(hospital);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
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

	public String getAdmission() {
		return admission;
	}

	public void setAdmission(String admission) {
		this.admission = admission;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
		this.hospital_id = hospital.getId();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updateAt) {
		this.updatedAt = updateAt;
	}

	public boolean isOpen() {
		return status != Status.CLOSE.ordinal();
	}

	public ArrayList<Medication> getMedications() {
		return medications;
	}

	public void setMedications(ArrayList<Medication> medications) {
		this.medications = medications;
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

	public String getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate;
	}

	public boolean isSent() {
		return sent;
	}

	public void setSent(boolean sent) {
		this.sent = sent;
	}

	public long getHospitalId() {
		return hospital_id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

    public String getAnotherLocationDescription() {
        return anotherLocationDescription;
    }

    public void setAnotherLocationDescription(String anotherLocationDescription) {
        this.anotherLocationDescription = anotherLocationDescription;
    }

    public String getMedsReacoesAdversas() {
        return medsReacoesAdversas;
    }

    public void setMedsReacoesAdversas(String medsReacoesAdversas) {
        this.medsReacoesAdversas = medsReacoesAdversas;
    }
}
