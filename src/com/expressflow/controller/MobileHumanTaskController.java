package com.expressflow.controller;

import java.net.URL;
import java.util.Date;

import javax.jdo.PersistenceManager;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.expressflow.Constants;
import com.expressflow.datastore.PMF;
import com.expressflow.jdo.Task;
import com.expressflow.security.KeyHolder;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.urlfetch.FetchOptions;
import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@Controller
public class MobileHumanTaskController {

	@RequestMapping(value = "/task/{id}", method=RequestMethod.POST)
	public Task saveHumanTask(@RequestBody Task task) {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (userService.isUserLoggedIn()) {
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode jsonNode = mapper.createObjectNode();
			jsonNode.put("namespace", "expressflow.com");
			jsonNode.put("publicAppKey", KeyHolder.EXPRESSTASKS_KEY);
			jsonNode.put("privateAppKey", KeyHolder.EXPRESSTASKS_SECRET);
			jsonNode.put("owner", user.getEmail());
			jsonNode.put("creator", user.getEmail());
			jsonNode.put("recipient", task.getRecipient());
			jsonNode.put("description", task.getDescription());
			jsonNode.put("name", task.getName());
			jsonNode.put("incomingProcessId", task.getProcessId());
			jsonNode.put("outgoingProcessId", task.getProcessId());
			jsonNode.put("visibility", task.getVisibility());
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				URLFetchService fetchService = URLFetchServiceFactory
						.getURLFetchService();
				URL url = new URL(Constants.getHost());
				HTTPRequest request = new HTTPRequest(
						url, HTTPMethod.POST, FetchOptions.Builder
						.withDeadline(3600));
				request.setHeader(new HTTPHeader("Content-Type", "application/json"));
				request.setPayload(jsonNode.toString().getBytes());
				HTTPResponse response = fetchService.fetch(request);
				String responseString = new String(response.getContent(), "utf-8");
				JsonNode taskNode = mapper.readValue(responseString, JsonNode.class);
				JsonNode urlNode = taskNode.findValue("url");
				JsonNode idNode = taskNode.findValue("id");
				if(urlNode != null || idNode != null){
					task.setUrl(urlNode.getTextValue());
					task.setId(idNode.getTextValue());
					task.setPublicAppKey(KeyHolder.EXPRESSTASKS_KEY);
					task.setPrivateAppKey(KeyHolder.EXPRESSTASKS_SECRET);
					task.setNamespace("expressflow.com");
					pm.makePersistent(task);
				}
				return task;
			} catch (Exception e) {
				e.printStackTrace(System.err);
				pm.close();
			}
			finally{
				pm.close();
			}
		}
		return null;
	}
}
