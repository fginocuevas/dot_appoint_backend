package com.thenogicode.appoint.event.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventData {
	private Long id;
	private String patientName;
	private String eventDate;
	private String startTime;
	private String endTime;
	private String assignedTo;
	private String creationDateTime;
	private String createdBy;
	private boolean isAccepted;
	private String acceptedDateTime;
}
