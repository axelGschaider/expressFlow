package com.expressflow.jdo;

import com.google.gdata.data.DateTime;

public class GoogleCalendar {
	private String id;
	private String kind;
	private String title;
	private Boolean canEdit;
	private String content;
	private String edited;
	private String etag;
	private String updated;
	private String published;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public Boolean getCanEdit() {
		return canEdit;
	}
	public void setCanEdit(Boolean canEdit) {
		this.canEdit = canEdit;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEdited() {
		return edited;
	}
	public void setEdited(String edited) {
		this.edited = edited;
	}
	public String getEtag() {
		return etag;
	}
	public void setEtag(String etag) {
		this.etag = etag;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public String getPublished() {
		return published;
	}
	public void setPublished(String published) {
		this.published = published;
	}
}
