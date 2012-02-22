package com.expressflow.jdo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Text;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Timer {
	@PrimaryKey
	@Persistent
	private String id;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	@Persistent
	private String name;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Persistent
	private int x;
	
	@Persistent
	private int y;
	
	@Persistent
	private String image;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	@Persistent
	private long trigger;
	
	public long getTrigger() {
		return trigger;
	}

	public void setTrigger(String trigger) {
		SimpleDateFormat sdfToDate = new SimpleDateFormat("yyyy-mm-dd HH:mm");
		try {
			Date date = sdfToDate.parse(trigger);
			this.trigger = date.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	@Persistent
	private String processId;
	
	private String xml;

	public String getXml() {
		return _xml.getValue();
	}

	public void setXml(String xml) {
		this._xml = new Text(xml);
	}
	
	// XML Model for the designer
	@Persistent
	private Text _xml;
	
	@Persistent
	private Text _execXml;

	public String getExecXml() {
		return this._execXml.getValue();
	}

	public void setExecXml(String execXml) {
		this._execXml = new Text(execXml);
	}
	
	private String execXml;

}
