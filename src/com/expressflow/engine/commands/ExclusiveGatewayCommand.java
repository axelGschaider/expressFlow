package com.expressflow.engine.commands;

/*
 * Copyright (c) 2011 Martin Vasko, Ph.D.
 * 
 * licensing@expressflow.com http://expressflow.com/license
 */

import java.util.logging.Logger;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

import com.expressflow.engine.interfaces.ICommand;
import com.expressflow.model.Activity;
import com.expressflow.model.ExclusiveGateway;

public class ExclusiveGatewayCommand implements ICommand {
	
	private static Logger log = Logger.getLogger(ExclusiveGatewayCommand.class
			.getSimpleName());

	@Override
	public void execute(String scopeID, Activity activity) {
		try {
			ExclusiveGateway eg = (ExclusiveGateway)activity;
			
			// Create or retrieve a JexlEngine
	        JexlEngine jexl = new JexlEngine();
	        // Create an expression object
	        String jexlExp = eg.getExpression();
	        Expression e = jexl.createExpression( jexlExp );
	        
	        // Create a context and add data
	        JexlContext jc = new MapContext();
	        jc.set("activity", activity );

	        // Now evaluate the expression, getting the result
	        Boolean decision = (Boolean)e.evaluate(jc);
	        
	        if(decision){
	        	SequenceFlowCommand ifBranchCommand = new SequenceFlowCommand();
	        	ifBranchCommand.execute(scopeID, eg.getIfSeq());
	        }
	        else{
	        	SequenceFlowCommand elseBranchCommand = new SequenceFlowCommand();
	        	elseBranchCommand.execute(scopeID, eg.getElseSeq());
	        }
	        
			
		} catch (Exception ioe) {
			log.warning(ioe.toString());
		}
	}

}
