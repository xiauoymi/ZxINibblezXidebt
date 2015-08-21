package com.nibbledebt.intuit.cad.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "CategorizationSource", namespace = "http://schema.intuit.com/platform/fdatafeed/transaction/v1")
@XmlEnum
public enum CategorizationSource {
	AGGR, OFX, CAT;

	public String value() {
		return name();
	}

	public static CategorizationSource fromValue(String v) {
		return valueOf(v);
	}
}