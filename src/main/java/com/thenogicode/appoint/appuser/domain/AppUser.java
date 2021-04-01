package com.thenogicode.appoint.appuser.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.thenogicode.appoint.core.domain.AbstractPersistableCustom;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "appuser",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = { "username" }, name = "username"),
				@UniqueConstraint(columnNames = { "email" }, name = "email")})
public class AppUser extends AbstractPersistableCustom<Long> {
	
	@Column(name = "email", unique= true, nullable = false, length = 100)
    private String email;
	
	@Column(name = "username", nullable = false, length = 100)
	private String username;
	
	@Column(name = "firstname", nullable = false, length = 100)
	private String firstname;
	
	 @Column(name = "lastname", nullable = false, length = 100)
	private String lastname;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "status", nullable = false)
	private Integer status = 1;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "role_id")
	private Role role;

}
