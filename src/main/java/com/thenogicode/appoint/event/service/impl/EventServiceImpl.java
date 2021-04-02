package com.thenogicode.appoint.event.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.thenogicode.appoint.appuser.domain.DoctorAppUser;
import com.thenogicode.appoint.appuser.domain.SchedulerAppUser;
import com.thenogicode.appoint.appuser.repository.AppUserRepository;
import com.thenogicode.appoint.core.appuser.utils.EventConstants;
import com.thenogicode.appoint.core.exception.DataNotFoundException;
import com.thenogicode.appoint.core.exception.DoctorUnableToAcceptEventException;
import com.thenogicode.appoint.core.exception.MaxExceedAcceptedAppointmentPerDayException;
import com.thenogicode.appoint.core.exception.MaxExceedAppointmentPerDayException;
import com.thenogicode.appoint.event.api.request.AcceptEventRequest;
import com.thenogicode.appoint.event.api.request.CreateEventRequest;
import com.thenogicode.appoint.event.data.EventData;
import com.thenogicode.appoint.event.domain.Event;
import com.thenogicode.appoint.event.repository.EventRepository;
import com.thenogicode.appoint.event.service.EventService;
import com.thenogicode.appoint.util.AdapterUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
	
	private final EventRepository eventRepository;
	private final AppUserRepository appUserRepository;

	@Override
	public List<EventData> retrieveByRange(String startDate, String endDate, Long doctorId) {
		
		LocalDate startDateLocal= LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
		LocalDate endDateLocal= LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);
		
		List<Event> events= new ArrayList<Event>();
		
		if(doctorId == null) {
			events= eventRepository.retrieveAllByRange(startDateLocal, endDateLocal);
		} else {
			events= eventRepository.retrieveAllByRangeAndDoctorId(startDateLocal, endDateLocal, doctorId);
		}
		
		return events.stream().map(AdapterUtils::generateEventDateFrom)
					.collect(Collectors.toList());
	}

	@Override
	public EventData createNewEvent(final CreateEventRequest request) {
		
		DoctorAppUser doctor= (DoctorAppUser) appUserRepository.findById(request.getAssignedDoctorId())
									.orElseThrow(() -> new DataNotFoundException("doctor", request.getAssignedDoctorId().toString()));
		
		SchedulerAppUser scheduler= (SchedulerAppUser) appUserRepository.findById(request.getSchedulerId())
										.orElseThrow(()-> new DataNotFoundException("scheduler", request.getSchedulerId().toString()));
		
		validateMaxAppointmentPerDay(request.getEventDate());
		
		Event event= generatedNewEventFrom(request, doctor, scheduler);
		
		Event createdEvent= eventRepository.save(event);
		
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

	@Override
	public EventData acceptEvent(AcceptEventRequest request) {
		
		Long targetEventId= request.getEventId();
		Long targetDoctorId= request.getDoctorId();
		
		Event event= eventRepository.findById(targetEventId)
				.orElseThrow(() -> new DataNotFoundException("event",targetEventId.toString()));
		
		/*
		 * The entire system should only accept a maximum of 5 appointments per day 
		 * The system should only allow a doctor to have 3 maximum number of approve 
		 * appointments per day
		 */
		
		validateMaxAppointmentPerDay(event.getEventDate());
		validateRequestDoctorInEvent(event, targetDoctorId);
		validateMaxAcceptedAppointmentForDoctorPerDay(event.getEventDate(), targetDoctorId);
		
		log.info("Accepting event with id: {}", targetEventId);
		
		event.setAccepted(true);
		event.setAcceptedDateTime(LocalDateTime.now());
		
		event= eventRepository.save(event);
		
		return AdapterUtils.generateEventDateFrom(event);
		
	}

	private void validateRequestDoctorInEvent(Event event, Long targetDoctorId) {
		if(event.getAssignedTo().getId().equals(targetDoctorId)) {
			throw new DoctorUnableToAcceptEventException(targetDoctorId);
		}
	}

	/**
	 * 
	 * Validate if system has reached max appointments created for the day
	 * {@link EventConstants#MAX_APPOINTMENTS_PER_DAY}
	 * 
	 * @param date
	 */
	private void validateMaxAppointmentPerDay(LocalDate date) {
		
		List<Event> allEventsForTheDay= eventRepository.retrieveAllByDate(date);
		
		if(allEventsForTheDay.size() >= EventConstants.MAX_APPOINTMENTS_PER_DAY) {
			log.info("Exceeded max appointments per day of {}", EventConstants.MAX_APPOINTMENTS_PER_DAY);
			throw new MaxExceedAppointmentPerDayException(date);
		}
	}

	/**
	 * 
	 * Validate if system has reached max appointments created for the day
	 * {@link EventConstants#MAX_ACCEPTED_APPOINTMENT_PER_DAY}
	 * 
	 * @param date
	 * @param doctorId
	 */
	private void validateMaxAcceptedAppointmentForDoctorPerDay(LocalDate date, Long doctorId) {
		
		List<Event> acceptedEventsForTheDay= eventRepository.retrieveAcceptedByDateAndDoctorId(date, doctorId);
		
		if(acceptedEventsForTheDay.size() >= EventConstants.MAX_ACCEPTED_APPOINTMENT_PER_DAY) {
			log.info("Exceeded max accepted appointments per day of {} for doctor {}", 
					EventConstants.MAX_ACCEPTED_APPOINTMENT_PER_DAY,
					doctorId);
			throw new MaxExceedAcceptedAppointmentPerDayException(date, doctorId);
		}
		
	}

	private Event generatedNewEventFrom(CreateEventRequest request, DoctorAppUser doctor, SchedulerAppUser scheduler) {
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
