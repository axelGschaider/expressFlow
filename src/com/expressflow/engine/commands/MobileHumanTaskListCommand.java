package com.expressflow.engine.commands;

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
import com.expressflow.model.HTState;
import com.expressflow.model.MobileHumanTaskList;
import com.expressflow.utils.Constants;
import com.expressflow.utils.NameUtil;
import com.google.appengine.api.appidentity.AppIdentityService;
import com.google.appengine.api.appidentity.AppIdentityServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class MobileHumanTaskListCommand implements ICommand {

	private static final Logger log = Logger
			.getLogger(MobileHumanTaskListCommand.class.getSimpleName());

	@Override
	public void execute(String scopeID, Activity activity) {
		UserService userService = UserServiceFactory.getUserService();
		User currentUser = userService.getCurrentUser();

		if (currentUser != null) {

			MobileHumanTaskList mhtl = (MobileHumanTaskList) activity;
			// Update the MobileHumanTaskListItem State to display in the
			// tasklist
			mhtl.setState(HTState.IN_PROGRESS);
			mhtl.persist();

			// Send an email to inform the participant
			Properties props = new Properties();
			Session session = Session.getDefaultInstance(props, null);
			try {
				Message msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress(currentUser.getEmail(), currentUser.getNickname()));

				msg.addRecipient(Message.RecipientType.TO,new InternetAddress(mhtl.getWorkerEmail(), mhtl.getWorkerEmail()));

				msg.setSubject("[expressFlow Engine] Mobile Human Task assigned to you");
				
				String message = "Hi there!\n\nYour expertise is needed!\n" + currentUser.getNickname() + " assigned a Human Task to you.\n";
				message += "Just head over to http://" + Constants.cutServiceAccountName() + ".appspot.com/tasklist/ and check what to do next." ;
				msg.setText(message);
	
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
