/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.common.security;

import java.util.ArrayList;
import java.util.List;

import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.nibbledebt.common.error.RepositoryException;
import com.nibbledebt.common.logging.LogLevel;
import com.nibbledebt.common.logging.Loggable;
import com.nibbledebt.core.data.model.NibblerDirectoryStatus;
import com.nibbledebt.core.processor.UsersProcessor;
import com.nibbledebt.web.rest.model.NibblerData;

/**
 * @author ralam1
 *
 */
public class MemberAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private UsersProcessor usersProcessor;

	@Autowired
	private MessageDigestPasswordEncoder encoder;

	@Autowired
	private String salt;

	@Override
	@Loggable(logLevel=LogLevel.INFO)
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		try {
			NibblerData nibbler = usersProcessor.retrieveNibbler(String
					.valueOf(authentication.getPrincipal()));
			if (nibbler == null) {
				throw new AuthenticationException(
						"The username you have entered is incorrect.") {
					private static final long serialVersionUID = 98475983755L;
				};
			} else {
				if (!StringUtils.equals(nibbler.getPassword(), encoder.encodePassword(
						String.valueOf(authentication.getCredentials()),
						salt))) {
					throw new AuthenticationException(
							"The username/password you have entered is incorrect.") {
						private static final long serialVersionUID = 89758937555L;
					};
				} else if(StringUtils.equals(nibbler.getPassword(), 
									encoder.encodePassword(String.valueOf(authentication.getCredentials()),
									salt)) && !nibbler.getStatus().equalsIgnoreCase(NibblerDirectoryStatus.ACTIVE.name())){
					  if(StringUtils.equals(nibbler.getStatus(), NibblerDirectoryStatus.RESET_REQUIRED.name())){
							throw new AuthenticationException(
									"This password on this account needs to be reset.") {
								private static final long serialVersionUID = 897589245435L;
							};
						} else if(StringUtils.equals(nibbler.getStatus(), NibblerDirectoryStatus.LOCKED_ATTEMPTS.name())){
							throw new AuthenticationException(
									"This account has been locked for 1 hour.") {
								private static final long serialVersionUID = 897589245435L;
							};
						} else if(StringUtils.equals(nibbler.getStatus(), NibblerDirectoryStatus.LOCKED_FRAUD.name())){
							throw new AuthenticationException(
									"This account has been locked and requires administrator intervention. Please call 1-800-NIBBLER") {
								private static final long serialVersionUID = 897589245435L;
							};
						} else{
							throw new AuthenticationException(
									"This account has not been activated.") {
								private static final long serialVersionUID = 897589245435L;
							};
						}				
				} else {
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
					usersProcessor.updateLastLogin(String
					.valueOf(authentication.getPrincipal()));
					return userAuth;
				}
			}
		} catch (IllegalArgumentException | RepositoryException e) {
			throw new AuthenticationException(
					"There was an error trying to login this user. Please try again later.") {
				private static final long serialVersionUID = 897589245435L;
			};
		} 
	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return UsernamePasswordAuthenticationToken.class
				.isAssignableFrom(authentication);
	}

}
