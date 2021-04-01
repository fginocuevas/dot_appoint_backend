package com.thenogicode.appoint.appuser.service;

import com.thenogicode.appoint.appuser.data.AppUserData;
import com.thenogicode.appoint.appuser.domain.AppUser;

public interface AppUserService {
	
	public AppUserData retrieveAppUser(Long id);
	public AppUserData createNewAppUser(AppUser appUser);
	
}
