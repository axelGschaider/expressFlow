package com.expressflow.datastore.process;

/*
 * Copyright (c) 2011 Martin Vasko, Ph.D.
 * 
 * licensing@expressflow.com http://expressflow.com/license
 */

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.VersionStrategy;

import com.google.appengine.api.datastore.Key;


@PersistenceCapable(identityType=IdentityType.APPLICATION, detachable="true")
@javax.jdo.annotations.Version(strategy=VersionStrategy.VERSION_NUMBER, column="VERSION",
         extensions={@Extension(vendorName="datanucleus", key="field-name", value="version")})
public class TaskHistory implements Serializable {

  private static final long serialVersionUID = 1L;

  @PrimaryKey
  @Persistent(primaryKey="true", valueStrategy=IdGeneratorStrategy.IDENTITY)
  private Key id;
  
  private Long version;
  
  @Persistent
  private String uid;
  
  @Persistent
  private Task task;
  
  @Persistent
  private String description;
  
  
  public Key getId() {
	  return id;
  }
  
  public Long getVersion() {
	  return version;
  }
  
  public String getUid() {
	  return uid;
  }
  
  public Task getTask() {
	  return task;
  }
  public void setTask(Task task) {
	  this.task = task;
  }
  
  public String getDescription() {
	  return description;
  }
  public void setDescription(String description) {
	  this.description = description;
  }
}
