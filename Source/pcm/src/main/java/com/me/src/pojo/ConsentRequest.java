package com.me.src.pojo;

public class ConsentRequest {
	private Patient patient;
	private String consentType;
	private String recordType;
	private Hospital recordRequester; // entity trying to request medical records
	private Hospital recordProvider;  // entity who has medical records
	private UserAccount requestByUser;
	private UserAccount respondByUser;
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
