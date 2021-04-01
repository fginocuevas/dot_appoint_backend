package com.thenogicode.appoint.appuser.service.impl;

import org.springframework.stereotype.Service;

import com.thenogicode.appoint.appuser.data.AppUserData;
import com.thenogicode.appoint.appuser.domain.AppUser;
import com.thenogicode.appoint.appuser.domain.DoctorAppUser;
import com.thenogicode.appoint.appuser.domain.SchedulerAppUser;
import com.thenogicode.appoint.appuser.repository.AppUserRepository;
import com.thenogicode.appoint.appuser.service.AppUserService;
import com.thenogicode.appoint.appuser.util.AppUserUtils;
import com.thenogicode.appoint.core.appuser.utils.AppUserConstants;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
	
	private final AppUserRepository appUserRepository;

	@Override
	public AppUserData retrieveAppUser(Long id) {
		AppUser appUser= appUserRepository.findById(id).orElse(null);
		
		if(appUser == null) {
			log.error("Failed to retrieve app user with id {}", id);
			return null;
		}
		
		return AppUserUtils.generateAppUserDataFrom(appUser);
	}
	
	@Override
	public AppUserData createNewScheduler(SchedulerAppUser appUser) {
		// Workaround for discriminator value not persisting right after create
		appUser.setRoleType(AppUserConstants.DISCRIMINATOR_VALUE_SCHEDULER);
		SchedulerAppUser createdAppUser= appUserRepository.saveAndFlush(appUser);
		return AppUserUtils.generateAppUserDataFrom(createdAppUser);
	}

	@Override
	public AppUserData createNewDoctor(DoctorAppUser appUser) {
		// Workaround for discriminator value not persisting right after create
		appUser.setRoleType(AppUserConstants.DISCRIMINATOR_VALUE_DOCTOR);
		DoctorAppUser createdAppUser= appUserRepository.saveAndFlush(appUser);
		return AppUserUtils.generateAppUserDataFrom(createdAppUser);
	}

	

}
