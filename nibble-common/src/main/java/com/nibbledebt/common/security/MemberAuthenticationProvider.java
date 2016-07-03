/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.common.security;

import java.util.ArrayList;
import java.util.List;

import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.nibbledebt.common.logging.LogLevel;
import com.nibbledebt.common.logging.Loggable;
import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.NibblerDirectoryStatus;
import com.nibbledebt.domain.model.NibblerData;

/**
 * @author ralam1
 *
 */
public class MemberAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private UsersDataProcessor usersDataProcessor;

	@Autowired
	private MessageDigestPasswordEncoder encoder;

	@Autowired
	private String salt;

	@Override
	@Loggable(logLevel=LogLevel.INFO)
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		try {
			NibblerData nibbler = usersDataProcessor.retrieveNibbler(String
					.valueOf(authentication.getPrincipal()));
			if (nibbler == null) {
				throw new BadCredentialsException(
						"The username you have entered is incorrect.") ;
			} else {
				if (!StringUtils.equals(nibbler.getPassword(), encoder.encodePassword(
						String.valueOf(authentication.getCredentials()),
						salt))) {
					throw new BadCredentialsException(
							"The username/password you have entered is incorrect.") ;
				} else if(StringUtils.equals(nibbler.getPassword(), encoder.encodePassword(String.valueOf(authentication.getCredentials()),salt)) && !nibbler.getStatus().equalsIgnoreCase(NibblerDirectoryStatus.ACTIVE.name())){
					  if(StringUtils.equals(nibbler.getStatus(), NibblerDirectoryStatus.RESET_REQUIRED.name())){
							throw new BadCredentialsException(
									"This password on this account needs to be reset.");
						} else if(StringUtils.equals(nibbler.getStatus(), NibblerDirectoryStatus.LOCKED_ATTEMPTS.name())){
							throw new BadCredentialsException(
									"This account has been locked for 1 hour.");
						} else if(StringUtils.equals(nibbler.getStatus(), NibblerDirectoryStatus.LOCKED_FRAUD.name())){
							throw new BadCredentialsException(
									"This account has been locked and requires administrator intervention. Please call 1-800-NIBBLER") ;
						} else{
							throw new BadCredentialsException(
									"Your account has not been activated. Check your email for the activation link. If you did not receive an email, please contact Customer Care at info@nibbledebt.com") ;
						}				
				} else if(!nibbler.getRoles().contains("nibbler_level_1")){
					if(nibbler.isFundingConnected() && nibbler.isLoanConnected()){
						throw new BadCredentialsException(
								"Nibble's web app is coming soon. Please check your email for weekly updates or contact us at info@nibbledebt.com") ;	
					}else{
						throw new BadCredentialsException(
								"ACCOUNT_NOT_LINKED") ;
					}
					 
				}else {
					MemberAuthentication userAuth = new MemberAuthentication();
					MemberDetails userDetails = new MemberDetails();
					userDetails.setFirstName(nibbler.getFirstName());
					userDetails.setLastName(nibbler.getLastName());
					userDetails.setIsFirstLogin(nibbler.getIsFirstLogin());
					userDetails.setUsername(String
					.valueOf(authentication.getPrincipal()));
					List<GrantedAuthority> auths = new ArrayList<>();
					for (String role : nibbler.getRoles())
						auths.add(new SimpleGrantedAuthority(role));
					userDetails.setAuthorities(auths);
					userAuth.setAuthenticated(true);
					userAuth.setPrincipal(userDetails);
					userAuth.setDetails(authentication.getDetails());
					usersDataProcessor.updateLastLogin(String
					.valueOf(authentication.getPrincipal()));
					return userAuth;
				}
			}
		} catch (IllegalArgumentException | RepositoryException e) {
			throw new AuthenticationCredentialsNotFoundException(
					"There was an error trying to login this user. Please try again later.");
		} 
	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return UsernamePasswordAuthenticationToken.class
				.isAssignableFrom(authentication);
	}

}
