package com.thenogicode.appoint.appuser.service.impl;

import org.springframework.stereotype.Service;

import com.thenogicode.appoint.appuser.data.AppUserData;
import com.thenogicode.appoint.appuser.domain.AppUser;
import com.thenogicode.appoint.appuser.repository.AppUserRepository;
import com.thenogicode.appoint.appuser.service.AppUserService;
import com.thenogicode.appoint.appuser.util.AppUserUtils;

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
	public AppUserData createNewAppUser(AppUser appUser) {
		AppUser createdAppUser= appUserRepository.save(appUser);
		return AppUserUtils.generateAppUserDataFrom(createdAppUser);
	}

}
