/*
 * Copyright (c) 2011 Martin Vasko, Ph.D.
 * 
 * licensing@expressflow.com 
 * http://expressflow.com/license
 */

package com.expressflow.googleapps;

import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.api.gwt.oauth2.client.Callback;

public class OAuth2Helper {
	private String AUTH_URL = "https://accounts.google.com/o/oauth2/auth";
	private String CLIENT_ID = "355386028484.apps.googleusercontent.com"; // available from the APIs console
	private AuthRequest req;
	
	public OAuth2Helper(){
		req = new AuthRequest(AUTH_URL, CLIENT_ID);
		Auth.get().login(req, new Callback<String, Throwable>() {
			
			@Override
			public void onSuccess(String result) {
				OAuthSingleton.getInstance().token = result;
			}
			
			@Override
			public void onFailure(Throwable reason) {
				// Wird ausgeführt, wenn das OAuth Popup vom
				// User mit "DENY" bestätigt wurde
			}
		});
	}
}
