package com.expressflow.model;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

public class MobileHumanTask extends Activity {
	private static final String URL = "url";
	private static final String CREATOR = "creator";
	private static final String RECIPIENT = "recipient";
	private static final String NAME = "name";
	private static final String DESCRIPTION = "description";
	
	private String url;
	private String creator;
	private String recipient;
	private String name;
	private String description;
	private List<String> variableNames;
	
	public MobileHumanTask(){
		this.variableNames = new ArrayList<String>();
	}
	
	public void addVariableName(String variableName){
		this.variableNames.add(variableName);
	}
	
	public String getVariable(int index){
		return this.variableNames.get(index);
	}
	
	public void removeVariable(int index){
		this.variableNames.remove(index);
	}
	
	public List<String> getVariableNames() {
		return variableNames;
	}

	public void setVariableNames(List<String> variableNames) {
		this.variableNames = variableNames;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static MobileHumanTask fromXML(Element element){
		MobileHumanTask mht = new MobileHumanTask();
		mht.setUrl(element.getAttributeValue(URL));
		mht.setCreator(element.getAttributeValue(CREATOR));
		mht.setDescription(element.getAttributeValue(DESCRIPTION));
		mht.setName(element.getAttributeValue(NAME));
		List<Element> children = (List<Element>)element.getChildren();
		if(children.size() > 0){
			for(int i = 0; i < children.size(); i++){
				Element child = children.get(i);
				Variable var = Variable.fromXML(child);
				mht.addVariableName(var.getName());
			}
		}
		return mht;
	}
}
