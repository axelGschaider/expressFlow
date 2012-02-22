package com.expressflow.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.expressflow.datastore.PMF;
import com.expressflow.googleapps.documentslist.GoogleServicesSingleton;
import com.expressflow.jdo.GoogleCalendar;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gdata.client.authn.oauth.GoogleOAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthHmacSha1Signer;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.data.calendar.CalendarFeed;

@Controller
public class GoogleCalendarController {
	private static Logger logger = Logger.getLogger(GoogleCalendarController.class
			.getSimpleName());
	
	@RequestMapping(value = "/googlecalendar/", method = RequestMethod.GET)
	public CalendarEntry[] getAllDocuments() throws IOException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		PersistenceManager pm = PMF.getManager();
		MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
		if (user != null
				&& cache.get(user.getEmail()) != null) {
			
			try {
				 // Create an instance of the DocsService to make API calls
	            CalendarService client = new CalendarService("expressFlow-v1");

	            // Use our newly built oauthParameters
	            client.setOAuthCredentials((GoogleOAuthParameters)cache.get(user.getEmail()), new OAuthHmacSha1Signer());
	            client.setConnectTimeout(3600);

	            URL feedUrl = new URL("https://www.google.com/calendar/feeds/default/owncalendars/full");
	            CalendarFeed resultFeed = client.getFeed(feedUrl, CalendarFeed.class);
	            List<CalendarEntry> result = new ArrayList<CalendarEntry>();
	            for (CalendarEntry entry : resultFeed.getEntries()) {
	            	GoogleServicesSingleton docEntry = GoogleServicesSingleton.getInstance();
	            	docEntry.addCalendar(entry.getId(), entry);
	            	result.add(entry);
	            }
	            return (CalendarEntry[]) result.toArray();
			}catch(Exception e){
				return null;
			}
		} else{
			return null;
		}
	}
	
	private GoogleCalendar mapEntry(CalendarEntry entry){
		GoogleCalendar googleCalendar = new GoogleCalendar();
		String title = entry.getTitle().getPlainText();
		String id = entry.getId();
		String kind = entry.getKind();

		googleCalendar.setId(id);
		googleCalendar.setKind(kind);
		googleCalendar.setTitle(title);
		googleCalendar.setCanEdit(entry.getCanEdit());
		googleCalendar.setEdited(entry.getEdited().toUiString());
		googleCalendar.setEtag(entry.getEtag());
		googleCalendar.setPublished(entry.getPublished().toUiString());
		googleCalendar.setUpdated(entry.getUpdated().toUiString());
	
		return googleCalendar;
	}
}
