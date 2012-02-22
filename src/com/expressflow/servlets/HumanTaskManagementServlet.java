package com.expressflow.servlets;

/*
 * Copyright (c) 2011 Martin Vasko, Ph.D.
 * 
 * licensing@expressflow.com http://expressflow.com/license
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.jdom.transform.JDOMResult;
import org.jdom.transform.JDOMSource;
import org.xml.sax.InputSource;

import com.expressflow.datastore.PMF;
import com.expressflow.datastore.process.HumanTaskDAO;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class HumanTaskManagementServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html");

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (user != null) {
			StringBuilder sb = new StringBuilder(request.getRequestURI());
			String taskName = sb.substring(11, sb.length());
			if (taskName != null) {
				Long id = Long.parseLong(taskName);
				PersistenceManager pm = PMF.get().getPersistenceManager();
				Key k = KeyFactory.createKey(
						HumanTaskDAO.class.getSimpleName(), id);
				HumanTaskDAO HT = pm.getObjectById(HumanTaskDAO.class, k);

				Document doc = null;
				try {
					doc = new SAXBuilder().build(new InputSource(
							new StringReader(HT.getXmlSrc().getValue())));
					Element root = doc.getRootElement();

					Source xmlFile = new JDOMSource(doc);
					JDOMResult htmlResult = new JDOMResult();
					Transformer transformer = TransformerFactory.newInstance()
							.newTransformer(
									new StreamSource("xslt/humantask.xsl"));
					transformer.transform(xmlFile, htmlResult);
					XMLOutputter xmlOutputter = new XMLOutputter();
					xmlOutputter.output(htmlResult.getDocument(),
							response.getWriter());
				} catch (Exception e) {
					PrintWriter out = new PrintWriter(response.getWriter());
					out.println("<html>");
					out.println("<head>");
					out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
					out.println("<title>expressFlow - Engine - Exception</title>");
					out.println("</head>");
					out.println("<body>");
					out.println(e.getMessage());
					out.println("</body>");
					out.println("</html>");
				}
			} else {
				response.sendRedirect(response.encodeRedirectURL(userService
						.createLoginURL(request.getRequestURI())));
			}
		} else  {
			response.sendRedirect(response.encodeRedirectURL(userService
					.createLoginURL(request.getRequestURI())));
		}
	}
}
