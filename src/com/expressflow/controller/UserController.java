package com.expressflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.expressflow.Constants;
import com.expressflow.jdo.User;
import com.expressflow.singleton.ProcessSingleton;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.Environment;

@Controller
public class UserController {
	@RequestMapping(value = "/user/init", method = RequestMethod.GET)
	public User initUser() {
		UserService userService = UserServiceFactory.getUserService();
		com.google.appengine.api.users.User user = userService.getCurrentUser();
		if (userService.isUserLoggedIn()) {
			User u = new User();
			u.setNickname(user.getNickname());
			u.setEmail(user.getEmail());
			Environment env = ApiProxy.getCurrentEnvironment();
			u.setAppId(env.getAppId());
			if(ProcessSingleton.getInstance().getOAuthParameters(user.getEmail()) != null)
				u.setGoogleServicesActivated(0);
			else
				u.setGoogleServicesActivated(1);
			return u;
		} else{
			return null;
		}
	}
}
