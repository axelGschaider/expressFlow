/*
 * Copyright (c) 2011 Martin Vasko, Ph.D.
 * 
 * licensing@expressflow.com 
 * http://expressflow.com/license
 */

package com.expressflow.googleapps.documentslist;

import java.io.IOException;
import java.net.URL;
import java.security.PrivateKey;

import javax.jdo.PersistenceManager;

import org.apache.commons.lang.StringEscapeUtils;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.expressflow.datastore.PMF;
import com.expressflow.security.KeyHelper;
import com.expressflow.singleton.ProcessSingleton;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gdata.client.authn.oauth.GoogleOAuthHelper;
import com.google.gdata.client.authn.oauth.GoogleOAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthException;
import com.google.gdata.client.authn.oauth.OAuthHmacSha1Signer;
import com.google.gdata.client.authn.oauth.OAuthRsaSha1Signer;
import com.google.gdata.client.docs.*;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.docs.*;
import com.google.gdata.data.extensions.*;
import com.google.gdata.util.*;

public class DocumentsListService {

	private DocsService client;

	private GoogleOAuthParameters oauthParameters;

	public DocumentsListService() {
	}

	public String authenticate() {
		String result = "NOK";
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
		if (cache.get(user.getEmail()) != null) {
			result = "OK";
		}
		return result;
	}

	public String showAllDocs() throws Exception, OAuthException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		Element result = new Element("documentlist");
		PersistenceManager pm = PMF.getManager();
		MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
		if (user != null
				&& cache.get(user.getEmail()) != null) {
			
			try {
				
	            // Create an instance of the DocsService to make API calls
	            DocsService client = new DocsService("expressFlow-v1");

	            // Use our newly built oauthParameters
	            client.setOAuthCredentials((GoogleOAuthParameters)cache.get(user.getEmail()), new OAuthHmacSha1Signer());
	            client.setConnectTimeout(3600);

	            URL feedUrl = new URL("https://docs.google.com/feeds/default/private/full");
	            DocumentListFeed resultFeed = client.getFeed(feedUrl,
	                DocumentListFeed.class);
	            for (DocumentListEntry entry : resultFeed.getEntries()) {
	            	GoogleServicesSingleton docEntry = GoogleServicesSingleton.getInstance();
	            	docEntry.addEntry(entry.getDocumentLink().getHref(), entry);
	            	result.addContent(mapElement(entry));
	            }
				
			} catch (Exception e) {
				result.addContent(e.toString());
				e.printStackTrace();
			}
		}
		XMLOutputter serializer = new XMLOutputter();
		serializer.setFormat(Format.getPrettyFormat());
		String resultString = serializer.outputString(result);
		resultString = StringEscapeUtils.unescapeXml(resultString);
		return resultString;
	}

	private Element mapElement(DocumentListEntry entry) {
		Element listEntry = new Element("listentry");
		String title = entry.getTitle().getPlainText();
		String resourceId = entry.getResourceId();
		String docType = entry.getType();

		listEntry.setAttribute("title", title);
		listEntry.setAttribute("resourceId", resourceId);
		listEntry.setAttribute("docType", docType);
		listEntry.setAttribute("link", entry.getDocumentLink().getHref());

		// print the timestamp the document was last viewed
		DateTime lastViewed = entry.getLastViewed();
		if (lastViewed != null) {
			listEntry.setAttribute("lastViewed", lastViewed.toUiString());
		}

		// print who made the last modification
		LastModifiedBy lastModifiedBy = entry.getLastModifiedBy();
		if (lastModifiedBy != null) {
			listEntry.setAttribute("modifiedBy", lastModifiedBy.getName());
			listEntry
					.setAttribute("modifiedByEmail", lastModifiedBy.getEmail());
		}

		// Files such as PDFs take up quota
		if (entry.getQuotaBytesUsed() > 0) {
			listEntry.setAttribute("quotaUsed", entry.getQuotaBytesUsed()
					.toString());
		}

		// print other useful metadata
		listEntry.setAttribute("lastUpdated", entry.getUpdated().toString());
		// Entry is currently viewed
		if (entry.isViewed())
			listEntry.setAttribute("isViewed", "true");
		else
			listEntry.setAttribute("isViewed", "false");
		// Writers can invite
		if (entry.isWritersCanInvite())
			listEntry.setAttribute("writersCanInvite", "true");
		else
			listEntry.setAttribute("writersCanInvite", "false");
		// Entry is hidden
		if (entry.isHidden())
			listEntry.setAttribute("hidden", "true");
		else
			listEntry.setAttribute("hidden", "false");
		// Starred
		if (entry.isStarred())
			listEntry.setAttribute("starred", "true");
		else
			listEntry.setAttribute("starred", "false");
		// Trashed
		if (entry.isTrashed())
			listEntry.setAttribute("trashed", "true");
		else
			listEntry.setAttribute("trashed", "false");

		return listEntry;
	}
}
