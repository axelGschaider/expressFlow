package com.expressflow.datastore;

/*
 * Copyright (c) 2011 Martin Vasko, Ph.D.
 * 
 * licensing@expressflow.com http://expressflow.com/license
 */

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Application {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key appid;
	
	@Persistent
	private String applicationName;
	
	@Persistent
	private String lowerApplicationName;
	
	@Persistent
	private String description;

	@Persistent
	private String url;
	
	public Application(String applicationName, String lowerApplicationName, String description, String url){
		this.applicationName = applicationName;
		this.lowerApplicationName = lowerApplicationName;
		this.description = description;
		this.url = url;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getLowerApplicationName() {
		return lowerApplicationName;
	}

	public void setLowerApplicationName(String lowerApplicationName) {
		this.lowerApplicationName = lowerApplicationName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Key getAppid() {
		return appid;
	}
	
	public void setAppid(Key appid) {
		this.appid = appid;
	}
}
