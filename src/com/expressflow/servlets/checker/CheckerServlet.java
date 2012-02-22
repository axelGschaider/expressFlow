package com.expressflow.servlets.checker;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckerServlet extends HttpServlet {

	private static final long serialVersionUID = -8208319006070687213L;

	private static final Logger log = Logger.getLogger(CheckerServlet.class
			.getName()); 
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		Date now = new Date();
		log.info("Heartbeat alive at " + now.getTime() +  " millis.");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		Date now = new Date();
		log.info("Heartbeat alive at " + now.getTime() +  " millis.");
	}
}
