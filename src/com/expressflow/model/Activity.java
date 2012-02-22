package com.expressflow.model;

/*
Copyright (c) 2011 Martin Vasko, Ph.D.

licensing@expressflow.com
http://expressflow.com/license
*/

import java.util.logging.Logger;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;

public class Activity implements Comparable{
	private static Logger logger = Logger.getLogger(Activity.class.getSimpleName());
	
	protected int index;
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	protected String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(Long predecessor) {
		this.predecessor = predecessor;
	}

	public Long getAncestor() {
		return ancestor;
	}

	public void setAncestor(Long ancestor) {
		this.ancestor = ancestor;
	}

	protected Long predecessor;
	
	protected Long ancestor;
	
	protected String state;
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public void persist(){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity entity = new Entity("Activity");
		entity.setProperty("name", name);
		entity.setProperty("predecessor", predecessor);
		entity.setProperty("ancestor", ancestor);
		entity.setProperty("state", state);
		datastore.put(entity);
	}
	
	public static Activity load(Key efid){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Activity activity = new Activity();
		try{
			Entity entity = datastore.get(efid);
			activity.setName((String)entity.getProperty("name"));
			activity.setAncestor((Long)entity.getProperty("ancestor"));
			activity.setPredecessor((Long)entity.getProperty("predecessor"));
			activity.setState((String)entity.getProperty("state"));
		}
		catch(EntityNotFoundException enfe){
			logger.info(enfe.getMessage());
			activity = null;
		}
		return activity;
	}

	@Override
	public int compareTo(Object o) throws ClassCastException{
		if(!(o instanceof Activity))
			throw new ClassCastException("Object of class Activity expected!");
		int otherIndex = ((Activity)o).getIndex();
		return this.index - otherIndex;
	}
}
