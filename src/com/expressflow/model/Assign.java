package com.expressflow.model;

/*
Copyright (c) 2011 Martin Vasko, Ph.D.

licensing@expressflow.com
http://expressflow.com/license
*/

import javax.jdo.PersistenceManager;

import org.jdom.Element;

import com.expressflow.datastore.PMF;
import com.expressflow.datastore.process.AssignDAO;
import com.expressflow.engine.xml.ModelSingleton;
import com.expressflow.utils.NameUtil;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

public class Assign extends Activity{
	private static final String FROM_EXPRESSION = "fromexpr";
	private static final String FROM_VARIABLE = "fromvariable";
	private static final String LITERAL = "literal";
	private static final String TO_EXPRESSION = "toexpr";
	private static final String OUTPUT_VARIABLE = "outputvariable";
	
	private String fromExpression;
	private Variable fromVariable;
	private String literal;
	
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
	private String toExpression;
	private Variable outputVariable;
	
	public static Assign fromXML(Element element){
		ModelSingleton model = ModelSingleton.getInstance();
		Assign assign = new Assign();
		assign.setFromExpression(element.getAttributeValue(FROM_EXPRESSION));
		MemcacheService memcache = MemcacheServiceFactory.getMemcacheService();
		Variable fromVar = (Variable)memcache.get(NameUtil.normalizeVariableName(element.getAttributeValue(FROM_VARIABLE)));
		assign.setFromVariable(fromVar);

		// assign.setFromVariable(model.variables.get(NameUtil.normalizeVariableName(element.getAttributeValue(FROM_VARIABLE))));
		assign.setLiteral(element.getAttributeValue(LITERAL));
		Variable outVar = (Variable)memcache.get(NameUtil.normalizeVariableName(element.getAttributeValue(OUTPUT_VARIABLE)));
		assign.setOutputVariable(outVar);
			
		// assign.setOutputVariable(model.variables.get(NameUtil.normalizeVariableName(element.getAttributeValue(OUTPUT_VARIABLE))));
		assign.setToExpression(element.getAttributeValue(TO_EXPRESSION));
		return assign;
	}
	
	public static Assign load(Key key){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		AssignDAO a = pm.getObjectById(AssignDAO.class, key);
		Assign assign = new Assign();
		assign.setFromExpression(a.getFromExpression());
		assign.setFromVariable(a.getFromVariable());
		assign.setLiteral(a.getLiteral());
		assign.setOutputVariable(a.getOutputVariable());
		assign.setToExpression(a.getToExpression());
		return assign;
	}
}
