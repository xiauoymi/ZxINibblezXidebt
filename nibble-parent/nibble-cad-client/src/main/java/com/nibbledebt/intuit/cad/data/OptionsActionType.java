package com.nibbledebt.intuit.cad.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "OptionsActionType", namespace = "http://schema.intuit.com/platform/fdatafeed/invtransaction/v1")
@XmlEnum
public enum OptionsActionType {
	EXERCISE, ASSIGN, EXPIRE;

	public String value() {
		return name();
	}

	public static OptionsActionType fromValue(String v) {
		return valueOf(v);
	}
}