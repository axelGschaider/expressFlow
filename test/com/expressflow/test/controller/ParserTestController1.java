package com.expressflow.test.controller;

/*
Copyright (c) 2011 Martin Vasko, Ph.D.

licensing@expressflow.com
http://expressflow.com/license
*/

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.expressflow.engine.xml.Parser;

@Controller
@RequestMapping("/test/parser")
public class ParserTestController1 {
	private static Logger log = Logger.getLogger(ParserTestController1.class.getSimpleName());
	
	@RequestMapping(value = "/", method = RequestMethod.GET) 
	public String testParser1(){
		Document doc = null;
		try {
			Parser parser = new Parser("someFakeId");
			doc = new SAXBuilder().build("testresources/test1.xml");
			Element root = doc.getRootElement();
			List<?> rootInfo = root.getChildren(); 
			Iterator<?> iterator = rootInfo.iterator(); 
			while ( iterator.hasNext() )   
				parser.executingParse((Element)iterator.next() );

			
		} catch (Exception e) {
			log.warning(e.getMessage());
		}
		String message = "Parsed successfully!\n";
		if(doc != null)
			message += doc.toString();
		else
			message = "Parsing failed!";
		return message;
	}
}
