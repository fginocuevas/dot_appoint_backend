package com.thenogicode.appoint.appuser.util;

import com.thenogicode.appoint.appuser.data.AppUserData;
import com.thenogicode.appoint.appuser.domain.AppUser;
import com.thenogicode.appoint.appuser.domain.DoctorAppUser;
import com.thenogicode.appoint.appuser.domain.RoleTypeEnum;
import com.thenogicode.appoint.appuser.domain.StatusTypeEnum;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class AppUserUtils {
	
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
				.role(RoleTypeEnum.fromInt(user.getRoleType()).getDisplayText())
				.build();
		
	} 
	
}
