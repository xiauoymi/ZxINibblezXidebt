/**
 * 
 */
package com.nibbledebt.core.data.model;

/**
 * @author ralam
 *
 */
public enum NibblerType {
	starter("starter"),
	receiver("receiver"),
	contributor("contributor");
	
	String value;
	
	String value(){
		return value;
	}
	
	NibblerType(String value){
		this.value = value;
	}
}
