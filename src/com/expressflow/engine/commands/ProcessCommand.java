package com.expressflow.engine.commands;

/*
 * Copyright (c) 2011 Martin Vasko, Ph.D.
 * 
 * licensing@expressflow.com http://expressflow.com/license
 */

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.logging.Logger;

import com.expressflow.model.Activity;
import com.expressflow.model.Process;
import com.expressflow.model.Variable;

public class ProcessCommand extends StructuredCommand{
	
	private static Logger log = Logger.getLogger(ProcessCommand.class
			.getSimpleName());
	
	private Map<String, Variable> variables;
	private Queue<Activity> activities;
	
	public ProcessCommand(){
		variables = new HashMap<String, Variable>();
		activities = new PriorityQueue<Activity>();
	}
	
	public void execute(Process process) {
		variables = process.getVariables();
		activities = process.getActivities();
		for(Map.Entry<String, Variable> entry : variables.entrySet()){
			Variable variable = entry.getValue();
		}
	}
}
