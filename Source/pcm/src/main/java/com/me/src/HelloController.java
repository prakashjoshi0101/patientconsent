package com.me.src;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class HelloController {
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@RequestMapping(value = "/hello.htm", method = RequestMethod.GET)
	public String hello(@RequestParam("name") String name, Model model) {
		logger.info("hello service called for: " + name);
		
		// call service appropriately from FrontController
		model.addAttribute("hello", "Hello " + name);
		return "main";
	}
}
