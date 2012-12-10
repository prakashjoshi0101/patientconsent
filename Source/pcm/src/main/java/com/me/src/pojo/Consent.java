package com.me.src.pojo;

import java.util.Date;

public class Consent {
	private Hospital hospital;
	private Patient patient; // consent for patient
	private Date date;
	private String consentType;
	private String recordType;
	private boolean status; // true=active, false=disabled
	private UserAccount consentCreatedBy; // can be user can patient or proxy (doctor)
	
	public Hospital getHospital() {
		return hospital;
	}
	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getConsentType() {
		return consentType;
	}
	public void setConsentType(String consentType) {
		this.consentType = consentType;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public UserAccount getConsentCreatedBy() {
		return consentCreatedBy;
	}
	public void setConsentCreatedBy(UserAccount consentCreatedBy) {
		this.consentCreatedBy = consentCreatedBy;
	}
	
	
}
