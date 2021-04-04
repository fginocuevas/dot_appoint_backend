package com.thenogicode.appoint.util;

import java.util.List;
import java.util.stream.Collectors;

import com.thenogicode.appoint.appuser.data.AppUserData;
import com.thenogicode.appoint.appuser.domain.AppUser;
import com.thenogicode.appoint.appuser.domain.DoctorAppUser;
import com.thenogicode.appoint.appuser.domain.RoleTypeEnum;
import com.thenogicode.appoint.appuser.domain.StatusTypeEnum;
import com.thenogicode.appoint.event.api.request.EditEventRequest;
import com.thenogicode.appoint.event.data.EventData;
import com.thenogicode.appoint.event.domain.Event;
import com.thenogicode.appoint.template.data.RoleTypeData;
import com.thenogicode.appoint.template.data.StatusTypeData;

import io.micrometer.core.instrument.util.StringUtils;
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
				.statusTypeId(user.getStatus())
				.statusDisplayText(StatusTypeEnum.fromInt(user.getStatus()).getDisplayText())
				.role(RoleTypeEnum.fromInt(user.getRoleType()).getDisplayText())
				.roleTypeId(user.getRoleType())
				.events(events == null? null : events.stream().map((e) -> generateEventDateFrom(e))
						.collect(Collectors.toList()))
				.build();
		
	} 
	
	public static EventData generateEventDateFrom(Event event) {
		return EventData.builder()
				.id(event.getId())
				.patientName(event.getPatientName())
				.eventDate(event.getEventDate().toString())
				.startTime(event.getStartTime().toString())
				.endTime(event.getEndTime().toString())
				.comments(StringUtils.isNotBlank(event.getComments())? event.getComments(): "")
				.assignedTo(event.getAssignedTo().getUserDisplayName())
				.doctorId(event.getAssignedTo().getId())
				.creationDateTime(event.getCreationDateTime().toString())
				.createdBy(event.getCreatedBy().getUserDisplayName())
				.isAccepted(event.isAccepted())
				.acceptedDateTime(event.getAcceptedDateTime()!= null? event.getAcceptedDateTime().toString(): null)
				.build();
	}
	
	public static void makeEventChanges(Event event, EditEventRequest request) {
		//TODO Better to implement checking if there were changes made
		// before setting the changes
		
		if(StringUtils.isNotBlank(request.getPatientName())) {
			event.setPatientName(request.getPatientName());
		}
		
		if(request.getEventDate() != null) {
			event.setEventDate(request.getEventDate());
		}
		
		if(request.getStartTime() != null) {
			event.setStartTime(request.getStartTime());
		}
		
		if(request.getEndTime() != null) {
			event.setEndTime(request.getEndTime());
		}
		
		if(StringUtils.isNotBlank(request.getComments())) {
			event.setComments(request.getComments());
		}
		
	}
	
	public static RoleTypeData generateRoleTypeDataFrom(RoleTypeEnum role) {
		return RoleTypeData.builder()
				.value(role.getValue())
				.displayText(role.getDisplayText())
				.build();
	}
	
	public static StatusTypeData generateStatusTypeDataFrom(StatusTypeEnum status) {
		return StatusTypeData.builder()
				.value(status.getValue())
				.displayText(status.getDisplayText())
				.build();
	}
	
}
