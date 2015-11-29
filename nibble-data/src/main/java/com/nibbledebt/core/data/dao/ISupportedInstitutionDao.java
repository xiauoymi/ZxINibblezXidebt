/**
 * 
 */
package com.nibbledebt.core.data.dao;

import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.SupportedInstitution;

/**
 * @author alam_home
 *
 */
public interface ISupportedInstitutionDao extends IDao<SupportedInstitution>{

	SupportedInstitution findByDisplayName(String displayName) throws RepositoryException;

	SupportedInstitution findByType(String type) throws RepositoryException;

	SupportedInstitution findByTestModeSupport(String type) throws RepositoryException;

}
