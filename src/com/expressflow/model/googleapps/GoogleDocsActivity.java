package com.expressflow.model.googleapps;

import java.util.HashMap;

import org.jdom.Element;

import com.expressflow.model.Activity;
import com.expressflow.model.SendEmail;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Text;

public class GoogleDocsActivity extends Activity {
	private static final String LINK = "link";
	private static final String ACTION = "action";
	private static final String EMAIL = "email";
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static final int PUBLISH = 1;
	public static final int DELETE = 2;
	
	private static HashMap<String, Integer> map = 
		new HashMap<String, Integer>(){
			private static final long serialVersionUID = 1L;

			{
				put("PUBLISH", 1);
				put("DELETE", 2);
			}
	};
	
	private String link;
	private String action;
	private String email;
	
	public static int get(String name){
		return map.get(name);
	}
	
	public String getAction() {
		return action.toUpperCase();
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	@Override
	public void persist(){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity entity = new Entity("GoogleDocsActivity");
		entity.setProperty(LINK, link);
		entity.setProperty(ACTION, action);
		entity.setProperty(EMAIL, email);
		datastore.put(entity);
	}

	public static GoogleDocsActivity fromXML(Element element){
		GoogleDocsActivity activity = new GoogleDocsActivity();
		activity.setLink(element.getAttributeValue(LINK));
		activity.setAction(element.getAttributeValue(ACTION));
		activity.setEmail(element.getAttributeValue(EMAIL));
		return activity;
	}
}
