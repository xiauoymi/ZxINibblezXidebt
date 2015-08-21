package com.nibbledebt.intuit.cad.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "InvestmentSubAccountType", namespace = "http://schema.intuit.com/platform/fdatafeed/transaction/v1")
@XmlEnum
public enum InvestmentSubAccountType {
	CASH, MARGIN, SHORT, OTHER;

	public String value() {
		return name();
	}

	public static InvestmentSubAccountType fromValue(String v) {
		return valueOf(v);
	}
}