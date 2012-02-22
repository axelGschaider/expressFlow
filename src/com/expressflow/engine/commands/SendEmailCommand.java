package com.expressflow.engine.commands;

/*
 * Copyright (c) 2011 Martin Vasko, Ph.D.
 * 
 * licensing@expressflow.com http://expressflow.com/license
 */

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Logger;

import com.expressflow.engine.interfaces.ICommand;
import com.expressflow.model.Activity;
import com.expressflow.model.SendEmail;
import com.expressflow.model.Variable;
import com.expressflow.utils.NameUtil;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailCommand implements ICommand {

	private static final Logger log = Logger.getLogger(SendEmailCommand.class
			.getSimpleName());

	// private ModelSingleton modelSingleton;
	
	@Override
	public void execute(String scopeID, Activity activity) {
		System.err.println("Executing Send Email command.");
		// modelSingleton = ModelSingleton.getInstance();
		SendEmail send = (SendEmail) activity;
		MemcacheService memcache = MemcacheServiceFactory.getMemcacheService(scopeID);

		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

			try {
				Message msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress(send.getFrom(),send.getNickname()));
				if(send.getResponse().length() > 0)
					msg.setReplyTo(new Address[] { new InternetAddress(send.getResponse()) });
				
				// Recipient entered manually
				if(!send.getTo().startsWith("$"))
					msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
						send.getTo(), send.getTo()));
				// Linked to a Variable
				else{
					Variable toVar = (Variable)memcache.get(NameUtil.normalizeVariableName(send.getTo()));
					msg.addRecipient(Message.RecipientType.TO, new InternetAddress((String)toVar.getValue()));
					// msg.addRecipient(Message.RecipientType.TO, new InternetAddress((String)modelSingleton.variables.get(NameUtil.normalizeVariableName(send.getTo())).getValue()));
				}
				
				msg.setSubject(send.getSubject());
				
				// Message Text entered manually
				if(!send.getBody().getValue().startsWith("$"))
					msg.setText(send.getBody().getValue());
				// Linked to a Variable
				else{
					Variable sendVar = (Variable)memcache.get(NameUtil.normalizeVariableName(send.getBody().getValue()));
					msg.setText((String)sendVar.getValue());
					// msg.setText((String)modelSingleton.variables.get(NameUtil.normalizeVariableName(send.getBody().getValue())).getValue());
				}
				Transport.send(msg);

			} catch (AddressException e) {
				e.printStackTrace(System.err);
			} catch (MessagingException e) {
				e.printStackTrace(System.err);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace(System.err);
			}
		
	}

}
