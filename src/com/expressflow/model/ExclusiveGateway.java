package com.expressflow.model;

/*
Copyright (c) 2011 Martin Vasko, Ph.D.

licensing@expressflow.com
http://expressflow.com/license
*/

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.jdom.Content;
import org.jdom.Element;

import com.expressflow.engine.xml.ModelSingleton;
import com.expressflow.engine.xml.Parser;

public class ExclusiveGateway extends Activity {
	
	public static final String CONDITION_EXPRESSION = "conditionExpression";
	
	private static Logger logger = Logger.getLogger(ExclusiveGateway.class.getSimpleName());
	
	private String expression;
	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public SequenceFlow getIfSeq() {
		return ifSeq;
	}

	public void setIfSeq(SequenceFlow ifSeq) {
		this.ifSeq = ifSeq;
	}

	public SequenceFlow getElseSeq() {
		return elseSeq;
	}

	public void setElseSeq(SequenceFlow elseSeq) {
		this.elseSeq = elseSeq;
	}

	private SequenceFlow ifSeq;
	private SequenceFlow elseSeq;
	
	public static ExclusiveGateway fromXML(Element element){
		ExclusiveGateway exclusiveGateway = new ExclusiveGateway();
		exclusiveGateway.name = element.getAttributeValue("NAME");
		List<?> children = element.getChildren();
		Iterator<?> iter = children.iterator();
		while(iter.hasNext()){
			Element sFElement = (Element)iter.next();
			SequenceFlow sF = SequenceFlow.fromXML(sFElement);
			Boolean hasChild = Parser.hasChild(sFElement, CONDITION_EXPRESSION);
			if(hasChild){
				Element exprElement = Parser.getChild(sFElement, CONDITION_EXPRESSION);
				if(exprElement != null){
					exclusiveGateway.expression = exprElement.getContent(0).getValue();
				}
				exclusiveGateway.ifSeq = sF;
			}
			else{
				exclusiveGateway.elseSeq = sF;
			}
		}
		return exclusiveGateway;
	}
}
