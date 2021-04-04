package com.thenogicode.appoint.appuser.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thenogicode.appoint.appuser.data.AppUserData;
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
	
	@GetMapping(value="/username/{username}", produces= MediaType.APPLICATION_JSON_VALUE)
	public AppUserData retrieveOne(@PathVariable final String username) {
		return appUserService.retrieveAppUserByUsername(username);
	}
	
}
