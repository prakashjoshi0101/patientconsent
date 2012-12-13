package com.me.src.globaladmin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.me.src.pojo.Hospital;
import com.me.src.pojo.HospitalEnterprise;

@Controller
@RequestMapping("/create-hospital.htm")
public class CreateHospitalController {
	private static final Logger logger = LoggerFactory.getLogger(CreateHospitalController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model) {
		HospitalEnterprise hospitalEnterprise = new HospitalEnterprise();
		model.addAttribute("hospitalEnterprise", hospitalEnterprise);
		return "global-admin/create-hospital";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("hospitalEnterprise") HospitalEnterprise hospitalEnterprise, BindingResult result, SessionStatus status) {		
	
		return "global-admin/success";
	}
	
}
