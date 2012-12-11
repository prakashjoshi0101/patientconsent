package com.me.src;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.src.dao.PersonDao;
import com.me.src.pojo.Person;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	PersonDao personDao;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	//public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("Login controller");
		String username = (String)request.getParameter("username");
		String password = (String)request.getParameter("password");
		
		model.addAttribute("username", username);
		model.addAttribute("password", password);
		
		Person person = new Person();
		person.setFirstName("Prakash");
		person.setLastName("Joshi");
		person.setAddress("194 Hillside");
		person.setDob(new Date());
		person.setEmailId("prakash@gmail.com");
		person.setGender(true);
		person.setPhone("617-314-1582");
		person.setSsn("123-45-678");
		
		personDao.saveOrUpdate(person);
		
		return "main";
	}
	
	@RequestMapping(value = "/home.htm", method = RequestMethod.GET)
	public String home() {
		return "login";
	}
}
