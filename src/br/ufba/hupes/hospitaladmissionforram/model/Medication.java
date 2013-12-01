package br.ufba.hupes.hospitaladmissionforram.model;

import java.io.Serializable;
import java.util.Date;

public class Medication implements Serializable {

	private static final long serialVersionUID = 1L;

	String name;
	String way;
	String dose;
	String indication;
	Date start;
	Date end;

	public Medication(String medication, String way, String dose,
			String indication, Date initialDate, Date finalDate) {
		this.name = medication;
		this.way = way;
		this.dose = dose;
		this.indication = indication;
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

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

}
