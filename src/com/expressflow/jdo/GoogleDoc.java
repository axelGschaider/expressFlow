package com.expressflow.jdo;

import com.google.gdata.data.DateTime;

public class GoogleDoc {

	private String title;
	private String resourceId;
	private String docType;
	private String link;
	private String lastViewed;
	private String lastUpdated;
	public String getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	private String modifiedBy;
	private String modifiedByEmail;
	private Long quotaUsed;
	private boolean isViewed;
	private boolean writersCanInvite;
	private boolean hidden;
	private boolean starred;
	private boolean trashed;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getLastViewed() {
		return lastViewed;
	}
	public void setLastViewed(String lastViewed) {
		this.lastViewed = lastViewed;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getModifiedByEmail() {
		return modifiedByEmail;
	}
	public void setModifiedByEmail(String modifiedByEmail) {
		this.modifiedByEmail = modifiedByEmail;
	}
	public Long getQuotaUsed() {
		return quotaUsed;
	}
	public void setQuotaUsed(Long quotaUsed) {
		this.quotaUsed = quotaUsed;
	}
	public boolean isViewed() {
		return isViewed;
	}
	public void setViewed(boolean isViewed) {
		this.isViewed = isViewed;
	}
	public boolean isWritersCanInvite() {
		return writersCanInvite;
	}
	public void setWritersCanInvite(boolean writersCanInvite) {
		this.writersCanInvite = writersCanInvite;
	}
	public boolean isHidden() {
		return hidden;
	}
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	public boolean isStarred() {
		return starred;
	}
	public void setStarred(boolean starred) {
		this.starred = starred;
	}
	public boolean isTrashed() {
		return trashed;
	}
	public void setTrashed(boolean trashed) {
		this.trashed = trashed;
	}
}
