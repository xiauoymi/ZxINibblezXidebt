package com.nibbledebt.intuit.cad.exception;

import com.nibbledebt.intuit.cad.data.Status;

public class ForbiddenException extends AggCatException {
	private static final long serialVersionUID = -701753025955295765L;

	public ForbiddenException(String errorMessage) {
		super(errorMessage);
	}

	public ForbiddenException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public ForbiddenException(String errorCode, String errorMessage,
			Status status, String errorResponseText) {
		super(errorCode, errorMessage, status, errorResponseText);
	}

	public ForbiddenException(Throwable throwable) {
		super(throwable);
	}

	public ForbiddenException(String errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
	}

	public ForbiddenException(String errorCode, String errorMessage,
			Throwable throwable) {
		super(errorCode, errorMessage, throwable);
	}
}