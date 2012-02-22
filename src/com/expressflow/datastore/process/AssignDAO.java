package com.expressflow.datastore.process;

/*
 * Copyright (c) 2011 Martin Vasko, Ph.D.
 * 
 * licensing@expressflow.com http://expressflow.com/license
 */

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.expressflow.model.Variable;
import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class AssignDAO {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key efid;
	
	@Persistent
	private String fromExpression;
	
	public String getFromExpression() {
		return fromExpression;
	}

	public void setFromExpression(String fromExpression) {
		this.fromExpression = fromExpression;
	}

	public Variable getFromVariable() {
		return fromVariable;
	}

	public void setFromVariable(Variable fromVariable) {
		this.fromVariable = fromVariable;
	}

	public String getLiteral() {
		return literal;
	}

	public void setLiteral(String literal) {
		this.literal = literal;
	}

	public String getToExpression() {
		return toExpression;
	}

	public void setToExpression(String toExpression) {
		this.toExpression = toExpression;
	}

	public Variable getOutputVariable() {
		return outputVariable;
	}

	public void setOutputVariable(Variable outputVariable) {
		this.outputVariable = outputVariable;
	}

	public Key getEfid() {
		return efid;
	}

	@Persistent
	private Variable fromVariable;
	
	@Persistent
	private String literal;
	
	@Persistent
	private String toExpression;
	
	@Persistent
	private Variable outputVariable;
}
