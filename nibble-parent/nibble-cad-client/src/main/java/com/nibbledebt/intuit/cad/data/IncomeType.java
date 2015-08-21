package com.nibbledebt.intuit.cad.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "IncomeType", namespace = "http://schema.intuit.com/platform/fdatafeed/invtransaction/v1")
@XmlEnum
public enum IncomeType {
	CGLONG, CGSHORT, DIV, INTEREST, MISC;

	public String value() {
		return name();
	}

	public static IncomeType fromValue(String v) {
		return valueOf(v);
	}
}