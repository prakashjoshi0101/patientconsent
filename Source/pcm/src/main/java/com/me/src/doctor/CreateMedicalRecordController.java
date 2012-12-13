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

import com.me.src.pojo.MedicalRecord;

@Controller
@RequestMapping("/create-medical-record.htm")
public class CreateMedicalRecordController {
	private static final Logger logger = LoggerFactory.getLogger(CreateMedicalRecordController.class);
		
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model) {
		MedicalRecord record = new MedicalRecord();
		model.addAttribute("record", record);
		return "doctor/create-medical-record";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("record") MedicalRecord record, BindingResult result, SessionStatus status) {		
	
		logger.info("Medical Record: " + record.getDescription());
		return "doctor/home";
	}
	
}
