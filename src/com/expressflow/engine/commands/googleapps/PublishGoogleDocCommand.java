package com.expressflow.engine.commands.googleapps;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.mortbay.log.Log;

import com.expressflow.engine.interfaces.ICommand;
import com.expressflow.googleapps.documentslist.GoogleServicesSingleton;
import com.expressflow.model.Activity;
import com.expressflow.model.googleapps.GoogleDocsActivity;
import com.expressflow.singleton.ProcessSingleton;
import com.google.appengine.api.datastore.Link;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gdata.client.DocumentQuery;
import com.google.gdata.client.authn.oauth.GoogleOAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthHmacSha1Signer;
import com.google.gdata.client.docs.DocsService;
import com.google.gdata.data.TextConstruct;
import com.google.gdata.data.acl.AclEntry;
import com.google.gdata.data.acl.AclRole;
import com.google.gdata.data.acl.AclScope;
import com.google.gdata.data.docs.DocumentListEntry;
import com.google.gdata.util.ServiceException;

public class PublishGoogleDocCommand implements ICommand {

	private DocsService client = null;

	public void execute(String scopeID, Activity activity) {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (user != null) {

			GoogleDocsActivity gDocA = (GoogleDocsActivity) activity;

			GoogleServicesSingleton docEntries = GoogleServicesSingleton
					.getInstance();

			DocumentListEntry docEntry = docEntries.get(gDocA.getLink());

			try {
				// Create an instance of the DocsService to make API calls
				client = new DocsService("expressFlow-v1");

				// Use our newly built oauthParameters
				MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
				client.setOAuthCredentials((GoogleOAuthParameters)cache.get(user.getEmail()),
						new OAuthHmacSha1Signer());

				AclRole role = new AclRole("reader");
				AclScope scope = new AclScope(AclScope.Type.USER,
						gDocA.getEmail());
				AclEntry aclEntry = addAclRole(role, scope, docEntry);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private AclEntry addAclRole(AclRole role, AclScope scope,
			DocumentListEntry entry) throws IOException, MalformedURLException,
			ServiceException {
		AclEntry aclEntry = new AclEntry();
		aclEntry.setRole(role);
		aclEntry.setScope(scope);

		return client.insert(new URL(entry.getAclFeedLink().getHref()),
				aclEntry);
	}

}
