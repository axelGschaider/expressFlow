package com.expressflow.engine.commands;

/*
 * Copyright (c) 2011 Martin Vasko, Ph.D.
 * 
 * licensing@expressflow.com http://expressflow.com/license
 */

import java.util.HashMap;
import java.util.Map;

import com.expressflow.model.Process;

public class StructuredCommandSelector {
	private Map<String, StructuredCommand> commands = new HashMap<String, StructuredCommand>(){
		private static final long serialVersionUID = 1L;

		{
			put(Process.class.getSimpleName(), new ProcessCommand());
		}
	};
	
	public StructuredCommand getCommand(String activityName){
		return commands.get(activityName);
	}
}
