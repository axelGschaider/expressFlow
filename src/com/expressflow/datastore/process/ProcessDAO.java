package com.expressflow.datastore.process;

/*
 * Copyright (c) 2011 Martin Vasko, Ph.D.
 * 
 * licensing@expressflow.com http://expressflow.com/license
 */

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class ProcessDAO {
	
	@Persistent
	private byte[] image;
	
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@PrimaryKey
	@Persistent
	private String id;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id){
		this.id = id;
	}

	@Persistent
	private String timesExecuted;
	
	@Persistent
	private String access;

	public String getTimesExecuted() {
		return timesExecuted;
	}

	public void setTimesExecuted(String timesExecuted) {
		this.timesExecuted = timesExecuted;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	@Persistent
	private String creator;
	
	@Persistent
	private String creationDate;
	
	@Persistent
	private String accessDate;
	
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getAccessDate() {
		return accessDate;
	}

	public void setAccessDate(String accessDate) {
		this.accessDate = accessDate;
	}

	@Persistent
	private String xml;
	
	@Persistent
	private String name;
	
	@Persistent
	private String state;
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProcessDAO(String name, String xml){
		this.name = name;
		this.xml = xml;
	}

	
}
