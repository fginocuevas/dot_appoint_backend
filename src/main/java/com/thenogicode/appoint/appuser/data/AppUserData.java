package com.thenogicode.appoint.appuser.data;

import java.util.List;

import com.thenogicode.appoint.event.data.EventData;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppUserData {
	private Long id;
    private String email;
	private String username;
	private String firstname;
	private String lastname;
	private Integer statusTypeId;
	private String statusDisplayText;
	private String role;
	private List<EventData> events;
}
