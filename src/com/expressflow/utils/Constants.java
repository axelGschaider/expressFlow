package com.expressflow.utils;

import com.google.appengine.api.appidentity.AppIdentityService;
import com.google.appengine.api.appidentity.AppIdentityServiceFactory;

/*
Copyright (c) 2011 Martin Vasko, Ph.D.

licensing@expressflow.com
http://expressflow.com/license
*/

public class Constants {
	public static String SESSION_EXPIRED = "SESSION-EXPIRED";
	public static Long APPLICATION_ID = new Long("1");
	
	public static String cutServiceAccountName(){
		AppIdentityService appIdentity = AppIdentityServiceFactory.getAppIdentityService();
		String serviceAccountName = appIdentity.getServiceAccountName();
		StringBuilder sb = new StringBuilder(serviceAccountName);
		String host = sb.substring(0, sb.indexOf("@"));
		return host;
	}
}
