package com.expressflow.controller;

import java.io.IOException;
import java.util.Date;

import javax.jdo.PersistenceManager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.expressflow.datastore.PMF;
import com.expressflow.jdo.Timer;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@Controller
public class TimerController {
	@RequestMapping(value = "/timermgmt/{id}", method = RequestMethod.POST)
	public Timer saveTimer(@RequestBody Timer timer) throws IOException{
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (userService.isUserLoggedIn()) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				Date now = new Date();
				timer.setId(KeyFactory.keyToString(KeyFactory.createKey(user.getEmail(), now.getTime())));
				Timer t = pm.makePersistent(timer);
				return t;
			} finally{
				pm.close();
			}
		}
		return null;
	}

}
