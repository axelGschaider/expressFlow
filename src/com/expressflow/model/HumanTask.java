package com.expressflow.model;

/*
Copyright (c) 2011 Martin Vasko, Ph.D.

licensing@expressflow.com
http://expressflow.com/license
*/

import javax.jdo.PersistenceManager;

import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.expressflow.datastore.PMF;
import com.expressflow.datastore.process.HumanTaskDAO;
import com.expressflow.datastore.process.MobileHumanTaskDAO;
import com.google.appengine.api.datastore.Text;

public class HumanTask extends Activity {
	private Text xmlSrc;
	private HumanTaskDAO entity;
	
	public HumanTask(){
		entity = new HumanTaskDAO();
	}
	
	public Text getXmlSrc() {
		return xmlSrc;
	}

	public void setXmlSrc(Text xmlSrc) {
		this.xmlSrc = xmlSrc;
	}
	
	public HumanTaskDAO getDAO(){
		return this.entity;
	}
	
	@Override
	public void persist(){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		HumanTaskDAO htDAO = new HumanTaskDAO();
		htDAO.setXmlSrc(this.xmlSrc);
		try {
            pm.makePersistent(htDAO);
        } finally {
        	this.entity = htDAO;
            pm.close();
        }
	}
	
	public static HumanTask fromXML(Element element){
		HumanTask ht = new HumanTask();
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		ht.setXmlSrc(new Text(outputter.outputString(element)));
		return ht;
	}
}
