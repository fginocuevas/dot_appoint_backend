package com.thenogicode.appoint.appuser.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thenogicode.appoint.appuser.domain.AppUser;
import com.thenogicode.appoint.appuser.domain.DoctorAppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

	List<DoctorAppUser> findAllByRoleType(Integer doctor);
	Optional<AppUser> findByUsername(String username);

}
