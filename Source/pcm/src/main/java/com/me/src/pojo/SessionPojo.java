package com.me.src.pojo;

import java.util.Date;

import javax.crypto.SealedObject;

public class SessionPojo {

	private byte[] sessionId;
	private byte[] stepId;
	private Date sessionCreationDate;
	private SealedObject data;
	
	
}
