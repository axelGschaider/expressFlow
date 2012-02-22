package com.expressflow.jdo;

import java.util.Date;

public class File {
	public static final String NAME = "File";
	public static final String BUCKET = "bucket";
	public static final String KEY = "key";
	public static final String CREATION_DATE = "creationDate";
	public static final String ACCESS_DATE = "accessDate";
	public static final String OWNER = "owner";
	
	private String path;
	private Date creationDate;
	private Date accessDate;
	private String owner;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getAccessDate() {
		return accessDate;
	}
	public void setAccessDate(Date accessDate) {
		this.accessDate = accessDate;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
}
