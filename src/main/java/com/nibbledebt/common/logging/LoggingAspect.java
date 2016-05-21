/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.common.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * @author Rocky Alam
 */
@Aspect
@Component
public class LoggingAspect {    
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Pointcut("execution(* *(..))")
    public void anyMethod() {
    }
	
    @Around("anyMethod() && @annotation(loggable)")
    public Object process(ProceedingJoinPoint pjp, Loggable loggable) throws Throwable {
        Object proceed;
        long startTs = System.currentTimeMillis();
        try {
            proceed = pjp.proceed();
            log(startTs, System.currentTimeMillis(), pjp, loggable.logLevel(), pjp.getArgs());
            return proceed;
        } catch (Exception e) {
            log(startTs, System.currentTimeMillis(), pjp, loggable.logLevel(), e, pjp.getArgs());
            throw e;
        } 
    }
    
    /**
     * Logging of a regular flow on a separate thread.
     * 
     * @param startTs
     * @param endTs
     * @param pjp
     * @param logLevel
     */
    private void log(	final Long startTs, 
    					final Long endTs, 
    					final ProceedingJoinPoint pjp,
    					final LogLevel logLevel,
    					final Object[] data){
    	taskExecutor.execute(new Runnable(){
    		public void run(){
    			long pTs = endTs - startTs;
	            String methodName = pjp.getSignature().getName();
	            String logMsg = "[Execution complete] "+ methodName + " : " + pTs + " ms";
	            logAtLevel(LoggerFactory.getLogger(pjp.getSignature().getDeclaringType()), logLevel, logMsg, data);
    		}
    	});    
    }
    
    /**
     * Logging of an error flow on a separate thread.
     * 
     * @param startTs
     * @param endTs
     * @param pjp
     * @param logLevel
     */
	private void log(	final Long startTs, 
						final Long endTs,
						final ProceedingJoinPoint pjp, 
						final LogLevel logLevel,
						final Throwable throwable,
    					final Object[] data) {
		taskExecutor.execute(new Runnable() {
			public void run() {
				long pTs = endTs - startTs;
				String methodName = pjp.getSignature().getName();
				String logMsg = "[Execution error] "+ methodName + " : " + pTs + " ms" + " : " + throwable;
				logAtLevel(LoggerFactory.getLogger(pjp.getSignature().getDeclaringType()), logLevel, logMsg, data);
			}
		});
	}
    
	/**
	 * switch between log levels based on the annotation parameter.
	 * 
	 * @param logger
	 * @param level
	 * @param message
	 */
	private void logAtLevel(Logger logger, LogLevel level, String message,  Object[] data){
		if(level == LogLevel.DEBUG)
			logger.debug(message, data);
		else if(level == LogLevel.INFO)
			logger.info(message, data);	
		else if(level == LogLevel.WARN)
			logger.warn(message);		
		else if (level == LogLevel.ERROR)
			logger.error(message);
		else if(level == LogLevel.TRACE)
			logger.trace(message);
		
	}
    
}