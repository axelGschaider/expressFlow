package com.expressflow.model;

/*
Copyright (c) 2011 Martin Vasko, Ph.D.

licensing@expressflow.com
http://expressflow.com/license
*/

import java.util.HashMap;
import java.util.Map;

public abstract class StructuredActivity{
	
	protected Map<String, Activity> activities;
	
	public StructuredActivity(){
		activities = new HashMap<String, Activity>();
	}
	
	public void addActivity(Activity activity){
		activities.put(activity.getName(), activity);
	}
	
	public Activity getActivity(int index){
		return activities.get(index);
	}
	
	public Activity getActivity(String name){
		return activities.get(name);
	}
	
	public void removeActivity(int index){
		activities.remove(index);
	}
	
	public void setActivity(String name, Activity activity){
		activities.put(activity.getName(), activity);
	}
	
	public void persist(){
		// Must be overriden
	}
}
