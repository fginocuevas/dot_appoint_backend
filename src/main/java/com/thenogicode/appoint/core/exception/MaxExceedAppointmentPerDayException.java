package com.thenogicode.appoint.core.exception;

import java.time.LocalDate;

import com.thenogicode.appoint.core.utils.EventConstants;

@SuppressWarnings("serial")
public class MaxExceedAppointmentPerDayException extends AbstractApplicationException {
	
	public MaxExceedAppointmentPerDayException(final LocalDate date) {
		super("Already exceeded max appointments per day of "
				.concat(EventConstants.MAX_APPOINTMENTS_PER_DAY.toString()), date.toString());
	}

}
