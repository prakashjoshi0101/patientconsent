package com.me.src;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class AdminController {
	
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	//public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
	@RequestMapping(value = "/CreatePerson.htm", method = RequestMethod.POST)
	public String ManagePerson(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("Manage controller");
		String username = (String)request.getParameter("username");
		String password = (String)request.getParameter("password");
		
		model.addAttribute("username", username);
		model.addAttribute("password", password);
				
		return "createperson";
	}
	
	@RequestMapping(value = "/CreateUserAcc.htm", method = RequestMethod.POST)
	public String home() {
		return "createuseraccount";
	}

}
