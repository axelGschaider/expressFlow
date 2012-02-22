package com.expressflow.model;

/*
Copyright (c) 2011 Martin Vasko, Ph.D.

licensing@expressflow.com
http://expressflow.com/license
*/

import java.io.Serializable;
import java.util.logging.Logger;

import org.jdom.Element;
import org.jdom.Text;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;

public class Variable extends Activity implements Serializable{
	
	private static final String NAME = "name";
	private static final String TYPE = "type";
	private static final String MESSAGE = "value";
	
	private String name;
	private String type;
	private Object value;
	
	public Object getValue() {
		if(value != null)
			return value;
		else
			return null;
	}

	public void setValue(Object _value) {
		this.value = _value;
	}

	public Variable(){};
	
	public Variable(String name, String type, Object value){
		this.name = name;
		this.type = type;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String _name) {
		this.name = _name;
	}
	public String getType() {
		return type;
	}
	public void setType(String _type) {
		this.type = _type;
	}
	
	public static Variable fromXML(Element element){
		Variable variable = new Variable();
		variable.setName(element.getAttributeValue(NAME));
		variable.setType(element.getAttributeValue(TYPE));
		if(element.getContent().size() > 0){
			Text text = (Text)element.getContent().get(0);
			variable.setValue(text.getValue());
		}
		return variable;
	}
	
	public void persist(){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity entity = new Entity("Variable");
		entity.setProperty(NAME, name);
		entity.setProperty(TYPE, type);
		entity.setProperty(MESSAGE, value);
		datastore.put(entity);
	}
	
	public static Variable load(Key efid){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Variable variable = new Variable();
		try{
			Entity entity = datastore.get(efid);
			variable.setName((String)entity.getProperty("name"));
			variable.setType((String)entity.getProperty("type"));
			variable.setValue((String)entity.getProperty("value"));
		}
		catch(EntityNotFoundException enfe){
			enfe.printStackTrace(System.err);
			variable = null;
		}
		return variable;
	}
}
