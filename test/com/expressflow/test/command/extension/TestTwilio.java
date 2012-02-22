package com.expressflow.test.command.extension;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

import com.google.api.client.util.Base64;

public class TestTwilio {

	@Test
	public void makeCall() {
		try {
			URL url = new URL(
					"https://api.twilio.com/2010-04-01/Accounts/AC2e82e255f3ae055cada2d29519c5c50e/Calls");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			String secured = "AC2e82e255f3ae055cada2d29519c5c50e:98ccf42d029301ee6e9f73e74b525535";
			String authorizationString = "Basic "
					+ Base64.encode(secured.getBytes());
			conn.setRequestProperty("Authorization", authorizationString);

			String message = "From=(650)492-6804&" + "To=+4369911073383&"
					+ "Url=http://demo.twilio.com/welcome/voice/";
			System.err.println("Sending message: " + message);

			// Post message
			OutputStreamWriter wr = new OutputStreamWriter(
					conn.getOutputStream());
			wr.write(message);
			wr.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
