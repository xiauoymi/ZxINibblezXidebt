/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.intuit.cad.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.nibbledebt.intuit.cad.core.Context;
import com.nibbledebt.intuit.cad.core.OAuthAuthorizer;
import com.nibbledebt.intuit.cad.exception.AggCatException;

/**
 * @author ralam
 *
 */
public class AggCatServiceFactory {
	private static Map<String, ServiceCacheEntry> cadServiceMap = new HashMap<>();
	
	public static AggCatService getService(String consumerKey, String consumerSecret, String samlId, String userId) throws AggCatException{
		if(cadServiceMap.get(userId) == null) {
			cadServiceMap.put(userId, new ServiceCacheEntry (new AggCatService( new Context( new OAuthAuthorizer(consumerKey, consumerSecret, samlId, userId) ) ),  new Date() ));
		}else{
			if(cadServiceMap.get(userId).getCreated().getTime() < System.currentTimeMillis()-360000){
				cadServiceMap.remove(userId);
				cadServiceMap.put(userId, new ServiceCacheEntry (new AggCatService( new Context( new OAuthAuthorizer(consumerKey, consumerSecret, samlId, userId) ) ),  new Date() ));
			}
		}
		return cadServiceMap.get(userId).getService();
	}
	
	private static class ServiceCacheEntry{
		private AggCatService service;
		private Date created;
		/**
		 * @param userId
		 * @param created
		 */
		public ServiceCacheEntry(AggCatService service, Date created) {
			super();
			this.service = service;
			this.created = created;
		}
		
		/**
		 * @return the service
		 */
		public AggCatService getService() {
			return service;
		}
		/**
		 * @return the created
		 */
		public Date getCreated() {
			return created;
		}
		/**
		 * return a string representation
		 */
		public String toString(){
			return ToStringBuilder.reflectionToString(this);
		}
	}
}
