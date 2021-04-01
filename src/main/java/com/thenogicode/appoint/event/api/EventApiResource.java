package com.thenogicode.appoint.event.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thenogicode.appoint.event.api.request.CreateEventRequest;
import com.thenogicode.appoint.event.data.EventData;
import com.thenogicode.appoint.event.service.EventService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("event")
@RequiredArgsConstructor
public class EventApiResource {
	
	private final EventService eventService;
	
	@PostMapping(value="/create",
			consumes= MediaType.APPLICATION_JSON_VALUE, 
			produces= MediaType.APPLICATION_JSON_VALUE)
	public EventData createNewEvent(@RequestBody final CreateEventRequest request) {
		return eventService.createNewEvent(request);
	}

}

