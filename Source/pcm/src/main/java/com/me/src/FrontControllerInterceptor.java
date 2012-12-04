package com.me.src;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.me.src.session.SessionToken;

public class FrontControllerInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory
			.getLogger(FrontControllerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		logger.info("preHandle()");
		Business business = Business.getInstance();
		Cookie[] cookies = request.getCookies();
		String stepId = "";
		String username = "";
		if (cookies != null) {			
			for (Cookie c : cookies) {
				if (c.getName().equals("stepId")) {
					stepId = c.getValue();
				}
				if (c.getName().equals("username")) {
					username = c.getValue();
				}
			}
			if (!username.isEmpty()) {
				SessionToken token = business.getSessionManagementServer()
						.getSessionToken(username);
				if(token != null) {
				
				if(stepId == null || stepId.isEmpty() || token.getSteptId() != Long.parseLong(stepId)) {
					logger.info("invalid step id *******");
				}
				}
			}
			
		}
		 //business.getSessionManagementServer().validateSession(request);
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		logger.info("postHandle()");
		Business business = Business.getInstance();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			String username = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("username")) {
					username = c.getValue();
				}
			}
			if (!username.isEmpty()) {
				SessionToken token = business.getSessionManagementServer()
						.getSessionToken(username);
				if (token != null) {
					token.setRecentActivityTimeNow();
					response.addCookie(new Cookie("stepId", Long.toString(token
							.getNextStepId())));
				}
			}
		}
		super.postHandle(request, response, handler, modelAndView);
	}
}
