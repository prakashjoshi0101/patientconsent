package com.me.src.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.me.src.pojo.command.CreatePatient;

@Controller
@RequestMapping("/create-patient.htm")
public class CreatePatientController {
	private static final Logger logger = LoggerFactory.getLogger(CreatePatientController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model) {
		CreatePatient createPatient = new CreatePatient();
		model.addAttribute("createPatient", createPatient);
		return "admin/create-patient";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("createPatient") CreatePatient createPatient, BindingResult result, SessionStatus status) {		
			
		logger.info("Patient Name: " +createPatient.getPatient().getPerson().getFirstName());
		return "admin/home";
	}
	
}
