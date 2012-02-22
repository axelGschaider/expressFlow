package com.expressflow.servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.expressflow.singleton.ProcessSingleton;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gdata.client.authn.oauth.GoogleOAuthHelper;
import com.google.gdata.client.authn.oauth.GoogleOAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthHmacSha1Signer;

public class RevokeOAuthAccessServlet extends HttpServlet {
	private static final long serialVersionUID = 2329588626433299341L;

	private static final Logger log = Logger
			.getLogger(RevokeOAuthAccessServlet.class.getName());

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		GoogleOAuthHelper oauthHelper = new GoogleOAuthHelper(
				new OAuthHmacSha1Signer());
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		GoogleOAuthParameters oauthParams = ProcessSingleton.getInstance()
				.getOAuthParameters(user.getEmail());
		try {
			if (ProcessSingleton.getInstance().getOAuthParameters(
					user.getEmail()) != null) {
				ProcessSingleton.getInstance().invalidateOAuthParameters(
						user.getEmail());
				oauthHelper.revokeToken(oauthParams);
			}
			response.sendRedirect("/index.jsp");
		} catch (Exception e) {
			log.log(Level.SEVERE, e.toString());
		}
	}
}
