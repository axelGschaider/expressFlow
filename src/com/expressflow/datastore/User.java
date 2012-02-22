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

import com.expressflow.utils.Constants;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class User {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key userid;

	@Persistent
	private Key applicationid;

	@Persistent
	private String username;

	@Persistent
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Persistent
	private Long lastActivityDate;

	@Persistent
	private Long lastRequest;

	@Persistent
	private double requestsPerDay;

	@Persistent
	private String privateKey;

	@Persistent
	private String password;

	// Default Constructor
	public User() {

	}

	public User(Long applicationid, String username, String email,
			String loweredUsername, Long lastActivityDate, Long lastRequest,
			Double requestsPerDay, String privateKey, String password,
			String passwordFormat) {
		this.applicationid = KeyFactory.createKey(Application.class.getSimpleName(), Constants.APPLICATION_ID);
		this.username = username;
		this.email = email;
		this.lastActivityDate = lastActivityDate;
		this.lastRequest = lastRequest;
		this.requestsPerDay = requestsPerDay;
		this.privateKey = privateKey;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getLastActivityDate() {
		return lastActivityDate;
	}

	public void setLastActivityDate(Long lastActivityDate) {
		this.lastActivityDate = lastActivityDate;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Key getUserid() {
		return userid;
	}

	public void setApplicationid(Key applicationid) {
		this.applicationid = applicationid;
	}

	public Key getApplicationid() {
		return applicationid;
	}

	public void setLastRequest(Long lastRequest) {
		this.lastRequest = lastRequest;
	}

	public Long getLastRequest() {
		return lastRequest;
	}

	public void setRequestsPerDay(double requestsPerDay) {
		this.requestsPerDay = requestsPerDay;
	}

	public double getRequestsPerDay() {
		return requestsPerDay;
	}
}
