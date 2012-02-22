package com.expressflow.engine.commands;

/*
 * Copyright (c) 2011 Martin Vasko, Ph.D.
 * 
 * licensing@expressflow.com http://expressflow.com/license
 */

import java.util.Iterator;
import java.util.Queue;

import com.expressflow.engine.interfaces.ICommand;
import com.expressflow.engine.xml.Parser;
import com.expressflow.model.Activity;
import com.expressflow.model.SequenceFlow;

public class SequenceFlowCommand implements ICommand {

	@Override
	public void execute(String scopeID, Activity activity) {
		SequenceFlow sf = (SequenceFlow)activity;
		
		Parser parser = new Parser(scopeID);
		
		Queue<Activity> activities = sf.getActivities();
		Iterator<?> iterator = activities.iterator();
		
		while(iterator.hasNext()){
			Activity act = activities.remove();
			parser.executingObjectParse(act);
		}
	}

}
