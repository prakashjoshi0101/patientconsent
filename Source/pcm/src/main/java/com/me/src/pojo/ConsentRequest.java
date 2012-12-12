package com.me.src.pojo;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class ConsentRequest extends MappedModel {
	@OneToOne
	private Patient patient = new Patient();
	private String consentType;
	private String recordType;
	
	@OneToOne
	private Hospital recordRequester = new Hospital(); // entity trying to request medical records
	
	@OneToOne
	private Hospital recordProvider = new Hospital();  // entity who has medical records
	
	@OneToOne
	private UserAccount requestByUser = new UserAccount();
	
	@OneToOne
	private UserAccount respondByUser = new UserAccount();
	
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
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
	public Hospital getRecordRequester() {
		return recordRequester;
	}
	public void setRecordRequester(Hospital recordRequester) {
		this.recordRequester = recordRequester;
	}
	public Hospital getRecordProvider() {
		return recordProvider;
	}
	public void setRecordProvider(Hospital recordProvider) {
		this.recordProvider = recordProvider;
	}
	public UserAccount getRequestByUser() {
		return requestByUser;
	}
	public void setRequestByUser(UserAccount requestByUser) {
		this.requestByUser = requestByUser;
	}
	public UserAccount getRespondByUser() {
		return respondByUser;
	}
	public void setRespondByUser(UserAccount respondByUser) {
		this.respondByUser = respondByUser;
	}	
	
	
}
