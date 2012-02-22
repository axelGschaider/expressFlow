package com.expressflow.model.aws;

import java.util.HashMap;

import org.jdom.Element;

import com.expressflow.model.Activity;

public class AmazonS3Activity extends Activity{
	private static final String ACTION = "action";
	
	public static final int PUBLISH = 1;
	public static final int DELETE_BUCKET = 2;
	public static final int DELETE_OBJECT = 3;
	
	private String bucketName;
	private String action;
	
	public static int get(String name){
		return map.get(name);
	}
	
	public String getAction() {
		return action.toUpperCase();
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	private static HashMap<String, Integer> map = 
			new HashMap<String, Integer>(){
				private static final long serialVersionUID = 1L;

				{
					put("PUBLISH", 1);
					put("DELETE_BUCKET", 2);
					put("DELETE_OBJECT", 3);
				}
		};
	
	public static AmazonS3Activity fromXML(Element element){
		AmazonS3Activity activity = new AmazonS3Activity();
		activity.setAction(element.getAttributeValue(ACTION));
		return activity;
	}
}
