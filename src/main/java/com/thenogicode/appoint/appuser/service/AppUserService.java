package com.thenogicode.appoint.appuser.service;

import com.thenogicode.appoint.appuser.data.AppUserData;
import com.thenogicode.appoint.appuser.domain.DoctorAppUser;
import com.thenogicode.appoint.appuser.domain.SchedulerAppUser;

public interface AppUserService {
	
	public AppUserData retrieveAppUser(Long id);
	
	public AppUserData createNewDoctor(DoctorAppUser appUser);
	public AppUserData createNewScheduler(SchedulerAppUser appUser);
}
