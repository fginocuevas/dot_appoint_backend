package com.thenogicode.appoint.doctor.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thenogicode.appoint.appuser.data.AppUserData;
import com.thenogicode.appoint.appuser.domain.DoctorAppUser;
import com.thenogicode.appoint.appuser.service.AppUserService;
import com.thenogicode.appoint.doctor.api.request.UpdateDoctorStatusRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("doctor")
@RequiredArgsConstructor
public class DoctorAppUserApiResource {
	
	private final AppUserService appUserService;
	
	@PostMapping(value="/create",
			consumes= MediaType.APPLICATION_JSON_VALUE,
			produces= MediaType.APPLICATION_JSON_VALUE)
	public AppUserData createNewScheduler(@RequestBody DoctorAppUser appUser) {
		return appUserService.createNewDoctor(appUser);
	}
	
	@PutMapping(value="/changeStatus",
			consumes= MediaType.APPLICATION_JSON_VALUE,
			produces= MediaType.APPLICATION_JSON_VALUE)
	public AppUserData updateDoctorStatus(@RequestBody UpdateDoctorStatusRequest request) {
		return appUserService.updateDoctorStatus(request);
	}
	
	@GetMapping(value="/retrieveAll")
	public List<AppUserData> retrieveAllDoctors(){
		return appUserService.retrieveAllDoctors(true);
	}
	
}
