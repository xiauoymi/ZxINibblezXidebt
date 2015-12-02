/**
 * 
 */
package com.nibbledebt.integration.mapper;

import org.dozer.CustomConverter;

/**
 * @author alam_home
 *
 */
public class LoginFieldConverter implements CustomConverter{

	@Override
	public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,
			Class<?> sourceClass) {
		if(sourceFieldValue == null){
			return null;
		}
		
		Boolean displayFlag = false;
		if(sourceFieldValue instanceof Integer){
			Integer displayOrder = (Integer) sourceFieldValue;
			displayFlag = displayOrder > 0 ? true : false;
		} else if (sourceFieldValue instanceof Boolean){
            return null;
        }
		return displayFlag;
	}

}
