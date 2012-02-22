package com.expressflow.servlets;

/*
Copyright (c) 2011 Martin Vasko, Ph.D.

licensing@expressflow.com
http://expressflow.com/license
*/

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class MobileFormServlet extends HttpServlet {
	private static final long serialVersionUID = -6351018213868233338L;

	private static final Logger log = Logger
			.getLogger(MobileFormServlet.class.getName());

	private Map<String, String> formElements = new HashMap<String, String>();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/javascript");

		String taskId = request.getParameter("mobileformname");
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		if (taskId != null && user != null) {
			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();
			Key key = KeyFactory.createKey("MobileForm", taskId);
			

			Document doc = null;
			try {
				Entity result = datastore.get(key);
				Text xmlTxt = (Text)result.getProperty("execXml");
				doc = new SAXBuilder().build(new InputSource(new StringReader(
						xmlTxt.getValue())));
				Element root = doc.getRootElement();

				PrintWriter out = new PrintWriter(response.getWriter());

				out.println(JavaScriptFormatter.ExtSetup("User"));

				// Parse Model Fields 
				List<?> children = root.getChildren("variable");
				Iterator<?> childIterator = children.iterator();
				
				while(childIterator.hasNext()){
					Element child = (Element)childIterator.next();
					out.println(JavaScriptFormatter.ExtModelField(
							child.getAttribute("name").getValue(), child.getContent(0).getValue()));
					out.println(",");
				}

				if (result.getProperty("name") != null)
					out.println(JavaScriptFormatter.ExtFormBase(
							"/exec/" + (String)result.getProperty("method"), (String)result.getProperty("name")));
				else
					out.println(JavaScriptFormatter.ExtFormBase(
							"/exec/" + (String)result.getProperty("method"), "Mobile Form "
									+ (String)result.getProperty("name")));

				// Parse FormFields
				Iterator<?> formFieldIterator = children.iterator();
				while(formFieldIterator.hasNext()){
					Element formChild = (Element)formFieldIterator.next();
					out.println(JavaScriptFormatter.ExtTextField(formChild.getAttributeValue("name")));
					out.println(",");
				}
				
				out.println(JavaScriptFormatter.ExtDockItems());

				int mgrIndex = 1;
				Iterator<?> mgrFieldIterator = children.iterator();
				while(mgrFieldIterator.hasNext()){
					int offset = children.size() - mgrIndex;
					Element child = (Element)mgrFieldIterator.next();
					if(child.getContentSize() > 0){
					out.println(JavaScriptFormatter
							.ExtModelManagerItem(child.getAttributeValue("name"),
									child.getContent(0).getValue()));
					} else {
						out.println(JavaScriptFormatter
								.ExtModelManagerItem(child.getAttributeValue("name"),
										"Enter value"));
					}
					if(offset > 0)
						out.println(",");
				}

				out.println(JavaScriptFormatter.ExtLoadModel("/exec/" + (String)result.getProperty("method")));
			} catch (Exception jde) {
				PrintWriter out = new PrintWriter(response.getWriter());
				out.println("<html>");
				out.println("<head>");
				out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
				out.println("<title>expressFlow - Mobile Form - Exception</title>");
				out.println("</head>");
				out.println("<body>");
				out.println(jde.getMessage());
				out.println("</body>");
				out.println("</html>");
			}
		}
	}
}
