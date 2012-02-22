package com.expressflow.engine.commands;

import java.net.URL;
import java.util.Collection;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import com.expressflow.engine.interfaces.ICommand;
import com.expressflow.model.Activity;
import com.expressflow.model.MobileHumanTask;
import com.expressflow.model.Variable;
import com.expressflow.security.KeyHolder;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.urlfetch.FetchOptions;
import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;

public class MobileHumanTaskCommand implements ICommand {

	@Override
	public void execute(String scopeID, Activity activity) {
		MobileHumanTask mobileHT = (MobileHumanTask)activity;
		
		try{
			URLFetchService fetchService = URLFetchServiceFactory
			.getURLFetchService();
			
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode jsonNode = mapper.createObjectNode();
			jsonNode.put("namespace", "expressflow.com");
			jsonNode.put("publicAppKey", KeyHolder.EXPRESSTASKS_KEY);
			jsonNode.put("privateAppKey", KeyHolder.EXPRESSTASKS_SECRET);
			MemcacheService memcache = MemcacheServiceFactory
			.getMemcacheService(scopeID);
			ArrayNode variables = mapper.createArrayNode();
			for(String varName : mobileHT.getVariableNames()){
				Variable var = (Variable)memcache.get(varName); 
				ObjectNode varNode = mapper.createObjectNode();
				varNode.put("name", var.getName());
				varNode.put("type", var.getType());
				varNode.put("value", var.getValue().toString());
				variables.add(varNode);
			}
			jsonNode.put("Variables", variables);
			
			URL url = new URL(mobileHT.getUrl());
			HTTPRequest request = new HTTPRequest(
					url, HTTPMethod.POST, FetchOptions.Builder
					.withDeadline(3600));
			request.setHeader(new HTTPHeader("Content-Type", "application/json"));
			request.setPayload(jsonNode.toString().getBytes());
			HTTPResponse response = fetchService.fetch(request);
		}
		catch(Exception e){
			e.printStackTrace(System.err);
		}
	}

}
