package com.expressflow.jdo;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Text;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class MobileForm {
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
	private String name;
	
	@SuppressWarnings("unused")
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
		return _execXml.getValue();
	}

	public void setExecXml(String _xmlSrc) {
		this._execXml = new Text(_xmlSrc);
	}
	
	@Persistent
	private String method;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	@Persistent
	private String creator;

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
}
