package com.expressflow.datastore.process;

/*
 * Copyright (c) 2011 Martin Vasko, Ph.D.
 * 
 * licensing@expressflow.com http://expressflow.com/license
 */

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class DirectInvoke extends Activity {
	
	@Persistent
	private String uri;
	
	@Persistent
	private String method;
	
	@Persistent
	private Text requestBody;
	
	@Persistent
	private String header;
	
	@Persistent
	private Key outputVariable;
	
	@Persistent
	private String responseHeader;
	
	@Persistent
	private String responseCode;
	
	@Persistent
	private int timeout;

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Text getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(Text requestBody) {
		this.requestBody = requestBody;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public Key getOutputvariable() {
		return outputVariable;
	}

	public void setOutputvariable(Key outputvariable) {
		this.outputVariable = outputvariable;
	}

	public String getResponseHeader() {
		return responseHeader;
	}

	public void setResponseHeader(String responseHeader) {
		this.responseHeader = responseHeader;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
}
