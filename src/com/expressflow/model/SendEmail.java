package com.expressflow.model;

/*
Copyright (c) 2011 Martin Vasko, Ph.D.

licensing@expressflow.com
http://expressflow.com/license
*/

import org.jdom.Element;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Text;

public class SendEmail extends Activity {
	private static final String RESPONSE_TO = "respond_to";
	private static final String SUBJECT = "subject";
	private static final String TO = "to";
	private static final String ID = "id";
	private static final String FROM = "from";
	private static final String NICKNAME = "nickname";

	
	private String response;
	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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

	private String subject;
	private String to;
	private Text body;
	private String id;
	private String from;
	private String nickname;
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void persist(){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity entity = new Entity("SendEmail");
		entity.setProperty(ID, id);
		entity.setProperty(RESPONSE_TO, response);
		entity.setProperty(SUBJECT, subject);
		entity.setProperty(TO, to);
		entity.setProperty(FROM, from);
		entity.setProperty(NICKNAME, nickname);
		datastore.put(entity);
	}
	
	public static SendEmail fromXML(Element element){
		SendEmail send = new SendEmail();
		send.setBody(new Text(element.getText()));
		send.setId(element.getAttributeValue(ID));
		send.setResponse(element.getAttributeValue(RESPONSE_TO));
		send.setSubject(element.getAttributeValue(SUBJECT));
		send.setTo(element.getAttributeValue(TO));
		send.setFrom(element.getAttributeValue(FROM));
		send.setNickname(element.getAttributeValue(NICKNAME));
		return send;
	}
}
