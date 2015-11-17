/**
 * 
 */
package com.nibbledebt.integration.finicity.model;

import java.util.Date;

import org.dozer.CustomConverter;

/**
 * @author alam_home
 *
 */
public class DateToUtilDate implements CustomConverter {

	/* (non-Javadoc)
	 * @see org.dozer.CustomConverter#convert(java.lang.Object, java.lang.Object, java.lang.Class, java.lang.Class)
	 */
	@Override
	public Object convert(Object dest, Object src, Class<?> destClass, Class<?> srcClass) {
		if(destClass == Date.class){
			if(src instanceof Long){
				return new Date(((Long)src)*1000);
			}
		}else {
			if(src instanceof Date){
				return ((Date)src).getTime()/1000;
			}
		}
		return null;
	}

}
