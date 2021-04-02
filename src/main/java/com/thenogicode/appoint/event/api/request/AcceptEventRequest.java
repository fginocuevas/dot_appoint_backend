package com.thenogicode.appoint.event.api.request;

import lombok.Data;

@Data
public class AcceptEventRequest {
	
	private Long eventId;
	private Long doctorId;

}
