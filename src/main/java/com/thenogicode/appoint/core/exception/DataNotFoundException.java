package com.thenogicode.appoint.core.exception;

@SuppressWarnings("serial")
public class DataNotFoundException extends AbstractApplicationException {

	public DataNotFoundException(final String data, final String value) {
		super("No data found for " + data + " with value " + value);
	}

}
