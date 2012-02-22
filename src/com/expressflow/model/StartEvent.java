package com.expressflow.model;

/*
Copyright (c) 2011 Martin Vasko, Ph.D.

licensing@expressflow.com
http://expressflow.com/license
*/

import java.util.logging.Logger;

import org.jdom.Element;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;

public class StartEvent extends Activity {

	private static final String ID = "ID";
	
	private static Logger logger = Logger.getLogger(StartEvent.class.getSimpleName());
	
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public void persist(){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity entity = new Entity("StartEvent");
		entity.setProperty(ID, id);
		datastore.put(entity);
	}
	
	public static StartEvent fromXML(Element element){
		StartEvent se = new StartEvent();
		se.setId(element.getAttributeValue(ID));
		return se;
	}
	
	public static StartEvent load(Key key){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		StartEvent se = new StartEvent();
		try{
			Entity entity = datastore.get(key);
		}
		catch(EntityNotFoundException enfe){
			logger.info(enfe.toString());
			se = null;
		}
		return se;
	}
}
