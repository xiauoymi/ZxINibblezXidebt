package com.nibbledebt.intuit.cad.exception;

import com.nibbledebt.intuit.cad.data.Status;

public class BadRequestException extends AggCatException {
	private static final long serialVersionUID = 1669964679974595082L;

	public BadRequestException(String errorMessage) {
		super(errorMessage);
	}

	public BadRequestException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public BadRequestException(String errorCode, String errorMessage,
			Status status, String errorResponseText) {
		super(errorCode, errorMessage, status, errorResponseText);
	}

	public BadRequestException(Throwable throwable) {
		super(throwable);
	}

	public BadRequestException(String errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
	}

	public BadRequestException(String errorCode, String errorMessage,
			Throwable throwable) {
		super(errorCode, errorMessage, throwable);
	}
}