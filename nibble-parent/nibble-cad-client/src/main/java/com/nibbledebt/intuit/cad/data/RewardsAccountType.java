package com.nibbledebt.intuit.cad.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "RewardsAccountType", namespace = "http://schema.intuit.com/platform/fdatafeed/rewardsaccount/v1")
@XmlEnum
public enum RewardsAccountType {
	AFFINITY, AIRLINE, AUTO, HOTEL, SHOPPING, OTHER;

	public String value() {
		return name();
	}

	public static RewardsAccountType fromValue(String v) {
		return valueOf(v);
	}
}