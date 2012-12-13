package com.me.src.doctor;

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
@RequestMapping("/create-consent.htm")
public class DoctorCreateConsentController {
	private static final Logger logger = LoggerFactory.getLogger(DoctorCreateConsentController.class);
		
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model) {
		Consent consent = new Consent();
		model.addAttribute("consent", consent);
		return "doctor/create-consent";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("consent") Consent consent, BindingResult result, SessionStatus status) {		
	
		logger.info("Consent: " + consent.getConsentType());
		return "doctor/home";
	}
	
}
