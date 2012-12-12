package com.me.src.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Person extends MappedModel {
	
	private String firstName;
	private String lastName;
	private boolean gender;
	private Date dob = new Date();
	private String address;
	private String phone;
	private String ssn;
	private String emailId;	
	
	@OneToOne
	private Hospital hospital;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Hospital getHospital() {
		return hospital;
	}
	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}	
	
}
