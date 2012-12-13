package com.me.src.patient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.me.src.pojo.Consent;

@Controller
@RequestMapping("/patient-create-consent.htm")
public class PatientCreateConsentController {
	private static final Logger logger = LoggerFactory.getLogger(PatientCreateConsentController.class);
		
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model) {
		Consent consent = new Consent();
		model.addAttribute("consent", consent);
		return "patient/create-consent";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("consent") Consent consent, BindingResult result, SessionStatus status) {		
	
		logger.info("Consent: " + consent.getConsentType());
		return "patient/home";
	}
	
}
