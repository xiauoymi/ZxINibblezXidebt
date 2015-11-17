/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.common.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Rocky Alam
 *
 */
public abstract class AbstractValidator implements Validator {

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public abstract boolean supports(Class<?> clazz);

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public abstract void validate(Object target, Errors errors) ;

}
