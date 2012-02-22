package com.expressflow.test.command;

import org.junit.Test;

import com.expressflow.engine.commands.googleapi.CreateShortUrlCommand;
import com.expressflow.model.Variable;
import com.expressflow.model.googleapi.GoogleURLShortenerActivity;

public class CreateShortUrlCommandTest {
	
	@Test
	public void testExecute() {
		CreateShortUrlCommand command = new CreateShortUrlCommand();
		GoogleURLShortenerActivity activity = new GoogleURLShortenerActivity();
		activity.setLongUrl("http://expressflow.com");
		Variable outVar = new Variable();
		outVar.setName("outVar");
		outVar.setValue("");
		command.execute("someFakeID", activity);
	}

}
