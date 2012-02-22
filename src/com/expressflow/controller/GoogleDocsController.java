package com.expressflow.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;

import org.jdom.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.expressflow.datastore.PMF;
import com.expressflow.googleapps.documentslist.GoogleServicesSingleton;
import com.expressflow.jdo.GoogleDoc;
import com.expressflow.singleton.ProcessSingleton;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gdata.client.authn.oauth.GoogleOAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthHmacSha1Signer;
import com.google.gdata.client.docs.DocsService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.docs.DocumentListEntry;
import com.google.gdata.data.docs.DocumentListFeed;
import com.google.gdata.data.extensions.LastModifiedBy;

@Controller
public class GoogleDocsController {
	private static Logger logger = Logger.getLogger(GoogleDocsController.class
			.getSimpleName());
	
	@RequestMapping(value = "/googledocs/", method = RequestMethod.GET)
	public List<GoogleDoc> getAllDocuments() throws IOException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
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
	            List<GoogleDoc> result = new ArrayList<GoogleDoc>();
	            for (DocumentListEntry entry : resultFeed.getEntries()) {
	            	GoogleServicesSingleton docEntry = GoogleServicesSingleton.getInstance();
	            	docEntry.addEntry(entry.getDocumentLink().getHref(), entry);
	            	result.add(mapEntry(entry));
	            }
	            return result;
			}catch(Exception e){
				return null;
			}
		} else{
			return null;
		}
	}
	
	private GoogleDoc mapEntry(DocumentListEntry entry){
		GoogleDoc googleDoc = new GoogleDoc();
		String title = entry.getTitle().getPlainText();
		String resourceId = entry.getResourceId();
		String docType = entry.getType();

		googleDoc.setTitle(title);
		googleDoc.setResourceId(resourceId);
		googleDoc.setLink(entry.getDocumentLink().getHref());

		// print the timestamp the document was last viewed
		DateTime lastViewed = entry.getLastViewed();
		if (lastViewed != null) {
			googleDoc.setLastViewed(lastViewed.toUiString());
		}

		// print who made the last modification
		LastModifiedBy lastModifiedBy = entry.getLastModifiedBy();
		if (lastModifiedBy != null) {
			googleDoc.setModifiedBy(lastModifiedBy.getName());
			googleDoc.setModifiedByEmail(lastModifiedBy.getEmail());
		}

		// Files such as PDFs take up quota
		if (entry.getQuotaBytesUsed() > 0) {
			googleDoc.setQuotaUsed(entry.getQuotaBytesUsed());
		}

		// print other useful metadata
		googleDoc.setLastUpdated(entry.getUpdated().toUiString());
		// Entry is currently viewed
		googleDoc.setViewed(entry.isViewed());
		// Writers can invite
		googleDoc.setWritersCanInvite(entry.isWritersCanInvite());
		// Entry is hidden
		googleDoc.setHidden(entry.isHidden());
		// Starred
		googleDoc.setStarred(entry.isStarred());
		// Trashed
		googleDoc.setTrashed(entry.isTrashed());
		return googleDoc;
	}
}
