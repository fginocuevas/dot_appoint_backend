package com.thenogicode.appoint.util;

import java.util.List;
import java.util.stream.Collectors;

import com.thenogicode.appoint.appuser.data.AppUserData;
import com.thenogicode.appoint.appuser.domain.AppUser;
import com.thenogicode.appoint.appuser.domain.DoctorAppUser;
import com.thenogicode.appoint.appuser.domain.RoleTypeEnum;
import com.thenogicode.appoint.appuser.domain.StatusTypeEnum;
import com.thenogicode.appoint.event.data.EventData;
import com.thenogicode.appoint.event.domain.Event;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class EntityAdapterHelper {
	
	public static AppUserData generateAppUserDataFrom(AppUser user) {
		
		List<Event> events= null;
		
		if(null == user) {
			log.error("Role cannot be null. Error occurred during generation of role data");
			//TODO Throw exception
			return null;
		}
		
		if(user instanceof DoctorAppUser) {
			events= ((DoctorAppUser) user).getEvents();
		}
		
		return AppUserData.builder()
				.id(user.getId())
				.email(user.getEmail())
				.username(user.getUsername())
				.firstname(user.getFirstname())
				.lastname(user.getLastname())
				.status(StatusTypeEnum.fromInt(user.getStatus()).getDisplayText())
				.role(RoleTypeEnum.fromInt(user.getRoleType()).getDisplayText())
				.events(events == null? null : events.stream().map((e) -> generateEventDateFrom(e))
						.collect(Collectors.toList()))
				.build();
		
	} 
	
	public static EventData generateEventDateFrom(Event event) {
		return EventData.builder()
				.id(event.getId())
				.eventDate(event.getEventDate())
				.startTime(event.getStartTime())
				.endTime(event.getEndTime())
				.assignedTo(event.getAssignedTo().getUserDisplayName())
				.creationDateTime(event.getCreationDateTime())
				.createdBy(event.getCreatedBy().getUserDisplayName())
				.isAccepted(event.isAccepted())
				.acceptedDateTime(event.getAcceptedDateTime())
				.build();
	}
	
}
