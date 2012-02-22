package com.expressflow.jdo;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class User {
	@PrimaryKey
	@Persistent
	private String id;
	
	@Persistent
	private int googleServicesActivated;
	
	@Persistent
	private String email;
	
	@Persistent
	private String nickname;
	
	@Persistent
	private long requestsPerDay;
	
	@Persistent
	private long lastRequestDay;
	
	@Persistent
	private String appId;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getGoogleServicesActivated() {
		return googleServicesActivated;
	}

	public void setGoogleServicesActivated(int googleServicesActivated) {
		this.googleServicesActivated = googleServicesActivated;
	}

	public long getRequestsPerDay() {
		return requestsPerDay;
	}

	public void setRequestsPerDay(long requestsPerDay) {
		this.requestsPerDay = requestsPerDay;
	}

	public long getLastRequestDay() {
		return lastRequestDay;
	}

	public void setLastRequestDay(long lastRequestDay) {
		this.lastRequestDay = lastRequestDay;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
