package com.thenogicode.appoint.event.api.request;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class EditEventRequest {
	private Long id;
	private String patientName;
	private LocalDate eventDate;
	private LocalTime startTime;
	private LocalTime endTime;
	private Long assignedDoctorId;
	private Long schedulerId;
	private String comments;
}
