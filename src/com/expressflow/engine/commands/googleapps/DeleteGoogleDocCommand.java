package com.expressflow.engine.commands.googleapps;

import com.expressflow.engine.interfaces.ICommand;
import com.expressflow.googleapps.documentslist.GoogleServicesSingleton;
import com.expressflow.model.Activity;
import com.expressflow.model.googleapps.GoogleDocsActivity;
import com.expressflow.singleton.ProcessSingleton;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gdata.client.authn.oauth.OAuthHmacSha1Signer;
import com.google.gdata.client.docs.DocsService;
import com.google.gdata.data.docs.DocumentListEntry;

public class DeleteGoogleDocCommand implements ICommand {
	
	private DocsService client = null;

	@Override
	public void execute(String scopeID, Activity activity) {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (user != null) {

			GoogleDocsActivity gDocA = (GoogleDocsActivity) activity;
			
			GoogleServicesSingleton docEntries = GoogleServicesSingleton
					.getInstance();

			DocumentListEntry docEntry = docEntries.get(gDocA.getLink());

			try {
				docEntry.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
