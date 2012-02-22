package com.expressflow.servlets;

/*
 * Copyright (c) 2011 Martin Vasko, Ph.D.
 * 
 * licensing@expressflow.com http://expressflow.com/license
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class MobileFormManagementServlet extends HttpServlet {
	private static final Logger log = Logger
			.getLogger(MobileFormManagementServlet.class.getName());

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html");
		PrintWriter out = new PrintWriter(response.getWriter());

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (user != null) {
			StringBuilder sb = new StringBuilder(request.getRequestURI());
			String taskName = sb.substring(12, sb.length());

			if (taskName != null) {
				out.println("<html>");
				out.println("<head>");
				out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
				out.println("<title>expressFlow - Mobile Human Task</title>");
				out.println("<link rel=\"stylesheet\" href=\"/css/sencha-touch.css\" type=\"text/css\">");
				out.println("<link rel=\"icon\" type=\"image/png\" href=\"/favicon.png\" />");
				out.println("<script type=\"text/javascript\" src=\"/js/sencha-touch.js\"></script>");
				out.println("<script type=\"text/javascript\" src=\"/js/mobileform.js?mobileformname="
						+ taskName + "\"></script>");
				out.println("<style type=\"text/css\">.demos-loading {  background: rgba(0,0,0,.3) url(../resources/shared/loading.gif) center center no-repeat;  display: block;  width: 10em;  height: 10em;  position: absolute;  top: 50%;  left: 50%;  margin-left: -5em;  margin-top: -5em; -webkit-border-radius: .5em; color: #fff; text-shadow: #000 0 1px 1px; text-align: center; padding-top: 8em; font-weight: bold;}</style>");
				out.println("</head>");
				out.println("<body>");
				out.println("</body>");
				out.println("</html>");
			}
		} else {
			response.sendRedirect(response.encodeRedirectURL(userService
					.createLoginURL(request.getRequestURI())));
		}
	}
}
