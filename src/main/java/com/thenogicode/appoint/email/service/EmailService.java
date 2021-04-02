package com.thenogicode.appoint.email.service;

import com.thenogicode.appoint.appuser.domain.DoctorAppUser;
import com.thenogicode.appoint.event.domain.Event;

public interface EmailService {
	
	public void sendNotificationEmailForAppointmentCreated(Event event, DoctorAppUser doctor);

}
