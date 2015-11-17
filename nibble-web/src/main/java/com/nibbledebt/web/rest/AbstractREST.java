/**
 * 
 */
package com.nibbledebt.web.rest;

import org.springframework.security.core.context.SecurityContextHolder;

import com.nibbledebt.common.security.MemberDetails;

/**
 * @author ralam
 *
 */
public abstract class AbstractREST {
	protected String getCurrentUser(){
		return ((MemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
	}
}
