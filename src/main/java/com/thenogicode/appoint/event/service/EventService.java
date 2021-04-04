package com.thenogicode.appoint.event.service;

import java.util.List;

import com.thenogicode.appoint.event.api.request.AcceptEventRequest;
import com.thenogicode.appoint.event.api.request.CreateEventRequest;
import com.thenogicode.appoint.event.api.request.EditEventRequest;
import com.thenogicode.appoint.event.data.EventData;
import com.thenogicode.appoint.event.domain.Event;

public interface EventService {
	
	public EventData createNewEvent(CreateEventRequest request);
	public EventData editEvent(EditEventRequest request);
	public void deleteEvent(Long id);
	
	public EventData acceptEvent(AcceptEventRequest request);
	
	public List<EventData> retrieveByRange(String startDate, String endDate, Long doctorId);
	public EventData retrieveOne(Long id);
	public List<EventData> retrieveAllEvents();
	public List<EventData> retrieveAllEventsByDoctor(Long id);
	
}
