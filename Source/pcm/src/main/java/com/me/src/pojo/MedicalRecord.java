package com.me.src.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class MedicalRecord extends MappedModel {
	@OneToOne
	private Patient patient = new Patient();
	@OneToOne
	private Doctor doctor = new Doctor();
	private Date date = new Date();
	private String description;
	private String prescription;
	private String recordType; // brain, accident, mental
		
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrescription() {
		return prescription;
	}
	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	
	
}
