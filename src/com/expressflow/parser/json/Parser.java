package com.expressflow.parser.json;

/*
 * Copyright (c) 2011 Martin Vasko, Ph.D.
 * 
 * licensing@expressflow.com http://expressflow.com/license
 */

import java.io.IOException;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

import com.expressflow.model.Activity;

public class Parser {

	public Activity read(JsonParser jp) throws IOException {
		if (jp.nextToken() != JsonToken.START_OBJECT) {
			throw new IOException("Expected data to start with an Object");
		}
		Activity result = new Activity();
		// Iterate over object fields:
		while (jp.nextToken() != JsonToken.END_OBJECT) {
			String fieldName = jp.getCurrentName();
			// Let's move to value
			jp.nextToken();
			if (fieldName.equals("name")) {
				result.setName(jp.getText());
			} else if (fieldName.equals("ancestor")) {
				result.setAncestor(jp.getLongValue());
			} else if (fieldName.equals("predecessor")) {
				result.setPredecessor(jp.getLongValue());
			} else { // ignore, or signal error?
				throw new IOException("Unrecognized field '" + fieldName + "'");
			}
		}
		jp.close(); // important to close both parser and underlying File reader
		return result;
	}
}
