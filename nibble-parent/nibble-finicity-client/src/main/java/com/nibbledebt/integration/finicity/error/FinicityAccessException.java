/**
 * 
 */
package com.nibbledebt.integration.finicity.error;

/**
 * @author alam_home
 *
 */
public class FinicityAccessException extends Exception {
	private static final long serialVersionUID = 9202415065871503063L;
	public FinicityAccessException(Exception e){
		super(e);
	}
}
