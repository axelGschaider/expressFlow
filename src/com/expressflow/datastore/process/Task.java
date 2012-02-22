package com.expressflow.datastore.process;

/*
 * Copyright (c) 2011 Martin Vasko, Ph.D.
 * 
 * licensing@expressflow.com http://expressflow.com/license
 */

import java.io.Serializable;
import java.util.List;

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
public class Task implements Serializable {

  private static final long serialVersionUID = 1L;
	
  public enum TaskStatus {
    CREATED,
    ASSIGNED,
    FINISHED
  }
  
  @PrimaryKey
  @Persistent(primaryKey="true", valueStrategy=IdGeneratorStrategy.IDENTITY)
  private Key id;
  
  private Long version;
  
  @Persistent
  private String uid;
  
  @Persistent
  private String title;
  
  @Persistent
  private String assignedTo;
  
  @Persistent
  private TaskStatus status;
  
  @Persistent(mappedBy="task")
  private List<TaskHistory> history;
  
  
  public Key getId() {
    return id;
  }
  
  public Long getVersion() {
    return version;
  }
  
  public String getUid() {
    return uid;
  }
  
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  
  public String getAssignedTo() {
    return assignedTo;
  }
  public void setAssignedTo(String assignedTo) {
    this.assignedTo = assignedTo;
  }
  
  public TaskStatus getStatus() {
    return status;
  }
  public void setStatus(TaskStatus status) {
    this.status = status;
  }
  
  public List<TaskHistory> getHistory() {
	  return history;
  }
  public void setHistory(List<TaskHistory> history) {
	  this.history = history;
  }
}
