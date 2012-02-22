package com.expressflow.engine.interfaces;

/*
 * Copyright (c) 2011 Martin Vasko, Ph.D.
 * 
 * licensing@expressflow.com http://expressflow.com/license
 */

import com.expressflow.model.Activity;

public interface ICommand {
	void execute(String scopeID, Activity activity);
}
