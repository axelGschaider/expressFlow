/*
 * Copyright (c) 2011 Martin Vasko, Ph.D.
 * 
 * licensing@expressflow.com 
 * http://expressflow.com/license
 */

package com.expressflow.googleapps;

public class OAuthSingleton {
	public String token;
	
	private static OAuthSingleton instance = new OAuthSingleton();
	
	private OAuthSingleton(){}
	
	public static OAuthSingleton getInstance(){
		return instance;
	}
}
