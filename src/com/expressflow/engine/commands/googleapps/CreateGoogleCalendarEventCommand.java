package com.expressflow.engine.commands.googleapps;

import java.net.URL;

import com.expressflow.engine.interfaces.ICommand;
import com.expressflow.model.Activity;
import com.expressflow.model.googleapps.GoogleCalendarActivity;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gdata.client.authn.oauth.GoogleOAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthHmacSha1Signer;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.calendar.*;
import com.google.gdata.data.extensions.When;

public class CreateGoogleCalendarEventCommand implements ICommand {

	// Create a CalenderService and authenticate
	private CalendarService service = null;
	
	@Override
	public void execute(String scopeID, Activity activity) {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (user != null) {
			GoogleCalendarActivity gta = (GoogleCalendarActivity)activity;
		   
			try{
				service = new CalendarService("expressFlow-v1");
				MemcacheService cache = MemcacheServiceFactory.getMemcacheService(scopeID);
				// Use our newly built oauthParameters
				service.setOAuthCredentials((GoogleOAuthParameters)cache.get(user.getEmail()),
						new OAuthHmacSha1Signer());
				
				// Create a CalendarEntry
				CalendarEventEntry myEntry = new CalendarEventEntry();

				myEntry.setTitle(new PlainTextConstruct(gta.getTitle()));
				myEntry.setContent(new PlainTextConstruct(gta.getContent()));

				DateTime startTime = DateTime.parseDateTime(gta.getStartTime());
				DateTime endTime = DateTime.parseDateTime(gta.getEndTime());
				When eventTimes = new When();
				eventTimes.setStartTime(startTime);
				eventTimes.setEndTime(endTime);
				myEntry.addTime(eventTimes);
				
				// Send the request and receive the response:
				CalendarEventEntry insertedEntry = service.insert(new URL(gta.getLink()), myEntry);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
