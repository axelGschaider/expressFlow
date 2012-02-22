package com.expressflow.test.command.extension;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.junit.Before;
import org.junit.Test;

import com.expressflow.model.SendSMS;
import com.google.appengine.api.datastore.Text;

public class SendSMSCommandTest {
	
	private SendSMS send;
	
	@Before
	public void tearUp(){
		send = new SendSMS();
		send.setTo("+4369911073383");
		send.setBody(new Text("Test case 123"));
	}
	
	@Test
	public void sendSM(){
		try {
			
			URL url = new URL("https://AC2e82e255f3ae055cada2d29519c5c50e:98ccf42d029301ee6e9f73e74b525535@api.twilio.com/2010-04-01/Accounts/AC2e82e255f3ae055cada2d29519c5c50e/SMS/Messages.json");
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoOutput(true);
	        connection.setRequestProperty("Content-Type", 
	           "application/x-www-form-urlencoded");
	        connection.setRequestMethod("POST");
	        
	        String message = "From=" + URLEncoder.encode(send.getFrom(), "UTF-8") + "&" 
    		+ "To=" + URLEncoder.encode(send.getTo(), "UTF-8") + "&" 
    		+ "Body=" + URLEncoder.encode(send.getBody().getValue(), "UTF-8");

	        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
	        writer.write(message);
	        writer.flush();

	        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
	            System.out.println("Everything sent successfully.");
	        } else {
	            System.out.println(connection.getResponseCode() + ": " + connection.getResponseMessage());
	        }
				
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}
	}
}
