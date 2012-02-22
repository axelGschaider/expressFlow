package com.expressflow.datastore;

/*
 * Copyright (c) 2011 Martin Vasko, Ph.D.
 * 
 * licensing@expressflow.com http://expressflow.com/license
 */

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

public class PMF {
	private static final PersistenceManagerFactory pmfInstance = JDOHelper
			.getPersistenceManagerFactory("transactions-optional");

	private PMF() {
	}

	public static PersistenceManager getManager() {
		return pmfInstance.getPersistenceManager();
	}

	public static PersistenceManagerFactory get() {
		return pmfInstance;
	}
}
