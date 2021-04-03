package com.thenogicode.appoint.appuser.service;

import java.util.List;

import com.thenogicode.appoint.appuser.data.AppUserData;
import com.thenogicode.appoint.appuser.domain.DoctorAppUser;
import com.thenogicode.appoint.appuser.domain.SchedulerAppUser;
import com.thenogicode.appoint.doctor.api.request.UpdateDoctorStatusRequest;

public interface AppUserService {
	
	public AppUserData retrieveAppUser(Long id);
	
	public AppUserData createNewDoctor(DoctorAppUser appUser);
	public AppUserData createNewScheduler(SchedulerAppUser appUser);

	public AppUserData updateDoctorStatus(UpdateDoctorStatusRequest request);

	List<AppUserData> retrieveAllDoctors(boolean onlyActive);
}
