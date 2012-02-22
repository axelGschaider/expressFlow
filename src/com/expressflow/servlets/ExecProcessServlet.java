package com.expressflow.servlets;

/*
 * Copyright (c) 2011 Martin Vasko, Ph.D.
 * 
 * licensing@expressflow.com http://expressflow.com/license
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.Enumeration;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class ExecProcessServlet extends HttpServlet {
	private static final long serialVersionUID = -8640966752142329271L;
	private static final Logger log = Logger.getLogger(ExecProcessServlet.class
			.getName());

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (user != null) {
			performRequest(request, response);
			try {
				response.getOutputStream().println(new JSONObject().put("result", "success").toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(System.err);
			}
		} else {
			response.sendRedirect(response.encodeRedirectURL(userService
					.createLoginURL(request.getRequestURI())));
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		performRequest(request, response);
		try {
			response.getOutputStream().println(new JSONObject().put("result", "success").toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(System.err);
		}
	}

	private void performRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html");

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (user != null) {
			StringBuilder sb = new StringBuilder(request.getRequestURI());
			String processID = sb.substring(6, sb.length());
			if (processID != null) {
					try {
						// TaskQueues do not support UserService functions...
						// Add the process to the flow-processing queue
						TaskOptions taskOptions = TaskOptions.Builder.withUrl("/_ah/queue/flowprocessor");
						
						Enumeration parameterNames = request.getParameterNames();
						while(parameterNames.hasMoreElements()){
							String parameterName = (String)parameterNames.nextElement();
							String[] paramValues = request.getParameterValues(parameterName);
						      if (paramValues.length == 1) {
						    	  taskOptions.param(parameterName, paramValues[0]);
						      }
						      else{
						    		  System.err.print(parameterName + " has more values?!");
						      }
						}
						
//						Map<String, String[]> parameterMap = request.getParameterMap();
//						for (Map.Entry<String, String[]> parameter : parameterMap.entrySet()) {
//							for(String value : parameter.getValue()){
//								taskOptions.param(parameter.getKey(), value);
//							}
//						}
						
						Queue queue = QueueFactory.getQueue("flowprocessor");
				        queue.add(taskOptions.param("key", processID));
				
					} catch (Exception e) {
						log.info(e.getMessage());
					} 
			}
		} else {
			response.sendRedirect(response.encodeRedirectURL(userService
					.createLoginURL(request.getRequestURI())));
		}
	}
}
