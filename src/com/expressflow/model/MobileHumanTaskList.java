package com.expressflow.model;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import javax.jdo.PersistenceManager;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.expressflow.datastore.PMF;
import com.expressflow.datastore.process.MobileHumanTaskDAO;
import com.expressflow.datastore.process.MobileFormListDAO;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.users.User;

/*
Copyright (c) 2011 Martin Vasko, Ph.D.

licensing@expressflow.com
http://expressflow.com/license
*/

public class MobileHumanTaskList extends Activity {
	
	private Element xmlSrc;
	public Element getXmlSrc() {
		return xmlSrc;
	}

	public void setXmlSrc(String xmlSrc) throws Exception{
		SAXBuilder builder = new SAXBuilder();
		Reader in = new StringReader(xmlSrc);
		Document doc = builder.build(in);
		this.xmlSrc = doc.getRootElement();
	}

	private MobileFormListDAO entity;
	
	public MobileFormListDAO getDAO(){
		return this.entity;
	}
	
	private String workerEmail;

	public void setWorkerEmail(String workerEmail){
		this.workerEmail = workerEmail;
	}
	
	public String getWorkerEmail() {
		return this.workerEmail;
	}
	
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public void persist(){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		MobileFormListDAO mobileHTLDAO = new MobileFormListDAO();
		if(this.entity.getName() != null)
			mobileHTLDAO.setName(this.entity.getName());
		XMLOutputter serializer = new XMLOutputter();
		serializer.setFormat(Format.getPrettyFormat());
		String xmlString = serializer.outputString(this.xmlSrc);
		mobileHTLDAO.setXmlSrc(new Text(xmlString));
		try {
            pm.makePersistent(mobileHTLDAO);
        } finally {
        	this.entity = mobileHTLDAO;
            pm.close();
        }
	}
	
	public static MobileHumanTaskList fromXML(Element element) throws Exception{
		MobileHumanTaskList mobileHTL = new MobileHumanTaskList();
		if(element.getAttributeValue("__name") != null)
			mobileHTL.entity.setName(element.getAttributeValue("__name"));
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		mobileHTL.setXmlSrc(outputter.outputString(element));
		return mobileHTL;
	}
}
