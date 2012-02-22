package com.expressflow.engine.commands.googleapi;

import java.io.IOException;
import java.net.URL;
import java.security.SignatureException;
import java.util.concurrent.Future;

import net.oauth.jsontoken.crypto.AbstractSigner;
import net.oauth.jsontoken.crypto.SignatureAlgorithm;
import net.oauth.signatures.SignedJsonAssertionToken;

import org.json.JSONObject;

import com.expressflow.engine.interfaces.ICommand;
import com.expressflow.model.Activity;
import com.expressflow.model.Variable;
import com.expressflow.model.googleapi.GoogleURLShortenerActivity;
import com.expressflow.security.KeyHolder;
import com.expressflow.utils.NameUtil;

import com.google.appengine.api.appidentity.AppIdentityService;
import com.google.appengine.api.appidentity.AppIdentityServiceFactory;
import com.google.appengine.api.appidentity.AppIdentityService.SigningResult;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.urlfetch.FetchOptions;
import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.gson.JsonParser;

public class CreateShortUrlCommand implements ICommand {

	public static String URL_SHORTENER_SCOPE = "https://www.googleapis.com/auth/urlshortener";
	
	public static String GOOGLE_TOKEN_ENDPOINT = "https://accounts.google.com/o/oauth2/token";
	
	@Override
	public void execute(String scopeID, Activity activity) {
		GoogleURLShortenerActivity gusa = (GoogleURLShortenerActivity)activity;
		
		MemcacheService memcache = MemcacheServiceFactory.getMemcacheService(scopeID);
		
		try {
			String accessToken = getGsToken();
			System.err.println("Got accessToken!");
			
			URLFetchService fetcher = URLFetchServiceFactory.getURLFetchService();
			URL url = new URL("https://www.googleapis.com/urlshortener/v1/url?pp=1&key=" + KeyHolder.GOOGLE_API_K);
			HTTPRequest request = new HTTPRequest(url, HTTPMethod.POST);
			request.addHeader(new HTTPHeader("Content-Type", "application/json"));
			request.addHeader(new HTTPHeader("Authorization", "OAuth " + accessToken));
			JSONObject json = new JSONObject();
			if(!gusa.getLongUrl().startsWith("$"))
	        	json.put("longUrl", gusa.getLongUrl());
	        else{
	        	Variable longUrlVar = (Variable)memcache.get(NameUtil.normalizeVariableName(gusa.getLongUrl()));
	        	json.put("longUrl", (String)longUrlVar.getValue());
	        }
			request.setPayload(json.toString().getBytes());
			Future<HTTPResponse> future = fetcher.fetchAsync(request);
			HTTPResponse response = (HTTPResponse)future.get();
			JSONObject jsonResponse = new JSONObject(new String(response.getContent(), "UTF-8"));
			String shortUrlValue = (String)jsonResponse.get("id");
			gusa.setShortUrl(shortUrlValue);
			Variable shortUrlVar = (Variable)memcache.get("shortUrl");
			shortUrlVar.setValue(shortUrlValue);
			memcache.delete("shortUrl");
			memcache.put("shortUrl", shortUrlVar);
	    } catch (Exception e) {
	        e.printStackTrace(System.err);
	    }
	}
	
	private String getGsToken() throws IOException {
	    try {
	      String jwtString = "";
	      jwtString = createJsonTokenForScope(URL_SHORTENER_SCOPE);
	      String token = this.doTokenExchange(jwtString);
	      return token;
	    } catch (SignatureException e1) {
	      e1.printStackTrace(System.err);
	    }
	    throw new IOException();
	}
	
	private String doTokenExchange(String jwt) throws IOException {
	    URLFetchService service = URLFetchServiceFactory.getURLFetchService();
	    URL url = new URL(GOOGLE_TOKEN_ENDPOINT);
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
	    jwt.setAudience(GOOGLE_TOKEN_ENDPOINT);
	    jwt.setScope(scope);
	    jwt.setNonce("");
	    return jwt.serializeAndSign();
	}
	
	static class AppEngineSigner extends AbstractSigner {

	    protected AppEngineSigner(String issuer, String keyId) {
	      super(issuer, keyId);
	    }

	    @Override
	    public SignatureAlgorithm getSignatureAlgorithm() {
	      return SignatureAlgorithm.RS256;
	    }

	    @Override
	    public byte[] sign(byte[] source) {
	      AppIdentityService service = AppIdentityServiceFactory.getAppIdentityService();
	      SigningResult key = service.signForApp(source);
	      this.setSigningKeyId(key.getKeyName());
	      return key.getSignature();
	    }

	}

}
