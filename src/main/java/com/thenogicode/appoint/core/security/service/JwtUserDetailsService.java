package com.thenogicode.appoint.core.security.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.thenogicode.appoint.appuser.domain.AppUser;
import com.thenogicode.appoint.appuser.repository.AppUserRepository;
import com.thenogicode.appoint.core.exception.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
	
	private final AppUserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		AppUser user = repository.findByUsername(username)
				.orElseThrow(() -> new DataNotFoundException("appuser", username));
		
		if (user.getUsername().equals(username)) {
			return new User(username, "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
	
}
