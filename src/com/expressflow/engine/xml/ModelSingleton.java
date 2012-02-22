package com.expressflow.engine.xml;

/*
 * Copyright (c) 2011 Martin Vasko, Ph.D.
 * 
 * licensing@expressflow.com http://expressflow.com/license
 */

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import com.expressflow.datastore.process.StructuredActivity;
import com.expressflow.model.Activity;
import com.expressflow.model.Variable;

public class ModelSingleton {
	private static ModelSingleton _instance = null;
	
	// Singleton properties
	// public Map<String, Variable> variables;
	public Queue<Activity> activities;
	public Map<String, StructuredActivity> structuredActivities;
	
	private ModelSingleton(){
		// variables = new HashMap<String, Variable>();
		activities = new PriorityQueue<Activity>();
		structuredActivities = new HashMap<String, StructuredActivity>();
	}
	
	public static ModelSingleton getInstance(){
		if(_instance == null){
			_instance = new ModelSingleton();
		}
		return _instance;
	}
}
