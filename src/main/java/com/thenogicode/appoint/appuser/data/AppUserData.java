package com.thenogicode.appoint.appuser.data;

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
	private String status;
	private RoleData role;
}
