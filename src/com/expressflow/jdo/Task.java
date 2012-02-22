package com.expressflow.jdo;

import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Task {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private String id;
	
	@Persistent
	private int x;
	
	@Persistent
	private int y;
	
	@Persistent
	private String name;
	
	@Persistent
	private byte[] image;
	
	@Persistent
	private String publicAppKey;
	
	@Persistent
	private String privateAppKey;
	
	@Persistent
	private String url;
	
	@Persistent
	private String namespace;
	
	@Persistent
	private String description;
	
	@Persistent
	private String processId;

	@Persistent
	private String creator;
	
	@Persistent
	private String recipient;
	
	@Persistent
	private String visibility;
	
	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	@SuppressWarnings("unused")
	private String xml;
	
	@Persistent
	private Text _xml;
	
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getXml() {
		return _xml.getValue();
	}

	public void setXml(String xml) {
		this._xml = new Text(xml);
	}
	
	@SuppressWarnings("unused")
	private String execXml;
	
	@Persistent
	private Text _execXml;
	
	public String getExecXml() {
		return _execXml.getValue();
	}

	public void setExecXml(String _xmlSrc) {
		this._execXml = new Text(_xmlSrc);
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getPublicAppKey() {
		return publicAppKey;
	}

	public void setPublicAppKey(String publicAppKey) {
		this.publicAppKey = publicAppKey;
	}

	public String getPrivateAppKey() {
		return privateAppKey;
	}

	public void setPrivateAppKey(String privateAppKey) {
		this.privateAppKey = privateAppKey;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
}
