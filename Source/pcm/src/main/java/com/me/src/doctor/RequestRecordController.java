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

import com.me.src.pojo.command.RecordRequestCommand;

@Controller
@RequestMapping("/request-record.htm")
public class RequestRecordController {
	private static final Logger logger = LoggerFactory.getLogger(RequestRecordController.class);
		
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model) {
		RecordRequestCommand request = new RecordRequestCommand();
		model.addAttribute("request", request);
		return "doctor/record-request";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("request") RecordRequestCommand request, BindingResult result, SessionStatus status) {		
	
		logger.info("Record Request: " + request.getPatientId());
		return "doctor/home";
	}
	
}
