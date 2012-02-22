package com.expressflow.engine.commands.extension;

/*
 * Copyright (c) 2011 Martin Vasko, Ph.D.
 * 
 * licensing@expressflow.com http://expressflow.com/license
 */ 

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import com.expressflow.engine.interfaces.ICommand;
import com.expressflow.model.Activity;
import com.expressflow.model.SendSMS;
import com.expressflow.model.Variable;
import com.expressflow.utils.NameUtil;
import com.google.api.client.util.Base64;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.urlfetch.FetchOptions;
import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;

public class SendSMSCommand implements ICommand {

	public SendSMSCommand() {

	}

	@Override
	public void execute(String scopeID, Activity activity) {
		SendSMS send = (SendSMS) activity;
		MemcacheService memcache = MemcacheServiceFactory.getMemcacheService(scopeID);
		
		try {
        
        // Recipient entered manually
        String to;
		if(!send.getTo().startsWith("$"))
			to = send.getTo();
		else{
			Variable varTo = (Variable)memcache.get(NameUtil.normalizeVariableName(send.getTo()));
			to = (String)varTo.getValue();
		}
		
		// Message entered manually
        String body;
		if(!send.getBody().getValue().startsWith("$"))
			body = send.getBody().getValue();
		else{
			Variable varBody = (Variable)memcache.get(NameUtil.normalizeVariableName(send.getBody().getValue()));
			body = (String)varBody.getValue();
		}
		
		URL url = new URL("https://api.twilio.com/2010-04-01/Accounts/AC2e82e255f3ae055cada2d29519c5c50e/SMS/Messages");
		URLFetchService fetchService = URLFetchServiceFactory
		.getURLFetchService();
		HTTPRequest request = new HTTPRequest(url, HTTPMethod.POST, FetchOptions.Builder.withDeadline(3600));
		
		String message = "From=" + send.getFrom() + "&" 
				+ "To=" + to + "&" 
				+ "Body=" + URLEncoder.encode(body, "UTF-8");
		System.err.println("Sending message: " + message);
		
		String secured = "AC2e82e255f3ae055cada2d29519c5c50e:98ccf42d029301ee6e9f73e74b525535";
		String authorizationString = "Basic " + Base64.encode(secured.getBytes());
		request.addHeader(new HTTPHeader("Authorization:", authorizationString));
		request.setPayload(message.getBytes());
		HTTPResponse httpResponse = fetchService.fetch(request);
		System.err.println(new String(httpResponse.getContent(), "utf-8"));
			
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

	}

}
