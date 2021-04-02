package com.thenogicode.appoint.util;

import java.time.LocalTime;

public class DateHelper {
	
	private static final LocalTime ALLOWED_TIME_SCHEDULE_START= LocalTime.of(8,59);	//9:00AM
	private static final LocalTime ALLOWED_TIME_SCHEDULE_END= LocalTime.of(18,0);	//5:00PM
	
	
	public static boolean isTimeWithinAllowTimePeriod(LocalTime current) {
		return current.isAfter(ALLOWED_TIME_SCHEDULE_START)
				&& current.isBefore(ALLOWED_TIME_SCHEDULE_END);
	}
	
	public static boolean isTimeNotWithinAllowTimePeriod(LocalTime current) {
		return !isTimeWithinAllowTimePeriod(current);
	}

}
