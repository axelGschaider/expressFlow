package com.expressflow.servlets.designer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stringtemplate.v4.*;

import com.expressflow.singleton.ProcessSingleton;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class ViewportServlet extends HttpServlet{
	private static final long serialVersionUID = -4571931798472512888L;

	private static final Logger log = Logger
	.getLogger(ViewportServlet.class.getName());
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException {
		response.setContentType("text/javascript");
		// UserService
		UserService userService = UserServiceFactory.getUserService(); 
		User user = userService.getCurrentUser();
		String path = request.getSession().getServletContext().getRealPath("/designer/templates/view/Viewport.js");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
		PrintWriter pw = response.getWriter();
		String line, viewport = "";
		while((line = reader.readLine()) != null){
			viewport += line;
		}
		ST st = new ST(viewport);
		MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
		if(cache.get(user.getEmail()) != null){
			st.add("googleServices", "{xtype: 'panel', title: 'Google Services', id: 'googleServicesPanel',	layout: { type: 'table', columns: 2	}, items:[] },");
		}
		st.add("googleServices", "");
		pw.println(st.render());
	}
}
