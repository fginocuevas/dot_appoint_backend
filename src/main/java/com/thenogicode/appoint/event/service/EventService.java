package com.thenogicode.appoint.event.service;

import com.thenogicode.appoint.event.api.request.CreateEventRequest;
import com.thenogicode.appoint.event.data.EventData;
import com.thenogicode.appoint.event.domain.Event;

public interface EventService {
	
	public EventData createNewEvent(CreateEventRequest request);
	public EventData editEvent(Event event);
	public void deleteEvent(Long id);

}
