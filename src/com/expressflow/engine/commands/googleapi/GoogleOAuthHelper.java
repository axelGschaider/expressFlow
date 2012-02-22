package com.expressflow.engine.commands.googleapi;

import java.io.IOException;
import java.net.URL;
import java.security.SignatureException;

import net.oauth.signatures.SignedJsonAssertionToken;

import com.expressflow.engine.commands.googleapi.CreateShortUrlCommand.AppEngineSigner;
import com.google.appengine.api.appidentity.AppIdentityService;
import com.google.appengine.api.appidentity.AppIdentityServiceFactory;
import com.google.appengine.api.urlfetch.FetchOptions;
import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.google.gson.JsonParser;

public class GoogleOAuthHelper {
	
	private String scope;
	public static String tokenEndpoint = "https://accounts.google.com/o/oauth2/token";

	public GoogleOAuthHelper(String scope, String tokenEndpoint){
		this.scope = scope;
		this.tokenEndpoint = tokenEndpoint;
	}
	
	public String getGsToken() throws IOException {
	    try {
	      String jwtString = "";
	      jwtString = createJsonTokenForScope(this.scope);
	      String token = this.doTokenExchange(jwtString);
	      return token;
	    } catch (SignatureException e1) {
	      e1.printStackTrace(System.err);
	    }
	    throw new IOException();
	}
	
	private String doTokenExchange(String jwt) throws IOException {
	    URLFetchService service = URLFetchServiceFactory.getURLFetchService();
	    URL url = new URL(this.tokenEndpoint);
	    HTTPRequest request =
	        new HTTPRequest(url, HTTPMethod.POST, FetchOptions.Builder.doNotFollowRedirects()
	            .setDeadline(10.0));
	    HTTPHeader header = new HTTPHeader("Content-Type", "application/x-www-form-urlencoded");
	    request.setHeader(header);

	    String payload = "grant_type=assertion&assertion_type=";
	    payload += SignedJsonAssertionToken.GRANT_TYPE_VALUE;
	    payload += "&assertion=";
	    payload += jwt;
	    request.setPayload(payload.getBytes());
	    HTTPResponse response = service.fetch(request);
	    JsonParser parser = new JsonParser();
	    String token =
	        parser.parse(new String(response.getContent())).getAsJsonObject().get("access_token")
	            .getAsString();
	    return token;
	  }
	
	private String createJsonTokenForScope(String scope) throws SignatureException {
		AppIdentityService appIdentity = AppIdentityServiceFactory.getAppIdentityService();
		String serviceAccountName = appIdentity.getServiceAccountName();
	    AppEngineSigner signer = new AppEngineSigner(serviceAccountName, "");
	    SignedJsonAssertionToken jwt = new SignedJsonAssertionToken(signer);
	    jwt.setAudience(this.tokenEndpoint);
	    jwt.setScope(scope);
	    jwt.setNonce("");
	    return jwt.serializeAndSign();
	}
}
