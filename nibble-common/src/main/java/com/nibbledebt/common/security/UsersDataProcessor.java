/**
 * 
 */
package com.nibbledebt.common.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nibbledebt.core.data.dao.INibblerDao;
import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.Nibbler;
import com.nibbledebt.core.data.model.NibblerRole;
import com.nibbledebt.core.processor.AbstractProcessor;
import com.nibbledebt.domain.model.NibblerData;

/**
 * @author alam_home
 *
 */
@Component
public class UsersDataProcessor extends AbstractProcessor {
	@Autowired
	private INibblerDao nibblerDao;
	
	@Transactional(propagation=Propagation.REQUIRED)
	@CacheEvict(value="nibblerCache", key="#username")
	public void updateLastLogin(String username) throws RepositoryException{
		Nibbler nibbler = nibblerDao.find(username);
		nibbler.getNibblerDir().setLastLoginTs(new Date(System.currentTimeMillis()));
		nibblerDao.update(nibbler);
	}

	@Transactional(readOnly=true)
	public NibblerData retrieveNibbler(String username) throws RepositoryException{
		Nibbler nibbler = nibblerDao.find(username);
		if(nibbler!=null){
			NibblerData mData = new NibblerData();
			mData.setFirstName(nibbler.getFirstName());
			mData.setLastName(nibbler.getLastName());
			mData.setEmail(nibbler.getEmail());
			mData.setPassword(nibbler.getNibblerDir().getPassword());
			mData.setStatus(nibbler.getNibblerDir().getStatus());
			mData.setResetCode(nibbler.getNibblerDir().getResetCode());
			nibbler.getAccounts().forEach(a->{
				if(a.getUseForRounding()){
					mData.setFundingConnected(true);
				}else{
					mData.setLoanConnected(true);
				}
			});
			mData.setIsFirstLogin(nibbler.getNibblerDir().getLastLoginTs()==null);
			for(NibblerRole role : nibbler.getNibblerDir().getRoles()){
				mData.getRoles().add(role.getName());
			}
			return mData;
		}else{
			return null;
		}		
	}
}