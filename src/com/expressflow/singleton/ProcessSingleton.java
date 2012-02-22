package com.expressflow.singleton;

/*
Copyright (c) 2011 Martin Vasko, Ph.D.

licensing@expressflow.com
http://expressflow.com/license
*/

import java.util.HashMap;

import org.jdom.Document;

import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.gdata.client.authn.oauth.GoogleOAuthParameters;

public class ProcessSingleton {
	private static ProcessSingleton instance = null;
	
	private Document document;
	// TODO: Add Multithread Support!
	private HashMap<String, String> oauthtoken = new HashMap<String, String>();
	private HashMap<String, String> oauthtokenSecret = new HashMap<String, String>();
	private HashMap<String, String> queryStrings = new HashMap<String, String>();
	private HashMap<String, String> oauth_nounces = new HashMap<String, String>();
	private HashMap<String, String> oauth_signature_method = new HashMap<String, String>();
	private HashMap<String, String> oauth_timestamps = new HashMap<String, String>();
	private HashMap<String, String> oauth_verifiers = new HashMap<String, String>();
	private HashMap<String, String> oauth_versions = new HashMap<String, String>();
	
	private HashMap<String, GoogleOAuthParameters> oauthParameters = new HashMap<String, GoogleOAuthParameters>();
	
	public GoogleOAuthParameters getOAuthParameters(String email){
		return oauthParameters.get(email);
	}
	
	public void invalidateOAuthParameters(String email){
		oauthParameters.remove(email);
	}
	
	public void setOAuthParameters(String email, GoogleOAuthParameters parameters){
		oauthParameters.put(email, parameters);
	}
	
	public String get_oauth_nounce(String email){
		return oauth_nounces.get(email);
	}
	
	public String get_oauth_signature_method(String email){
		return oauth_signature_method.get(email);
	}
	
	public String get_oauth_timestamp(String email){
		return oauth_timestamps.get(email);
	}
	
	public String get_oauth_verifier(String email){
		return oauth_verifiers.get(email);
	}
	
	public String get_oauth_version(String email){
		return oauth_versions.get(email);
	}
	
	public String getQueryString(String email){
		return queryStrings.get(email);
	}
	
	public void set_oauth_nounce(String email, String value){
		oauth_nounces.put(email, value);
	}
	
	public void set_oauth_signature_method(String email, String value){
		oauth_signature_method.put(email, value);
	}
	
	public void set_oauth_timestamp(String email, String value){
		oauth_timestamps.put(email, value);
	}
	
	public void set_oauth_verifier(String email, String value){
		oauth_verifiers.put(email, value);
	}
	
	public void set_oauth_version(String email, String value){
		oauth_versions.put(email, value);
	}
	
	public void setQueryString(String email, String queryString){
		this.queryStrings.put(email, queryString);
	}
	
	public String getOauthtokenSecret(String email){
		return oauthtokenSecret.get(email);
	}
	
	public void setOauthtokenSecret(String email, String oauthtokenSecret){
		this.oauthtokenSecret.put(email, oauthtokenSecret);
	}
	
	public String getOauthtoken(String email) {
		return oauthtoken.get(email);
	}

	public void setOauthtoken(String email, String oauthtoken) {
		this.oauthtoken.put(email, oauthtoken);
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	private ProcessSingleton(){	}
	
	public static ProcessSingleton getInstance(){
		if (instance == null) {
            instance = new ProcessSingleton();
        }
        return instance;
	}
}
