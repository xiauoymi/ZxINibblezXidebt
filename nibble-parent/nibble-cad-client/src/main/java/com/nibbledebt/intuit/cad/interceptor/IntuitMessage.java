package com.nibbledebt.intuit.cad.interceptor;

public class IntuitMessage {
	private String requestType;
	private RequestElements requestElements = new RequestElements();

	private ResponseElements responseElements = new ResponseElements();

	public RequestElements getRequestElements() {
		return this.requestElements;
	}

	public ResponseElements getResponseElements() {
		return this.responseElements;
	}

	/**
	 * @return the requestType
	 */
	public String getRequestType() {
		return requestType;
	}

	/**
	 * @param requestType the requestType to set
	 */
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
}