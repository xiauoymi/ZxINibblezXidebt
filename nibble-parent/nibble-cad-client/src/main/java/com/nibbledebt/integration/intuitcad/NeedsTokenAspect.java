/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.intuitcad;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ralam
 *
 */
@Aspect
@Component
public class NeedsTokenAspect {
	
	@Autowired private SecurityContext securityContext;

	@Pointcut("execution(* *(..))")
	public void anyMethod() {
	}

	@Before("anyMethod() && @annotation(needsToken)")
	public void process(NeedsToken needsToken) throws Throwable {
		securityContext.refreshToken();
	}
}
