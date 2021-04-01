package com.thenogicode.appoint.event.service;

import java.util.List;

import com.thenogicode.appoint.appuser.domain.DoctorAppUser;
import com.thenogicode.appoint.event.api.request.CreateEventRequest;
import com.thenogicode.appoint.event.data.EventData;
import com.thenogicode.appoint.event.domain.Event;

public interface EventService {
	
	public EventData createNewEvent(CreateEventRequest request);
	public EventData editEvent(Event event);
	public void deleteEvent(Long id);
	public List<EventData> retrieveByRange(String startDate, String endDate, DoctorAppUser doctor);

}
