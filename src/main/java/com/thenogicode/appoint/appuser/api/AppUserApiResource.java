package com.thenogicode.appoint.appuser.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thenogicode.appoint.appuser.data.AppUserData;
import com.thenogicode.appoint.appuser.domain.AppUser;
import com.thenogicode.appoint.appuser.service.AppUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class AppUserApiResource {
	
	private final AppUserService appUserService;
	
	@GetMapping(value="/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public AppUserData retrieveOne(@PathVariable final Long id) {
		return appUserService.retrieveAppUser(id);
	}
	
	@PostMapping(value="/create",
			consumes= MediaType.APPLICATION_JSON_VALUE,
			produces= MediaType.APPLICATION_JSON_VALUE)
	public AppUserData createNewAppUser(@RequestBody AppUser appUser) {
		return appUserService.createNewAppUser(appUser);
	}

}
