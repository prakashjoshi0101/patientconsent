package com.me.src;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.me.src.dao.ConsentDao;
import com.me.src.dao.ConsentRequestDao;
import com.me.src.dao.DoctorDao;
import com.me.src.dao.HospitalDao;
import com.me.src.dao.MedicalRecordDao;
import com.me.src.dao.PatientDao;
import com.me.src.dao.PersonDao;
import com.me.src.dao.UserAccountDao;
import com.me.src.pojo.Person;
import com.me.src.pojo.Role;
import com.me.src.pojo.UserAccount;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
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
	
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model) {
		UserAccount ua = new UserAccount();
		model.addAttribute("userAccount", ua);
		return "login";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	//nihar addded model in function parameters
	
	//public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("userAccount") UserAccount userAccount, BindingResult result, SessionStatus status,Model model, HttpSession session){
		logger.info("Login controller");
		
		// Initial Configuration
		//initGlobalAdmin();
				
		logger.info("Username & Password: " + userAccount.getUsername() + " - " + userAccount.getPassword());		
		
		//nihar changes
		
		//model.addAttribute("patientlist",patientDao.findAll());
		/*model.addAttribute("patientlist",patientDao.listPatient(1));*/
		
		//nihar changes
		
		UserAccount ua = userAccountDao.getUserAccount(userAccount.getUsername().toLowerCase(), userAccount.getPassword());		
		if(ua != null) {
			session.setAttribute("userAccount", ua);
			if(ua.getRole().equals(Role.GlobalAdmin.toString())) {
				return "global-admin/home";
			}
			else if(ua.getRole().equals(Role.Admin.toString())) {
				return "admin/home";
			}
			else if(ua.getRole().equals(Role.Doctor.toString())) {
				return "doctor/home";
			}
			else if(ua.getRole().equals(Role.Patient.toString())) {
				return "patient/home";
			}
		}
		
		return "error";
	}	
	
	
	public void initGlobalAdmin() {
		
		Person person = new Person();
		person.setFirstName("Thomas");
		person.setLastName("Hardy");
		person.setGender(true);
		person.setPhone("857-245-1872");
		person.setSsn("781-27-2837");
		person.setEmailId("global@cms.org");
		person.setAddress("500 Bolyston St, Boston");
		person.setDob(new Date(1980, 11, 10));		
				
		personDao.saveOrUpdate(person);
		
		UserAccount ua = new UserAccount();
		ua.setUsername("admin");
		ua.setPassword("admin");
		ua.setRole(Role.GlobalAdmin.toString());
		ua.setPerson(person);
				
		userAccountDao.saveOrUpdate(ua);
	}
}
