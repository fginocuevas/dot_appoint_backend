package com.thenogicode.appoint.event.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.thenogicode.appoint.appuser.domain.DoctorAppUser;
import com.thenogicode.appoint.appuser.domain.SchedulerAppUser;
import com.thenogicode.appoint.appuser.repository.AppUserRepository;
import com.thenogicode.appoint.event.api.request.CreateEventRequest;
import com.thenogicode.appoint.event.data.EventData;
import com.thenogicode.appoint.event.domain.Event;
import com.thenogicode.appoint.event.repository.EventRepository;
import com.thenogicode.appoint.event.service.EventService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
	
	private final EventRepository eventRepository;
	private final AppUserRepository appUserRepository;

	@Override
	public EventData createNewEvent(final CreateEventRequest request) {

		
		//TODO Add validations 
		
		DoctorAppUser doctor= (DoctorAppUser) appUserRepository.findById(request.getAssignedDoctorId())
									.orElse(null);
		
		SchedulerAppUser scheduler= (SchedulerAppUser) appUserRepository.findById(request.getSchedulerId())
										.orElse(null);
		
		/*
		 * The entire system should only accept a maximum of 5 appointments per day 
		 * The system should only allow a doctor to have 3 maximum number of approve 
		 * appointments per day
		 */
		
		Event event= generatedEventFrom(request, doctor, scheduler);
		
		Event createdEvent= eventRepository.save(event);
		
		if(createdEvent == null) {
			log.error("Error occurred during creation of event");
			//TODO Throw exception here
			return null;
		}
		
		return EventData.builder()
				.eventDate(createdEvent.getEventDate())
				.startTime(createdEvent.getStartTime())
				.endTime(createdEvent.getEndTime())
				.assignedTo(createdEvent.getAssignedTo().getUserDisplayName())
				.creationDateTime(createdEvent.getCreationDateTime())
				.createdBy(createdEvent.getCreatedBy().getUserDisplayName())
				.build();
	}

	@Override
	public EventData editEvent(Event event) {
		return null;
	}

	@Override
	public void deleteEvent(Long id) {
		eventRepository.deleteById(id);
	}

	private Event generatedEventFrom(CreateEventRequest request, DoctorAppUser doctor, SchedulerAppUser scheduler) {
		return Event.builder()
				.eventDate(request.getEventDate())
				.startTime(request.getStartTime())
				.endTime(request.getEndTime())
				.assignedTo(doctor)
				.creationDateTime(LocalDateTime.now())
				.createdBy(scheduler)
				.build();
	}
}
