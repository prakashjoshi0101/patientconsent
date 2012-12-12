package com.me.src;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.src.pojo.MedicalRecord;
import com.me.src.pojo.Person;

public class MedicalRecordController {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
	@RequestMapping(value = "/addmedicalrecord.htm", method = RequestMethod.POST)
	public String ManagePerson(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("MedicalRecordController controller");
		
		MedicalRecord mr = new MedicalRecord();
		mr.setPatient(patient);
	    
		p.setFirstName( (String)request.getParameter("firstname"));
		p.setLastName( (String)request.getParameter("lastname"));
		
		p.setGender( Boolean.parseBoolean(request.getParameter("gender")));
		p.setAddress( (String)request.getParameter("address"));
		p.setEmailId( (String)request.getParameter("email"));
		p.setPhone( (String)request.getParameter("phone"));
				
		return "admin";

}
