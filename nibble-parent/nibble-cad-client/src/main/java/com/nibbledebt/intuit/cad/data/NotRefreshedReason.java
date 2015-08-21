package com.nibbledebt.intuit.cad.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "NotRefreshedReason", namespace = "http://schema.intuit.com/platform/fdatafeed/transactionlist/v1")
@XmlEnum
public enum NotRefreshedReason {
	NOT_NECESSARY, CREDENTIALS_REQUIRED, CHALLENGE_RESPONSE_REQUIRED, UNAVAILABLE;

	public String value() {
		return name();
	}

	public static NotRefreshedReason fromValue(String v) {
		return valueOf(v);
	}
}