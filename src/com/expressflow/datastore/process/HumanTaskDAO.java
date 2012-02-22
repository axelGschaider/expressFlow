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
import com.google.appengine.api.datastore.Text;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class HumanTaskDAO {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key efid;
	
	@Persistent
	private Text xmlSrc;
	
	@Persistent
	private String method;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Key getEfid() {
		return efid;
	}

	public Text getXmlSrc() {
		return xmlSrc;
	}

	public void setXmlSrc(Text xmlSrc) {
		this.xmlSrc = xmlSrc;
	}
}
