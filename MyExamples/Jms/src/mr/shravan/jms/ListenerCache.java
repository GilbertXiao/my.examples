/*
 * Created on May 08, 2008 By Shravan Kumar Talupula
 * Copyright 2008. Manhattan Associates.,
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of 
 * Manhattan Associates. You shall not disclose such Confidential Information 
 * and shall use it only in accordance with the terms of the license agreement
 * you entered into with Manhattan Associates.
 */
package mr.shravan.jms;

import java.util.Hashtable;


public final class ListenerCache {

	private static ListenerCache INSTANCE = new ListenerCache();
	private Hashtable ht = new Hashtable();
	
	protected ListenerCache() {

	}

	public static ListenerCache getInstance() {
		return INSTANCE;
	}

	/**
	 * Adds to cache
	 * 
	 * @param key
	 * @param object
	 */
	public void put(String key, Object object) {

		ht.put(key, object);
	}

	/**
	 * Retrieves from cached list
	 * 
	 * @param key
	 * @return Object
	 */
	public Object get(String key) {
		return (Object) ht.get(key);
	}

	

	/**
	 * Checks whether cache is enabled.
	 * 
	 * @return boolean
	 */
	public boolean isCacheEnabled() {
		return true;
	}

	
	public void put(String ctxFactory,
			String providerURL, String factoryName, String queueName,Object listener) {

		String key = getKey(ctxFactory,
			providerURL, factoryName, queueName);

		put(key, listener);

	}
	public static String getKey(String ctxFactory,
			String providerURL, String factoryName, String queueName)
	{
		String key = ctxFactory+":"+providerURL+":"+factoryName+":"+queueName;
		return key;
	}
}
