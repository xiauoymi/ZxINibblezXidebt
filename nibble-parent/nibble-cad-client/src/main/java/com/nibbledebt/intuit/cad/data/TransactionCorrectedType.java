package com.nibbledebt.intuit.cad.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "TransactionCorrectedType", namespace = "http://schema.intuit.com/platform/fdatafeed/transaction/v1")
@XmlEnum
public enum TransactionCorrectedType {
	REPLACE, DELETE;

	public String value() {
		return name();
	}

	public static TransactionCorrectedType fromValue(String v) {
		return valueOf(v);
	}
}