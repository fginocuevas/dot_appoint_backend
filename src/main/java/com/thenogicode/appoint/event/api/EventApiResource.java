package com.thenogicode.appoint.event.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thenogicode.appoint.event.api.request.AcceptEventRequest;
import com.thenogicode.appoint.event.api.request.CreateEventRequest;
import com.thenogicode.appoint.event.api.request.EditEventRequest;
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
	
	@PostMapping(value="/accept",
			consumes= MediaType.APPLICATION_JSON_VALUE, 
			produces= MediaType.APPLICATION_JSON_VALUE)
	public EventData acceptedEvent(@RequestBody final AcceptEventRequest request) {
		return eventService.acceptEvent(request);
	}
	
	@GetMapping(value="/retrieve/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public EventData retrieveOne(@PathVariable final Long id) {
		return eventService.retrieveOne(id);
	}
	
	@GetMapping(value="/retrieveByDateRange",
			consumes= MediaType.APPLICATION_JSON_VALUE, 
			produces= MediaType.APPLICATION_JSON_VALUE)
	public List<EventData> retrieveEventsByRange(@RequestParam(name = "startDate")  String startDate, 
			@RequestParam(name = "endDate")  String endDate){
		return eventService.retrieveByRange(startDate, endDate, null);
	}
	
	@GetMapping(value="/retrieveAll",
			consumes= MediaType.APPLICATION_JSON_VALUE, 
			produces= MediaType.APPLICATION_JSON_VALUE)
	public List<EventData> retrieveAllEvents(){
		return eventService.retrieveAllEvents();
	}
	
	@GetMapping(value="/retrieveByDateRangeAndDoctor",
			consumes= MediaType.APPLICATION_JSON_VALUE, 
			produces= MediaType.APPLICATION_JSON_VALUE)
	public List<EventData> retrieveEventsByRangeAndDoctor(
			@RequestParam(name = "startDate")  String startDate, 
			@RequestParam(name = "endDate")  String endDate, 
			@RequestParam(name = "doctorId")  Long doctorId){
		return eventService.retrieveByRange(startDate, endDate, doctorId);
	}
	
	@PutMapping(value="/edit",
			consumes= MediaType.APPLICATION_JSON_VALUE, 
			produces= MediaType.APPLICATION_JSON_VALUE)
	public EventData editEvent(@RequestBody final EditEventRequest request) {
		return eventService.editEvent(request);
	}
	
	@GetMapping(value="/delete/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteAnEvent(@PathVariable final Long id){
		eventService.deleteEvent(id);
		return ResponseEntity.ok(null);
	}

}

