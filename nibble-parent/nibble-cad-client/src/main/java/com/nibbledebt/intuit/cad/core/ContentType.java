package com.nibbledebt.intuit.cad.core;

import com.nibbledebt.intuit.cad.util.StringUtils;

public enum ContentType {
	XML("application/xml"),

	JSON("application/json"),

	TEXT("text/plain"),

	HTML("text/html"),

	OCTETSTREAM("application/octet-stream");

	private String type = null;

	private ContentType(String type) {
		this.type = type;
	}

	public String toString() {
		return this.type;
	}

	public static String getContentType(String format) {
		String contentType = null;
		if (StringUtils.hasText(format)) {
			if (format.equalsIgnoreCase(XML.name()))
				contentType = XML.toString();
			else if (format.equalsIgnoreCase(JSON.name())) {
				contentType = JSON.toString();
			}
		}
		return contentType;
	}
}