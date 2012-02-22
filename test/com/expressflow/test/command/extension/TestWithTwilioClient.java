package com.expressflow.test.command.extension;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Call;

public class TestWithTwilioClient {

	private Account mainAccount;
	private TwilioRestClient client;

	@Before
	public void tearUp() {
		// Create a rest client
		client = new TwilioRestClient("AC2e82e255f3ae055cada2d29519c5c50e",
				"98ccf42d029301ee6e9f73e74b525535");

		// Get the main account (The one we used to authenticate the client
		mainAccount = client.getAccount();
	}

	@Test
	public void sendSMS() {

		try {

			// Send an sms
			SmsFactory smsFactory = mainAccount.getSmsFactory();
			Map<String, String> smsParams = new HashMap<String, String>();
			smsParams.put("To", "5105551212"); // Replace with a valid phone
												// number
			smsParams.put("From", "(650) 492-6804"); // Replace with a valid
														// phone
														// number in your
														// account
			smsParams.put("Body", "This is a test message!");

			smsFactory.create(smsParams);
		} catch (TwilioRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void makeCall() {
		Call call;
		
		try {
			// Make a call
			CallFactory callFactory = mainAccount.getCallFactory();
			Map<String, String> callParams = new HashMap<String, String>();
			callParams.put("To", "+4369911073383"); // Replace with a valid
													// phone number
			callParams.put("From", "(650) 492-6804"); // Replace with a valid
														// phone
														// number in your
														// account
			callParams.put("Url", "http://demo.twilio.com/welcome/voice/");
			

			call = callFactory.create(callParams);
			
			System.out.println(call.getSid());
			
		} catch (TwilioRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
