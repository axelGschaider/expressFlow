package com.expressflow.servlets.manage;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class ManageProcessesServlet extends HttpServlet {
	private static final long serialVersionUID = -6185974479830270631L;
	
	private static final Logger logger = Logger.getLogger(ManageProcessesServlet.class.getSimpleName());
	
	public void doGet(HttpServletRequest requ, HttpServletResponse resp)
			throws IOException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (user != null) {
			resp.setContentType("text/html");
			resp.getOutputStream()
					.println("<html>" +
							"<head>" +
							"<title>expressFlow - My Processes</title>" +
							"<link rel='stylesheet' type='text/css' href='css/touch/sencha-touch.css' />" +
							"<script type='text/javascript' src='myprocesses/touch/sencha-touch-all.js'></script>" +
							"<script type='text/javascript' src='myprocesses/model/Process.js'></script>" +
							"<script type='text/javascript' src='myprocesses/store/ProcessStore.js'></script>" +
							"<script type='text/javascript' src='myprocesses/app.js'></script>" +
							"</head>" +
							"<body></body>" +
							"</html>");
		} else {
			resp.sendRedirect(resp.encodeRedirectURL(userService
					.createLoginURL(requ.getRequestURI())));
		}
	}
}
