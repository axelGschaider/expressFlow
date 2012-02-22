package com.expressflow.controller;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@Controller
public class TaskController {
	@RequestMapping(value = "/task/", method = RequestMethod.GET)
	public JsonNode getTasks() throws IOException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (userService.isUserLoggedIn()) {
			ObjectMapper mapper = new ObjectMapper();
			ArrayNode jsonArray = mapper.createArrayNode();
			return jsonArray;
		}
		return null;
	}
}
