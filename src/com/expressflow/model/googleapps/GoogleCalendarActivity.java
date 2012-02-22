package com.expressflow.model.googleapps;

import org.jdom.Element;

import com.expressflow.model.Activity;

public class GoogleCalendarActivity extends Activity {
	
	private static final String LINK = "link";
	private static final String TITLE = "title";
	private static final String CONTENT = "content";
	private static final String STARTTIME = "startTime";
	private static final String ENDTIME = "endTime";
	
	private String link;
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	private String title;
	private String content;
	private String startTime;
	private String endTime;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public static GoogleCalendarActivity fromXML(Element element){
		GoogleCalendarActivity activity = new GoogleCalendarActivity();
		activity.setLink(element.getAttributeValue(LINK));
		activity.setTitle(element.getAttributeValue(TITLE));
		activity.setContent(element.getAttributeValue(CONTENT));
		activity.setStartTime(element.getAttributeValue(STARTTIME));
		activity.setEndTime(element.getAttributeValue(ENDTIME));
		return activity;
	}
}
