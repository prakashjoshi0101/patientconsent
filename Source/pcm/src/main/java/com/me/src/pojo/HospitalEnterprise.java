package com.me.src.pojo;

public class HospitalEnterprise {

	private Hospital hospital;
	private UserAccount userAccount;
	
	
	public HospitalEnterprise() {
		hospital = new Hospital();
		userAccount = new UserAccount();
		userAccount.setPerson(new Person());
	}
	
	public Hospital getHospital() {
		return hospital;
	}
	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
	
}
