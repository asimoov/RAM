package br.ufba.hupes.hospitaladmissionforram.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class Medication implements Serializable {

	private static final long serialVersionUID = 1L;

	@Expose
	String name;
	
	@Expose
	String way;
	
	@Expose
	String dose;
	
	@Expose
	String indication;
	
	@Expose
	String start;
	
	@Expose
	String end;
	
	@Expose
	boolean plant;

	@Expose
	String plant_use;

	@Expose
	String plant_part_used;

	@Expose
	String plant_prepare_mode;

	@Expose
	String plant_dose;

	@Expose
	String plant_obtainment;
	
	public Medication() {
	}
	
	public Medication(String medication, String way, String dose,
			String indication, String initialDate, String finalDate) {
		this.name = medication;
		this.way = way;
		this.dose = dose;
		this.indication = indication;
		this.start = initialDate;
		this.end = finalDate;
	}

	public Medication(String name, String plant_use, String plant_part_used,
			String plant_prepare_mode, String plant_dose, String plant_obtainment, String initialDate, String finalDate) {
		this.plant = true;
		this.name = name;
		this.plant_dose = plant_dose;
		this.plant_part_used = plant_part_used;
		this.plant_prepare_mode = plant_prepare_mode;
		this.plant_use = plant_use;
		this.plant_obtainment = plant_obtainment;
		this.start = initialDate;
		this.end = finalDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public String getIndication() {
		return indication;
	}

	public void setIndication(String indication) {
		this.indication = indication;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

}