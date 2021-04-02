package com.thenogicode.appoint.core.exception;

@SuppressWarnings("serial")
public class DoctorUnableToAcceptEventException extends AbstractApplicationException {
	
	public DoctorUnableToAcceptEventException(final Long doctorId) {
		super("Doctor id " + doctorId + " does not match assigned doctor id in event"
				, doctorId);
	}

}
