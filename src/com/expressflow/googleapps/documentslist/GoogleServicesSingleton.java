package com.expressflow.googleapps.documentslist;

import java.util.HashMap;

import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.data.docs.DocumentListEntry;

public class GoogleServicesSingleton {
	private static GoogleServicesSingleton instance = null;
	
	private HashMap<String, DocumentListEntry> documentEntries = new HashMap<String, DocumentListEntry>();
	private HashMap<String, CalendarEntry> calendarEntries = new HashMap<String, CalendarEntry>();
	
	public DocumentListEntry get(String link){
		return documentEntries.get(link);
	}
	
	public void removeEntry(String link){
		documentEntries.remove(link);
	}
	
	public void addEntry(String link, DocumentListEntry entry){
		documentEntries.put(link, entry);
	}
	
	public void addCalendar(String link, CalendarEntry entry){
		calendarEntries.put(link, entry);
	}
	
	public CalendarEntry getCalendarEntry(String link){
		return calendarEntries.get(link);
	}
	
	public void removeCalendarEntry(String link){
		calendarEntries.remove(link);
	}
	
	private GoogleServicesSingleton(){
		
	}
	
	public static GoogleServicesSingleton getInstance(){
		if(instance == null){
			instance = new GoogleServicesSingleton();
		}
		return instance;
	}
}
