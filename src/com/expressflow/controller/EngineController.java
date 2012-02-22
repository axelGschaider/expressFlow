package com.expressflow.controller;

/*
 * Copyright (c) 2011 Martin Vasko, Ph.D.
 * 
 * licensing@expressflow.com http://expressflow.com/license
 */

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.expressflow.utils.States;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;

@Controller
@RequestMapping("/engine")
public class EngineController {
	private static Logger logger = Logger.getLogger(EngineController.class.getSimpleName());
	
	@ModelAttribute("process")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Entity> listProcessQueue() throws IOException {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("Process");
		query.addFilter("state", Query.FilterOperator.EQUAL, States.QUEUED);
		List<Entity> processes = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
		return processes;
	}
	
	@ModelAttribute("process")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public boolean startProcess(@RequestBody String source) throws IOException {
		System.out.println(source);
		return true;
	}
}
