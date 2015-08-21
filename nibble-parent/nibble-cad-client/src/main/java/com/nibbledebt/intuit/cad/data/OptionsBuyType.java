package com.nibbledebt.intuit.cad.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "OptionsBuyType", namespace = "http://schema.intuit.com/platform/fdatafeed/invtransaction/v1")
@XmlEnum
public enum OptionsBuyType {
	BUYTOOPEN, BUYTOCLOSE;

	public String value() {
		return name();
	}

	public static OptionsBuyType fromValue(String v) {
		return valueOf(v);
	}
}