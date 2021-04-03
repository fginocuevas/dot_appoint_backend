package com.thenogicode.appoint.appuser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thenogicode.appoint.appuser.domain.AppUser;
import com.thenogicode.appoint.appuser.domain.DoctorAppUser;
import com.thenogicode.appoint.appuser.domain.RoleTypeEnum;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

	List<DoctorAppUser> findAllByRoleType(Integer doctor);


}
