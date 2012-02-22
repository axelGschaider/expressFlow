package com.expressflow.model;

/*
Copyright (c) 2011 Martin Vasko, Ph.D.

licensing@expressflow.com
http://expressflow.com/license
*/

import java.util.Date;

import javax.jdo.PersistenceManager;

import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.expressflow.datastore.PMF;
import com.expressflow.datastore.process.MobileHumanTaskDAO;
import com.google.appengine.api.datastore.Text;

public class MobileForm extends Activity {
	
	private Text xmlSrc;
	private MobileHumanTaskDAO entity;
	
	public MobileForm(){
		entity = new MobileHumanTaskDAO();
	}
	
	public Text getXmlSrc() {
		return xmlSrc;
	}

	public void setXmlSrc(Text xmlSrc) {
		this.xmlSrc = xmlSrc;
	}
	
	public MobileHumanTaskDAO getDAO(){
		return this.entity;
	}

	@Override
	public void persist(){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		MobileHumanTaskDAO mobileHTDAO = new MobileHumanTaskDAO();
		if(this.entity.getName() != null)
			mobileHTDAO.setName(this.entity.getName());
		mobileHTDAO.setXmlSrc(this.xmlSrc);
		try {
            pm.makePersistent(mobileHTDAO);
        } finally {
        	this.entity = mobileHTDAO;
            pm.close();
        }
		
//		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
//		Entity entity = new Entity("MobileHumanTask");
//		Key efid = KeyFactory.createKey(MobileHumanTask.class.getSimpleName(), new Date().getTime());
//		entity.setProperty("efid", efid);
//		entity.setProperty("xmlSrc", this.xmlSrc);
//		datastore.put(entity);
	}
	
	public static MobileForm fromXML(Element element){
		MobileForm mobileHT = new MobileForm();
		if(element.getAttributeValue("__name") != null)
			mobileHT.entity.setName(element.getAttributeValue("__name"));
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		mobileHT.setXmlSrc(new Text(outputter.outputString(element)));
		return mobileHT;
	}
}
