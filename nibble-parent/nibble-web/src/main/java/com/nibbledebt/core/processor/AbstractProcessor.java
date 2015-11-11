/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.processor;

import java.util.Date;

import org.springframework.security.core.context.SecurityContextHolder;

import com.nibbledebt.common.security.MemberDetails;
import com.nibbledebt.core.data.model.AbstractModel;

/**
 * @author ralam1
 *
 */
public class AbstractProcessor {
	
	protected void setCreated(AbstractModel model, String username){
		model.setCreatedTs(new Date());
		model.setCreatedUser(username);
	}
	
	protected void setUpdated(AbstractModel model, String username){
		model.setUpdatedTs(new Date());
		model.setUpdatedUser(username);
	}
}