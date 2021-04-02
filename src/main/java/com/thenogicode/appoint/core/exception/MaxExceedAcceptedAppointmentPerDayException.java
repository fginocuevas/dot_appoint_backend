package com.thenogicode.appoint.core.exception;

import java.time.LocalDate;

import com.thenogicode.appoint.core.appuser.utils.EventConstants;

@SuppressWarnings("serial")
public class MaxExceedAcceptedAppointmentPerDayException extends AbstractApplicationException {
	
	public MaxExceedAcceptedAppointmentPerDayException(final LocalDate date, final Long doctorId) {
		super("Already exceeded max accepted appointments per day of "
				.concat(EventConstants.MAX_ACCEPTED_APPOINTMENT_PER_DAY.toString())
				.concat(" for doctor id ").concat(doctorId.toString())
				, date.toString()
				, doctorId);
	}

}
	
