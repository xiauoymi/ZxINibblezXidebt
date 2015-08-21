package com.nibbledebt.intuit.cad.exception;

import com.nibbledebt.intuit.cad.data.Status;

public class AggCatException extends Exception {
	private static final long serialVersionUID = 8955466107559861519L;
	private String errorCode;
	private String errorMessage;
	private Status status;
	private String errorResponseText;
	private Throwable throwable;

	public AggCatException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public AggCatException(String errorCode, String errorMessage) {
		super(errorCode + " : " + errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public AggCatException(String errorCode, String errorMessage,
			Status status, String errorResponseText) {
		super(errorCode + " : " + errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.status = status;
		this.errorResponseText = errorResponseText;
	}

	public AggCatException(Throwable throwable) {
		super(throwable);
		this.throwable = throwable;
	}

	public AggCatException(String errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
		this.errorMessage = errorMessage;
		this.throwable = throwable;
	}

	public AggCatException(String errorCode, String errorMessage,
			Throwable throwable) {
		super(errorCode + " : " + errorMessage, throwable);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.throwable = throwable;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public Status getStatus() {
		return this.status;
	}

	public String getErrorResponseText() {
		return this.errorResponseText;
	}

	public Throwable getThrowable() {
		return this.throwable;
	}
}