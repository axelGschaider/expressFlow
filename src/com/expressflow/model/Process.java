package com.expressflow.model;

/*
Copyright (c) 2011 Martin Vasko, Ph.D.

licensing@expressflow.com
http://expressflow.com/license
*/

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;

import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.expressflow.datastore.PMF;
import com.expressflow.datastore.process.ProcessDAO;
import com.expressflow.engine.xml.ModelSingleton;
import com.expressflow.engine.xml.Parser;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

public class Process extends SequenceFlow{
	
	private static final String NAME = "name";
	private static final String ACCESS = "access";
	private static final String ACCESS_RIGHTS = "accessRights";
	private static final String STATE = "state";
	private static final String TIMES_EXECUTED = "timesExecuted";
	private static final String ID = "id";
	private static final String XML = "xml";
	
	private ModelSingleton modelSingleton;
	
	
	private String xml;
	private String state;
	private String creator;
	private String creationDate;
	private String accessDate;
	private String access;
	private String accessRights;
	
	public String getAccessRights() {
		return accessRights;
	}

	public void setAccessRights(String accessRights) {
		this.accessRights = accessRights;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
		this.entity.setAccess(access);
	}

	public String getTimesExecuted() {
		return timesExecuted;
	}

	public void setTimesExecuted(String timesExecuted) {
		this.timesExecuted = timesExecuted;
		this.entity.setTimesExecuted(timesExecuted);
	}

	private String timesExecuted;

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
		this.entity.setCreator(creator);
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
		this.entity.setCreationDate(creationDate);
	}

	public String getAccessDate() {
		return accessDate;
	}

	public void setAccessDate(String accessDate) {
		this.accessDate = accessDate;
		this.entity.setAccessDate(accessDate);
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
		this.entity.setState(state);
	}

	private ProcessDAO entity;
	
	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	private String name;
	
	public Process(){
		modelSingleton = ModelSingleton.getInstance();
		entity = new ProcessDAO(NAME, XML);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.entity.setName(name);
	}

	private Long efid;
	
	public static final Process fromXML(Element element){ 
		Process processor = new Process();
		processor.setName(element.getAttributeValue(NAME));
		processor.entity.setName(element.getAttributeValue(NAME));
		processor.setAccess(element.getAttributeValue(ACCESS));
		processor.entity.setAccess(element.getAttributeValue(ACCESS));
		processor.setState(element.getAttributeValue(STATE));
		processor.entity.setState(element.getAttributeValue(STATE));
		processor.setTimesExecuted(element.getAttributeValue(TIMES_EXECUTED));
		processor.entity.setTimesExecuted(element.getAttributeValue(TIMES_EXECUTED));
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
		processor.setAccessDate(dateFormat.format(now).toString());
		processor.entity.setAccessDate(dateFormat.format(now).toString());
		
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		processor.setXml(outputter.outputString(element));
		
		Parser parser = new Parser(processor.getId());
		List<?> rootInfo = element.getChildren(); 
		// Iterate through Variables
		Iterator<?> variableIterator = rootInfo.iterator();
		while(variableIterator.hasNext()){
			parser.parseVariables(processor.getId(), (Element)variableIterator.next());
		}
		
		// Iterate through Process
		Iterator<?> elementIterator = rootInfo.iterator(); 
		while ( elementIterator.hasNext() )  { 
			Activity activity = new Activity();
			activity = Parser.parse((Element)elementIterator.next());
			activity.setIndex(processor.activities.size());
			processor.activities.add(activity);
		}
			
//		Iterator<?> iter = element.getChildren().iterator();
//		while(iter.hasNext()){
//			Activity activity = new Activity();
//			activity = Parser.parse((Element)iter.next());
//			if(activity != null && activity instanceof Variable){
//				processor.variables.put(activity.getName(), (Variable)activity);
//			}
//			else if(activity != null){
//				activity.setIndex(processor.activities.size());
//				processor.activities.add(activity);
//			}
//		}
		return processor;
	}
	
	@Override
	public void persist(){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ProcessDAO pDAO = new ProcessDAO(name, this.xml);
		pDAO.setState(this.state);
		pDAO.setCreationDate(this.creationDate);
		pDAO.setCreator(this.creator);
		pDAO.setAccessDate(this.accessDate);
		pDAO.setAccess(this.access);
		pDAO.setTimesExecuted(this.timesExecuted);
		pDAO.setXml(this.xml);
		try {
			if(pDAO.getId() == null)
				pm.makePersistent(pDAO);
        } finally {
        	this.entity = pDAO;
        	this.setId(pDAO.getId());
            pm.close();
        }
		
//		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
//		Entity entity = new Entity("Process");
//		if(efid != null)
//			entity.setProperty(ID, efid);
//		else{ 
//			Key key = KeyFactory.createKey(Process.class.getSimpleName(), new Date().getTime());
//			this.setId(key.toString());
//			entity.setProperty(ID, key.toString());
//		}
//		entity.setProperty(NAME, this.getName());
//		entity.setProperty(XML, this.getXml());
//		// Persist all Process Variables
//		for(Map.Entry<String, Variable> entry: modelSingleton.variables.entrySet()){
//			entry.getValue().persist();
//		}
//		
//		// Persist all Process Activities
//		Iterator<?> iterator = this.activities.iterator();
//		while(iterator.hasNext()){
//			Activity activity = (Activity)iterator.next();
//			activity.persist();
//		}
//		datastore.put(entity);
	}
	
	public static Process load(Key key){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Process processor = new Process();
		try{
			Entity entity = datastore.get(key);
		}
		catch(Exception ex){
			ex.printStackTrace(System.err);
		}
		return processor;
	}
}
