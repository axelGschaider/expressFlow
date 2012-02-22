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

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import com.expressflow.datastore.process.ProcessDAO;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class User2BP {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key efid;
	
	@Persistent
	private Key userid; 
	
	@Persistent
	private Key emid;
	
	@Persistent
	private Key roleid;

	public User2BP(Long userid, Long emid, Long roleid){
		this.userid = KeyFactory.createKey(User.class.getSimpleName(), userid);
		this.emid = KeyFactory.createKey(ProcessDAO.class.getSimpleName(), emid);
		this.roleid = KeyFactory.createKey(Role.class.getSimpleName(), roleid);
	}
	
	public Key getUserid() {
		return userid;
	}

	public void setUserid(Key userid) {
		this.userid = userid;
	}

	public Key getEmid() {
		return emid;
	}

	public void setEmid(Key emid) {
		this.emid = emid;
	}

	public Key getRoleid() {
		return roleid;
	}

	public void setRoleid(Key roleid) {
		this.roleid = roleid;
	}

	public Key getEfid() {
		return efid;
	}
}
