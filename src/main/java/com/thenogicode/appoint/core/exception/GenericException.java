package com.thenogicode.appoint.core.exception;

@SuppressWarnings("serial")
public class GenericException extends AbstractApplicationException {

	public GenericException(final String actionName) {
		super("An unknown error occurred during ", actionName);
	}

}
