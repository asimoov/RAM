package br.ufba.hupes.hospitaladmissionforram.model;

import java.io.Serializable;

public class Medication implements Serializable {

	private static final long serialVersionUID = 1L;

	String medication;
	String way;
	String dose;
	String indication;
	String initialDate;
	String finalDate;

	public Medication(String medication, String way, String dose,
			String indication, String initialDate, String finalDate) {
		this.medication = medication;
		this.way = way;
		this.dose = dose;
		this.indication = indication;
		this.initialDate = initialDate;
		this.finalDate = finalDate;
	}

	public String getMedication() {
		return medication;
	}

	public void setMedication(String medication) {
		this.medication = medication;
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

	public String getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(String initialDate) {
		this.initialDate = initialDate;
	}

	public String getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(String finalDate) {
		this.finalDate = finalDate;
	}

}
