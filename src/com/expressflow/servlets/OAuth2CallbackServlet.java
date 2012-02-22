package com.expressflow.servlets;

/*
 * Copyright (c) 2011 Martin Vasko, Ph.D.
 * 
 * licensing@expressflow.com http://expressflow.com/license
 */

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.expressflow.security.KeyHolder;
import com.expressflow.singleton.ProcessSingleton;
import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import com.google.gdata.client.authn.oauth.GoogleOAuthHelper;
import com.google.gdata.client.authn.oauth.GoogleOAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthHmacSha1Signer;

import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessProtectedResource;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessTokenRequest.GoogleAuthorizationCodeGrant;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAuthorizationRequestUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;

public class OAuth2CallbackServlet extends HttpServlet {
	
	private static final long serialVersionUID = -6848783656882675006L;
	private static final Logger log = Logger.getLogger(OAuth2CallbackServlet.class
			.getName());
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException {
		
		// Create an instance of GoogleOAuthParameters
        GoogleOAuthParameters oauthParameters = new GoogleOAuthParameters();
        oauthParameters.setOAuthConsumerKey(KeyHolder.GOOGLE_API_CLIENT_ID);
        oauthParameters.setOAuthConsumerSecret(KeyHolder.GOOGLE_API_CLIENT_SECRET);

        GoogleOAuthHelper oauthHelper = new GoogleOAuthHelper(
          new OAuthHmacSha1Signer());

        // Remember the token secret that we stashed? Let's get it back
        // now. We need to add it to oauthParameters
        String oauthTokenSecret = (String) request.getSession().getAttribute(
          "oauthTokenSecret");
        oauthParameters.setOAuthTokenSecret(oauthTokenSecret);

        // The query string should contain the oauth token, so we can just
        // pass the query string to our helper object to correctly
        // parse and add the parameters to our instance of oauthParameters
        oauthHelper.getOAuthParametersFromCallback(request.getQueryString(),
          oauthParameters);
        
        try {

            // Now that we have all the OAuth parameters we need, we can
            // generate an access token and access token secret. These
            // are the values we want to keep around, as they are 
            // valid for all API calls in the future until a user revokes
            // our access.
            String accessToken = oauthHelper.getAccessToken(oauthParameters);
            String accessTokenSecret = oauthParameters.getOAuthTokenSecret();

            // In a real application, we want to redirect the user to a new
            // servlet that makes API calls. For the safe of clarity and simplicity,
            // we'll just reuse this servlet for making API calls.
            oauthParameters = new GoogleOAuthParameters();
            oauthParameters.setOAuthConsumerKey(KeyHolder.GOOGLE_API_CLIENT_ID);
            oauthParameters.setOAuthConsumerSecret(KeyHolder.GOOGLE_API_CLIENT_SECRET);

            // This is interesting: we set the OAuth token and the token secret
            // to the values extracted by oauthHelper earlier. These values are
            // already in scope in this example code, but they can be populated
            // from reading from the datastore or some other persistence mechanism.
            oauthParameters.setOAuthToken(accessToken);
            oauthParameters.setOAuthTokenSecret(accessTokenSecret);
            
    		UserService userService = UserServiceFactory.getUserService();
    		User user = userService.getCurrentUser();
    		
//    		MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
//    		syncCache.put(user.getEmail(), oauthParameters);
            ProcessSingleton.getInstance().setOAuthParameters(user.getEmail(), oauthParameters);
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
		
//		UserService userService = UserServiceFactory.getUserService();
//		User user = userService.getCurrentUser();
//		ProcessSingleton.getInstance().setOauthtoken(user.getEmail(), request.getParameter("oauth_token"));
//		String oauthTokenSecret = (String) request.getSession().getAttribute(
//        "oauth_token_secret");
//		String oauth_nounce = (String)request.getSession().getAttribute("oauth_nounce");
//		String oauth_signature_method = (String)request.getSession().getAttribute("oauth_signature_method");
//		String oauth_timestamp = (String)request.getSession().getAttribute("oauth_timestamp");
//		String oauth_verifier = (String)request.getSession().getAttribute("oauth_verifier");
//		String oauth_version = (String)request.getSession().getAttribute("oauth_version");
//		ProcessSingleton.getInstance().setQueryString(user.getEmail(), request.getQueryString());
//		ProcessSingleton.getInstance().setOauthtokenSecret(user.getEmail(), oauthTokenSecret);
//		ProcessSingleton.getInstance().set_oauth_nounce(user.getEmail(), oauth_nounce);
//		ProcessSingleton.getInstance().set_oauth_signature_method(user.getEmail(), oauth_signature_method);
//		ProcessSingleton.getInstance().set_oauth_timestamp(user.getEmail(), oauth_timestamp);
//		ProcessSingleton.getInstance().set_oauth_verifier(user.getEmail(), oauth_verifier);
//		ProcessSingleton.getInstance().set_oauth_version(user.getEmail(), oauth_version);
		response.sendRedirect("/designer/Designer");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException {
		log.info("[OAUTH2CALLBACK]: POST");
	}
}
