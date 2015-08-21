package com.nibbledebt.intuit.cad.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "PositionType", namespace = "http://schema.intuit.com/platform/fdatafeed/invtransaction/v1")
@XmlEnum
public enum PositionType {
	LONG, SHORT;

	public String value() {
		return name();
	}

	public static PositionType fromValue(String v) {
		return valueOf(v);
	}
}