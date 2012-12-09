package com.me.src.pojo;

import java.util.Date;

public class Consent {
	private Hospital hospital;
	private Patient patient;
	private Date date;
	private String consentType;
	private String recordType;
	private boolean status; // true=active, false=disabled
}
