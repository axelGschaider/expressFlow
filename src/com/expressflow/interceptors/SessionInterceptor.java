package com.expressflow.interceptors;

/*
Copyright (c) 2011 Martin Vasko, Ph.D.

licensing@expressflow.com
http://expressflow.com/license
*/

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.expressflow.Constants;
import com.expressflow.datastore.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class SessionInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = Logger.getLogger(SessionInterceptor.class.getSimpleName());
	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		// Checking expressBPEL Session
		logger.info("Checking User Session");
		User user = (User) request.getSession().getAttribute(
				Constants.SESSION_USER);
		String uri = request.getRequestURI();
		String base = request.getContextPath();
		uri = uri.substring(base.length());
		if (user == null && !uri.startsWith("/api/login")) {
			
			// Check Google App Engine User Service
			logger.info("Checking Google App Engine User Service");
			UserService userService = UserServiceFactory.getUserService();
			com.google.appengine.api.users.User googleUser = userService.getCurrentUser();
	
			if(googleUser != null){
				logger.info("Email: " + googleUser.getEmail() + "\t Nickname: " + googleUser.getNickname());
			}
			
			if(googleUser == null){
				logger.info("All checks failed. Invalidating session.");
				response.sendRedirect(userService.createLoginURL(request.getRequestURI() ));
				return false;
			}
		}
		return true;
	}
}
