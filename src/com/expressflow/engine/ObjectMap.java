package com.expressflow.engine;

/*
 * Copyright (c) 2011 Martin Vasko, Ph.D.
 * 
 * licensing@expressflow.com http://expressflow.com/license
 */

import java.util.HashMap;

import org.jdom.Element;

import com.expressflow.model.Activity;
import com.expressflow.model.ExclusiveGateway;
import com.expressflow.model.MobileActivity;
import com.expressflow.model.SendEmail;
import com.expressflow.model.SendSMS;
import com.expressflow.model.SequenceFlow;
import com.expressflow.model.StartEvent;
import com.expressflow.model.Variable;

public class ObjectMap {
	
	public static final int PROCESS = 10;
	public static final int SEQUENCEFLOW = 11;
	
	public static final int START_EVENT = 2;
	public static final int MOBILEFORM = 3;
	public static final int VARIABLE = 4;
	public static final int EXCLUSIVE_GATEWAY = 5;
	
	public static final int SEND_SMS = 101;
	public static final int SEND_EMAIL = 102;
	
	private static HashMap<Class, Integer> map = 
		new HashMap<Class, Integer>(){
			private static final long serialVersionUID = 1L;

			{
				put(SequenceFlow.class, SEQUENCEFLOW);
				put(StartEvent.class, START_EVENT);
				put(MobileActivity.class, MOBILEFORM);
				put(Variable.class, VARIABLE);
				put(ExclusiveGateway.class, EXCLUSIVE_GATEWAY);
				put(SendSMS.class, SEND_SMS);
				put(SendEmail.class, SEND_EMAIL);
			}
		};
		
		public static HashMap<Class, Integer> getMap(){
			return map;
		}
		
		public static int resolveActivity(Activity activity){
			int result = 0;
			switch(map.get(activity.getClass())){
			case EXCLUSIVE_GATEWAY:
				result = EXCLUSIVE_GATEWAY;
				break;
			case MOBILEFORM:
				result = MOBILEFORM;
				break;
			case PROCESS:
				result = PROCESS;
				break;
			case SEND_EMAIL:
				result = SEND_EMAIL;
				break;
			case SEND_SMS:
				result = SEND_SMS;
				break;
			case SEQUENCEFLOW:
				result = SEQUENCEFLOW;
				break;
			case START_EVENT:
				result = START_EVENT;
				break;
			case VARIABLE:
				result = VARIABLE;
				break;
			default:
				result = -1;
				break;
			}
			return result;
		}
}
