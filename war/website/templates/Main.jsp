<%@page import="com.google.gdata.client.authn.oauth.OAuthParameters"%>
<%@page import="com.expressflow.security.KeyHolder"%>
<%@page import="com.expressflow.singleton.ProcessSingleton"%>
<%@page import="com.google.gdata.client.authn.oauth.OAuthException"%>
<%@page import="com.google.gdata.client.docs.DocsService"%>
<%@page import="com.google.gdata.client.authn.oauth.OAuthHmacSha1Signer"%>
<%@page import="com.google.gdata.client.authn.oauth.GoogleOAuthHelper"%>
<%@page import="com.google.gdata.client.authn.oauth.GoogleOAuthParameters"%>
<%@page import="com.google.appengine.api.users.User"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.google.appengine.api.memcache.MemcacheService"%>
<%@page import="com.google.appengine.api.memcache.MemcacheServiceFactory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%
    // Authenticate User
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null) {
    	
    	/* TODO: Set the keys accordingly!
    	 * Details can be found under:
    	 * http://code.google.com/intl/en-US/apis/gdata/docs/auth/oauth.html
    	 */
    	GoogleOAuthParameters oauthParameters = new GoogleOAuthParameters();
		oauthParameters.setOAuthConsumerKey(KeyHolder.GOOGLE_API_CLIENT_ID);
		oauthParameters.setOAuthConsumerSecret(KeyHolder.GOOGLE_API_CLIENT_SECRET);
		oauthParameters.setScope("https://docs.google.com/feeds/ https://www.google.com/calendar/feeds/");
		String protocol=request.getScheme();
		String domain=request.getServerName();
		String port=Integer.toString(request.getServerPort());
		String callbackUrl = protocol+"://"+domain+":"+port; 
		oauthParameters.setOAuthCallback(callbackUrl + "/oauth2callback");

		GoogleOAuthHelper oauthHelper = new GoogleOAuthHelper(
	            new OAuthHmacSha1Signer());
		
		String oauthUrl = null;
		
		try{
			oauthHelper.getUnauthorizedRequestToken(oauthParameters);
			oauthUrl = oauthHelper.createUserAuthorizationUrl(oauthParameters);
			request.getSession().setAttribute("oauthTokenSecret",
	                oauthParameters.getOAuthTokenSecret());
		}catch(OAuthException oae){
		}
%>
<p>Hello, <%= user.getNickname() %>! <br />
<br />
What's next?<br />
> <a href="http://expressflow.com/doc/10-min-fast-track.html" target="_blank">10 minutes Quick start</a><br/>
> <a href="http://wiki.expressflow.com" target="_blank">Flow Wiki</a><br />
> <a href="/designer/Designer">Design my Flows</a> <br /> 
> <a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">Sign out</a> <br />
<br />
<!-- Google OAuth Service START -->
Access to Google Docs<sup><a href="/website/doc/GoogleServiceHelp.jsp" target="_blank">?</a></sup>:<br />
<% if(ProcessSingleton.getInstance().getOAuthParameters(user.getEmail()) != null){ %>
<img src="/images/accept.png" /> Access enabled<br />
> <a href="/oauth2revoke">Revoke Access to Google Docs</a><br />
<% }  else { %>
> <a href="<%= oauthUrl %>">Enable Access to Google Docs</a><br />
<% } %>
<!-- Google OAuth Service END -->
<br />
Mobile Administration
<br />
> <a href="/myprocesses">Your processes</a> <br />
> <a href="/mytasks">Your Tasks</a> 
</p>
<%
    } else {
%>
<p>
> <a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Connect with my Google Account</a>
</p>
<%
    }
%> 