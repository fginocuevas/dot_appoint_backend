package com.thenogicode.appoint.scheduler.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thenogicode.appoint.appuser.data.AppUserData;
import com.thenogicode.appoint.appuser.domain.SchedulerAppUser;
import com.thenogicode.appoint.appuser.service.AppUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("scheduler")
@RequiredArgsConstructor
public class SchedulerAppUserApiResource {
	
	private final AppUserService appUserService;
	
	@PostMapping(value="/create",
			consumes= MediaType.APPLICATION_JSON_VALUE,
			produces= MediaType.APPLICATION_JSON_VALUE)
	public AppUserData createNewScheduler(@RequestBody SchedulerAppUser appUser) {
		return appUserService.createNewScheduler(appUser);
	}

}
