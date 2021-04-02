package com.thenogicode.appoint.core.exception;

@SuppressWarnings("serial")
public abstract class AbstractApplicationException extends RuntimeException {
	
    private final String defaultUserMessage;
    private final Object[] defaultUserMessageArgs;
	
	protected AbstractApplicationException(final String defaultUserMessage,
            final Object... defaultUserMessageArgs) {
        this.defaultUserMessage = defaultUserMessage;
        this.defaultUserMessageArgs = defaultUserMessageArgs;
    }

	public String getDefaultUserMessage() {
		return defaultUserMessage;
	}

	public Object[] getDefaultUserMessageArgs() {
		return defaultUserMessageArgs;
	}
	
}
