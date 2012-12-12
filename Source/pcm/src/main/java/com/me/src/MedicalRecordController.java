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

import com.me.src.pojo.Person;

public class MedicalRecordController {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
	@RequestMapping(value = "/addperson.htm", method = RequestMethod.POST)
	public String ManagePerson(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("Persom controller");
		
		Person p = new Person();
		DateFormat formatter ; 
	    Date db ; 
	    formatter = new SimpleDateFormat("dd-MM-yy");
	    try {
			db = (Date)formatter.parse(request.getParameter("dob"));
			p.setDob(db);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		p.setFirstName( (String)request.getParameter("firstname"));
		p.setLastName( (String)request.getParameter("lastname"));
		
		p.setGender( Boolean.parseBoolean(request.getParameter("gender")));
		p.setAddress( (String)request.getParameter("address"));
		p.setRole( (String)request.getParameter("role"));
		p.setEmailId( (String)request.getParameter("email"));
		p.setPhone( (String)request.getParameter("phone"));
				
		return "admin";

}
