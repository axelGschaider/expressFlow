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
import com.google.appengine.api.datastore.Text;

public class SendSMS extends Activity {
	/**
	 * !!!!!!!! TO-DO !!!!!!!!
	 * Adapt these four values
	 * -- from -- a (maybe your's) twilio.com validated phone number
	 * -- ACCOUNTSID -- your account sid
	 * -- AUTHTOKEN -- these two are well known in a REST world...
	 * -- APIVERSION -- "2010-04-01" currently
	 * to your personal twilio.com Account details!
	 */
	private String from = "+16504926804";
	private String ACCOUNTSID = "AC2e82e255f3ae055cada2d29519c5c50e";
	private String AUTHTOKEN = "98ccf42d029301ee6e9f73e74b525535";
	private String APIVERSION = "2010-04-01";
	
	// The rest of the class can remain unchanged ;-)
	public static final String ID = "id";
	public static final String FROM = "from";
	public static final String TO = "to";
	public static final String BODY = "body";
	
	private Logger log = Logger.getLogger(SendSMS.class.getSimpleName());
	
	public String getACCOUNTSID() {
		return ACCOUNTSID;
	}

	public String getAUTHTOKEN() {
		return AUTHTOKEN;
	}

	public String getAPIVERSION() {
		return APIVERSION;
	}
	
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFrom() {
		return from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public Text getBody() {
		return body;
	}
	public void setBody(Text body) {
		this.body = body;
	}

	private String to;
	private Text body;
	
	@Override
	public void persist(){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity entity = new Entity("SendSMS");
		entity.setProperty(ID, id);
		entity.setProperty("predecessor", predecessor);
		entity.setProperty("ancestor", ancestor);
		entity.setProperty(FROM, from);
		entity.setProperty(TO, to);
		entity.setProperty(BODY, body);
		datastore.put(entity);
	}
	
	public static SendSMS fromXML(Element element){
		SendSMS send = new SendSMS();
		send.setBody(new Text(element.getText()));
		send.setId(element.getAttributeValue(ID));
		send.setTo(element.getAttributeValue(TO));
		return send;
	}
}
