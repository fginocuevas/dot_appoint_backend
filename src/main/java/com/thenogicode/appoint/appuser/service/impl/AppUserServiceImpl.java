package com.thenogicode.appoint.appuser.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.thenogicode.appoint.appuser.data.AppUserData;
import com.thenogicode.appoint.appuser.domain.AppUser;
import com.thenogicode.appoint.appuser.domain.DoctorAppUser;
import com.thenogicode.appoint.appuser.domain.RoleTypeEnum;
import com.thenogicode.appoint.appuser.domain.SchedulerAppUser;
import com.thenogicode.appoint.appuser.domain.StatusTypeEnum;
import com.thenogicode.appoint.appuser.repository.AppUserRepository;
import com.thenogicode.appoint.appuser.service.AppUserService;
import com.thenogicode.appoint.core.appuser.utils.AppUserConstants;
import com.thenogicode.appoint.core.exception.DataNotFoundException;
import com.thenogicode.appoint.core.exception.GenericException;
import com.thenogicode.appoint.doctor.api.request.UpdateDoctorStatusRequest;
import com.thenogicode.appoint.util.EntityAdapterHelper;

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
		
		return EntityAdapterHelper.generateAppUserDataFrom(appUser);
	}
	
	@Override
	public List<AppUserData> retrieveAllDoctors(boolean onlyActive) {
		
		List<DoctorAppUser> doctors= appUserRepository.findAllByRoleType(RoleTypeEnum.DOCTOR.getValue());
		
		return doctors.stream().map(doctor -> EntityAdapterHelper.generateAppUserDataFrom(doctor))
				.collect(Collectors.toList());
	}
	
	@Override
	public AppUserData createNewScheduler(SchedulerAppUser appUser) {
		// Workaround for discriminator value not persisting right after create
		appUser.setRoleType(AppUserConstants.DISCRIMINATOR_VALUE_SCHEDULER);
		SchedulerAppUser createdAppUser= appUserRepository.saveAndFlush(appUser);
		return EntityAdapterHelper.generateAppUserDataFrom(createdAppUser);
	}

	@Override
	public AppUserData createNewDoctor(DoctorAppUser appUser) {
		// Workaround for discriminator value not persisting right after create
		appUser.setRoleType(AppUserConstants.DISCRIMINATOR_VALUE_DOCTOR);
		DoctorAppUser createdAppUser= appUserRepository.saveAndFlush(appUser);
		return EntityAdapterHelper.generateAppUserDataFrom(createdAppUser);
	}

	@Override
	public AppUserData updateDoctorStatus(UpdateDoctorStatusRequest request) {
		
		DoctorAppUser doctor= (DoctorAppUser) appUserRepository.findById(request.getDoctorId())
				.orElseThrow(() -> new DataNotFoundException("doctor", request.getDoctorId().toString()));
		
		if(StatusTypeEnum.fromInt(request.getStatusType()) == null) {
			throw new GenericException("Status type " + request.getStatusType() + " is invalid.");
		} else if (doctor.getStatus().equals(request.getStatusType())) {
			throw new GenericException("Doctor is already in the " 
						+ StatusTypeEnum.fromInt(request.getStatusType()).getDisplayText() + " status.");
		}
		
		doctor.setStatus(request.getStatusType());
		
		appUserRepository.saveAndFlush(doctor);
		
		return EntityAdapterHelper.generateAppUserDataFrom(doctor);
		
	}

}
