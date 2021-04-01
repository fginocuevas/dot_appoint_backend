package com.thenogicode.appoint.event.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventData {
	private LocalDate eventDate;
	private LocalTime startTime;
	private LocalTime endTime;
	private String assignedTo;
	private LocalDateTime creationDateTime;
	private String createdBy;
}