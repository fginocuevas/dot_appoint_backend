package com.thenogicode.appoint.appuser.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.thenogicode.appoint.core.domain.AbstractPersistableCustom;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Getter
@Setter
@Table(name = "appuser",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = { "username" }, name = "username"),
				@UniqueConstraint(columnNames = { "email" }, name = "email")})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role_type_enum", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("0")
public abstract class AppUser extends AbstractPersistableCustom<Long> implements UserDetails{
	
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
	
	/**
	 * See {@link StatusTypeEnum}
	 */
	@Column(name = "status", nullable = false)
	private Integer status = 1;
	
	/**
	 * See {@link RoleTypeEnum}
	 */
	@Column(name = "role_type_enum", insertable = false, updatable = false)
    private Integer roleType;
	
	//TODO Implement date time properties such as createdBy in the future
	
	public String getUserDisplayName() {
		return this.getFirstname().concat(" ").concat(getLastname());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
