package com.expressflow.datastore.process;

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
public class Activity {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key efid;
	
	@Persistent
	private String name;
	
	@Persistent
	private Key predecessor;
	
	@Persistent
	private Key ancestor;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Key getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(Key predecessor) {
		this.predecessor = predecessor;
	}

	public Key getAncestor() {
		return ancestor;
	}

	public void setAncestor(Key ancestor) {
		this.ancestor = ancestor;
	}

	public Key getEfid() {
		return efid;
	}
}
