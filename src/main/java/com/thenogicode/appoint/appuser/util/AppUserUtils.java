package com.thenogicode.appoint.appuser.util;

import com.thenogicode.appoint.appuser.data.AppUserData;
import com.thenogicode.appoint.appuser.data.RoleData;
import com.thenogicode.appoint.appuser.domain.AppUser;
import com.thenogicode.appoint.appuser.domain.Role;
import com.thenogicode.appoint.appuser.domain.RoleTypeEnum;
import com.thenogicode.appoint.appuser.domain.StatusTypeEnum;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class AppUserUtils {
	
	public static RoleData generateRoleDataFrom(Role role) {
		if(null == role) {
			log.error("Role cannot be null. Error occurred during generation of role data");
			//TODO Throw exception
			return null;
		}
		
		return RoleData.builder()
				.roleType(role.getRoleType())
				.role(RoleTypeEnum.fromInt(role.getRoleType()).getDisplayText())
				.disabled(role.getDisabled())
				.build();
		
	} 
	
	public static AppUserData generateAppUserDataFrom(AppUser user) {
		if(null == user) {
			log.error("Role cannot be null. Error occurred during generation of role data");
			//TODO Throw exception
			return null;
		}
		
		return AppUserData.builder()
				.id(user.getId())
				.email(user.getEmail())
				.username(user.getUsername())
				.firstname(user.getFirstname())
				.lastname(user.getLastname())
				.status(StatusTypeEnum.fromInt(user.getStatus()).getDisplayText())
				.role(generateRoleDataFrom(user.getRole()))
				.build();
		
	} 

}
