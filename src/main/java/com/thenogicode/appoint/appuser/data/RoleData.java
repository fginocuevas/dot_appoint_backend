package com.thenogicode.appoint.appuser.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleData {
	private Integer roleType;
	private String role;
	private Boolean disabled;
}
