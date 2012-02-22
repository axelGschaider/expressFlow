package com.expressflow.controller;

/*
 * Copyright (c) 2011 Martin Vasko, Ph.D.
 * 
 * licensing@expressflow.com http://expressflow.com/license
 */

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.jdom.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.expressflow.datastore.PMF;
import com.expressflow.datastore.process.ProcessDAO;
import com.expressflow.jdo.MobileForm;
import com.expressflow.jdo.Process;
import com.expressflow.servlets.JavaScriptFormatter;
import com.expressflow.utils.Constants;
import com.expressflow.utils.ProcessStates;
import com.google.appengine.api.appidentity.AppIdentityService;
import com.google.appengine.api.appidentity.AppIdentityServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@Controller
public class ProcessController {

	private static Logger logger = Logger.getLogger(ProcessController.class
			.getSimpleName());
	
	@RequestMapping(value = "/process/", method = RequestMethod.GET)
	public List<Process> getProcesses() throws IOException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (userService.isUserLoggedIn()) {
			PersistenceManager pm = PMF.getManager();
			Query query = pm.newQuery(Process.class);
			query.setFilter("creator == creatorParam");
			query.declareParameters("String creatorParam");
			try {
				List<Process> processes = (List<Process>) query
						.execute(user.getEmail());
				return processes;
			} catch (Exception e) {
				logger.warning(e.getMessage());
				return null;
			}
		}
		return null;
	}
	
	@RequestMapping(value = "/process/deploy/", method = RequestMethod.GET)
	public JsonNode getDeployedProcesses() throws IOException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (userService.isUserLoggedIn()) {
			PersistenceManager pm = PMF.getManager();
			Query query = pm.newQuery(Process.class);
			query.setFilter("creator == creatorParam && state == 'deployed'");
			query.declareParameters("String creatorParam");
			try {
				List<Process> results = (List<Process>) query
						.execute(user.getEmail());
				
				ObjectMapper mapper = new ObjectMapper();
				ArrayNode jsonArray = mapper.createArrayNode();
				
				if (!results.isEmpty()) {
					for (Process p : results) {
						ObjectNode jP = mapper.createObjectNode();
						jP.put("name", p.getName());
						jP.put("description", p.getDescription());
						// Query all matching Mobile Forms
						Query mQ = pm.newQuery(MobileForm.class);
						mQ.setFilter("method == methodParam");
						mQ.declareParameters("String methodParam");
						List<MobileForm> mResults = (List<MobileForm>)mQ.execute(p.getId());
						if(mResults.size() > 0){
							MobileForm m = mResults.get(0);
							jP.put("url", "http://" + Constants.cutServiceAccountName() + ".appspot.com" + "/mobileform/" + m.getId());
						}
						jsonArray.add(jP);
					}
				} else {
					// ... no results ...
				}
				return jsonArray;
			} catch (Exception e) {
				logger.warning(e.getMessage());
				return null;
			}
		}
		return null;
	}

	@RequestMapping(value = "/process/{id}", method = RequestMethod.GET)
	public Entity getProcessById(@PathVariable String id) throws EntityNotFoundException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (userService.isUserLoggedIn()) {
			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();
			Key key = KeyFactory.createKey("Process", Long.parseLong(id));
			Entity result = datastore.get(key);
			return result;
		} else
			return null;
	}
	
	@RequestMapping(value = "/process/init")
	public Process initProcess() {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (userService.isUserLoggedIn()) {
			Process process = new Process();
			Date now = new Date();
			process.setAccessDate(now);
			process.setCreationDate(now);
			process.setAccessDateView(DateFormat.getInstance().format(now));
			process.setCreationDateView(DateFormat.getInstance().format(now));
			process.setCreator(user.getEmail());
			process.setId(KeyFactory.keyToString(KeyFactory.createKey(user.getEmail(), now.getTime())));
			process.setXml("<process></process>");
			process.setExecXml("<process />");
			return process;
		} else
			return null;
	}
	
	@RequestMapping(value = "/process/deploy/{id}")
	public Process deployProcess(@PathVariable String id){
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (userService.isUserLoggedIn()) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try{
				Key k = KeyFactory.createKey(Process.class.getSimpleName(), id);
				Process p = pm.getObjectById(Process.class, k);
				p.setState(ProcessStates.DEPLOYED);
				return p;
			}finally{
				pm.close();
			}
		} else
			return null;
	}
	
	@RequestMapping(value= "/process/{id}", method = RequestMethod.POST)
	public Process saveProcess(@RequestBody Process process) throws IOException{
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (userService.isUserLoggedIn()) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				process.setId(process.getId().toUpperCase());
				process.setAccessDate(DateFormat.getInstance().parse(process.getAccessDateView()));
				if(process.getCreationDate() == null){
					process.setCreationDate(DateFormat.getInstance().parse(process.getCreationDateView()));
				}
				pm.makePersistent(process);
				return process;
			}
			catch(Exception e){
				e.printStackTrace(System.err);
				pm.close();
	        } finally {
	            pm.close();
	        }
		}
		return null;
	}
}
