package com.expressflow.test.security;

import java.security.SignatureException;

import net.oauth.jsontoken.crypto.AbstractSigner;
import net.oauth.jsontoken.crypto.SignatureAlgorithm;
import net.oauth.signatures.SignedJsonAssertionToken;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.appidentity.AppIdentityService;
import com.google.appengine.api.appidentity.AppIdentityServiceFactory;
import com.google.appengine.api.appidentity.AppIdentityService.SigningResult;
import com.google.appengine.tools.development.testing.LocalAppIdentityServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class AppIdentityTest {
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalAppIdentityServiceTestConfig());
	
	public static String URL_SHORTENER_SCOPE = "https://www.googleapis.com/auth/urlshortener";
	
	public static String GOOGLE_TOKEN_ENDPOINT = "https://accounts.google.com/o/oauth2/token";

	@Before
	public void setUp() {
		helper.setUp();
	}
	
	@After
    public void tearDown() {
        helper.tearDown();
    }
	
	@Test
	public void assertJsonTokenForScopeIsNotNull(){
		try{
			String jsonTokenForScope = createJsonTokenForScope(URL_SHORTENER_SCOPE);
			Assert.assertNotNull(jsonTokenForScope);
		}
		catch(Exception e){
			Assert.fail(e.getMessage());
		}
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
