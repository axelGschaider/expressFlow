package com.expressflow.model;

/*
Copyright (c) 2011 Martin Vasko, Ph.D.

licensing@expressflow.com
http://expressflow.com/license
*/

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.logging.Logger;

import org.jdom.Element;

import com.expressflow.engine.xml.Parser;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class SequenceFlow extends Activity {
	private static final String ID = "ID";
	private static final String SOURCEREF = "SOURCEREF";
	private static final String TARGETREF = "TARGETREF";
	
	protected Queue<Activity> activities;
	
	public void setActivities(Queue<Activity> activities) {
		this.activities = activities;
	}

	protected Map<String, Variable> variables;
	
	public Map<String, Variable> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Variable> variables) {
		this.variables = variables;
	}

	private static Logger logger = Logger.getLogger(SequenceFlow.class.getSimpleName());
	
	public SequenceFlow(){
		activities = new PriorityQueue<Activity>();
		variables = new HashMap<String, Variable>();
	}
	
	public Queue<Activity> getActivities(){
		return activities;
	}
	
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSourceRef() {
		return sourceRef;
	}
	public void setSourceRef(String sourceRef) {
		this.sourceRef = sourceRef;
	}
	public String getTargetRef() {
		return targetRef;
	}
	public void setTargetRef(String targetRef) {
		this.targetRef = targetRef;
	}
	private String sourceRef;
	private String targetRef;
	
	@Override
	public void persist(){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity entity = new Entity("SequenceFlow");
		entity.setProperty(ID, id);
		entity.setProperty(SOURCEREF, sourceRef);
		entity.setProperty(TARGETREF, targetRef);
		datastore.put(entity);
		logger.info("SequenceFlow " + id + " persisted.");
	}
	
	public static SequenceFlow fromXML(Element element){
		SequenceFlow sf = new SequenceFlow();
		sf.setId(element.getAttributeValue(ID));
		sf.setSourceRef(element.getAttributeValue(SOURCEREF));
		sf.setTargetRef(element.getAttributeValue(TARGETREF));
		Iterator<?> iter = element.getChildren().iterator();
		while(iter.hasNext()){
			Activity activity = new Activity();
			activity = Parser.parse((Element)iter.next());
			if(activity != null && activity instanceof Variable){
				sf.variables.put(activity.getName(), (Variable)activity);
			}
			if(activity != null){
				activity.setIndex(sf.activities.size());
				sf.activities.add(activity);
			}
		}
		return sf;
	}
}
