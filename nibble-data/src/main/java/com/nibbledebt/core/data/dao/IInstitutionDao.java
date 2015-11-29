/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.dao;

import java.util.List;

import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.Institution;

/**
 * @author ralam
 *
 */
public interface IInstitutionDao extends IDao<Institution> {

	Institution findByName(String instName) throws RepositoryException;
	List<Institution> findByType(String type) throws RepositoryException;
	Institution find(String instName, String externalId)  throws RepositoryException;
	Institution findBySupportedInstitution(Long supportedInstitutionId) throws RepositoryException;
	List<Institution> findByTestModeSupport(String type) throws RepositoryException;
}
