package com.expressflow.engine.commands;

/*
 * Copyright (c) 2011 Martin Vasko, Ph.D.
 * 
 * licensing@expressflow.com http://expressflow.com/license
 */

import com.expressflow.engine.interfaces.ICommand;
import com.expressflow.engine.xml.ModelSingleton;
import com.expressflow.model.Activity;
import com.expressflow.model.Assign;
import com.expressflow.model.Variable;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

public class AssignCommand implements ICommand {
	
	// private ModelSingleton modelSingleton;

	@Override
	public void execute(String scopeID, Activity activity) {
		// modelSingleton = ModelSingleton.getInstance();
		MemcacheService memcache = MemcacheServiceFactory.getMemcacheService(scopeID);
		
		Assign assign = (Assign)activity;
		
		String fromExpression = "";
		if(assign.getFromExpression() != null)
			fromExpression = assign.getFromExpression();
		
		Variable fromVariable = new Variable();
		if(assign.getFromVariable() != null)
			fromVariable = (Variable)memcache.get(assign.getFromVariable().getName());
			//fromVariable = modelSingleton.variables.get(assign.getFromVariable().getName());
		
		String literal = "";
		if(assign.getLiteral() != null)
			literal = assign.getLiteral();
		
		Variable outputVariable = new Variable();
		if(assign.getOutputVariable() != null)
			outputVariable = (Variable)memcache.get(assign.getOutputVariable().getName());
			// outputVariable = modelSingleton.variables.get(assign.getOutputVariable().getName());
		
		String toExpression = "";
		if(assign.getToExpression() != null)
			toExpression = assign.getToExpression();
		
		// Currently only Variable assignments are supported
		if(fromVariable != null && outputVariable != null){
			// Assign from fromVariable to outputVariable and change internal datastructure
			outputVariable.setValue(fromVariable.getValue());
			memcache.delete(outputVariable.getName());
			memcache.put(outputVariable.getName(), outputVariable);
			// modelSingleton.variables.remove(outputVariable.getName());
			// modelSingleton.variables.put(outputVariable.getName(), outputVariable);
			assign.setOutputVariable(outputVariable);
		}
		
	}

}
