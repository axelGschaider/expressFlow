package com.expressflow.engine.commands;

/*
 * Copyright (c) 2011 Martin Vasko, Ph.D.
 * 
 * licensing@expressflow.com http://expressflow.com/license
 */

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.expressflow.engine.interfaces.ICommand;
import com.expressflow.model.Activity;
import com.expressflow.model.SendEmail;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class SendReminderCommand implements ICommand {

	private static final Logger log = Logger.getLogger(SendReminderCommand.class
			.getSimpleName());
	
	@Override
	public void execute(String scopeID, Activity activity) {
		SendEmail send = (SendEmail) activity;

		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		UserService userService = UserServiceFactory.getUserService();
		User currentUser = userService.getCurrentUser();

		if (currentUser != null) {
			try {
				Message msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress(currentUser.getEmail(), currentUser.getNickname()));
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
						send.getTo(), send.getTo()));
				msg.setSubject(send.getSubject());
				msg.setText(send.getBody().getValue());
				Transport.send(msg);

			} catch (AddressException e) {
				log.warning(e.getMessage());
			} catch (MessagingException e) {
				log.warning(e.getMessage());
			} catch (UnsupportedEncodingException e) {
				log.warning(e.getMessage());
			}
		}
	}

}
