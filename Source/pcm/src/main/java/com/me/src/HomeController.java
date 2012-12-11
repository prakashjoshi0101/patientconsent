package com.me.src;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.src.dao.ConsentDao;
import com.me.src.dao.ConsentRequestDao;
import com.me.src.dao.DoctorDao;
import com.me.src.dao.HospitalDao;
import com.me.src.dao.MedicalRecordDao;
import com.me.src.dao.PatientDao;
import com.me.src.dao.PersonDao;
import com.me.src.dao.UserAccountDao;
import com.me.src.pojo.Consent;
import com.me.src.pojo.ConsentRequest;
import com.me.src.pojo.Doctor;
import com.me.src.pojo.Hospital;
import com.me.src.pojo.MedicalRecord;
import com.me.src.pojo.Patient;
import com.me.src.pojo.Person;
import com.me.src.pojo.UserAccount;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	PersonDao personDao;
	@Autowired
	DoctorDao doctorDao;
	@Autowired
	ConsentDao consentDao;
	@Autowired
	ConsentRequestDao consentRequestDao;
	@Autowired
	HospitalDao hospitalDao;
	@Autowired
	MedicalRecordDao medicalRecordDao;
	@Autowired
	PatientDao patientDao;
	@Autowired
	UserAccountDao userAccountDao;
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	//public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("Login controller");
		String username = (String)request.getParameter("username");
		String password = (String)request.getParameter("password");
		
		model.addAttribute("username", username);
		model.addAttribute("password", password);
		
		Person person = new Person();
		person.setFirstName("Prakash");
		person.setLastName("Joshi");
		person.setAddress("194 Hillside");
		person.setDob(new Date());
		person.setEmailId("prakash@gmail.com");
		person.setGender(true);
		person.setPhone("617-314-1582");
		person.setSsn("123-45-678");
		
		personDao.saveOrUpdate(person);
		
		doctorDao.saveOrUpdate(new Doctor());
		consentDao.saveOrUpdate(new Consent());
		consentRequestDao.saveOrUpdate(new ConsentRequest());
		hospitalDao.saveOrUpdate(new Hospital());
		medicalRecordDao.saveOrUpdate(new MedicalRecord());
		patientDao.saveOrUpdate(new Patient());
		userAccountDao.saveOrUpdate(new UserAccount());
		
		return "main";
	}
	
	@RequestMapping(value = "/home.htm", method = RequestMethod.GET)
	public String home() {
		return "login";
	}
}
