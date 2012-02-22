package com.expressflow.utils;

/*
Copyright (c) 2011 Martin Vasko, Ph.D.

licensing@expressflow.com
http://expressflow.com/license
*/

public class NameUtil {
	public static String normalizeVariableName(String name){
		StringBuilder sb = new StringBuilder(name);
		return sb.substring(name.indexOf("$") + 1, name.length());
	}
}
