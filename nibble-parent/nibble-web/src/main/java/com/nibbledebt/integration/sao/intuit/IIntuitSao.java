/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.sao.intuit;

import java.util.List;
import java.util.Map;

import com.nibbledebt.common.error.ServiceException;
import com.nibbledebt.integration.model.cad.Institution;
import com.nibbledebt.integration.model.cad.InstitutionDetail;
import com.nibbledebt.integration.model.cad.LinkResponse;

/**
 * @author ralam
 *
 */
public interface IIntuitSao {
	public List<Institution> getInstitutions() throws ServiceException;

	public InstitutionDetail getInstitution(String institutionId) throws ServiceException;
	
	public LinkResponse discoverAndAddAccounts(String institutionId, Map<String,String> loginCreds, String userId) throws ServiceException ;
}
