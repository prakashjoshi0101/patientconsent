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

import com.me.src.pojo.command.CreateDoctor;

@Controller
@RequestMapping("/create-doctor.htm")
public class CreateDoctorController {
	private static final Logger logger = LoggerFactory.getLogger(CreateDoctorController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model) {
		CreateDoctor createDoctor = new CreateDoctor();
		model.addAttribute("createDoctor", createDoctor);
		return "admin/create-doctor";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("createDoctor") CreateDoctor createDoctor, BindingResult result, SessionStatus status) {		
	
		logger.info("Doctor Name" +createDoctor.getDoctor().getPerson().getFirstName());
		return "admin/home";
	}
	
}
