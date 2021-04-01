package com.thenogicode.appoint.appuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thenogicode.appoint.appuser.domain.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

}
