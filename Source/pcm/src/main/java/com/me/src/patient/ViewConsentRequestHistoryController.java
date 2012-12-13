package com.me.src.patient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.src.dao.ConsentRequestDao;

@Controller
@RequestMapping("/view-consent-history.htm")
public class ViewConsentRequestHistoryController {
	private static final Logger logger = LoggerFactory.getLogger(ViewConsentRequestHistoryController.class);
		
	@Autowired
	ConsentRequestDao consentRequestDao;
	
//	@RequestMapping(method = RequestMethod.GET)
//	public String initForm(ModelMap model) {
//		Consent consent = new Consent();
//		model.addAttribute("consent", consent);
//		return "patient/create-consent";
//	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewHistory() {
	
		logger.info("View Consent History: " + consentRequestDao.findAll().size());
		return "patient/request-history";
	}
	
}
