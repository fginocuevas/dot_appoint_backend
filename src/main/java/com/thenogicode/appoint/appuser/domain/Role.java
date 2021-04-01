package com.thenogicode.appoint.appuser.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.thenogicode.appoint.core.domain.AbstractPersistableCustom;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="roles")
public class Role extends AbstractPersistableCustom<Long>{
		
	@Column(name="role_type", nullable=false)
	private Integer roleType;
	
	@Column(name= "is_disabled", nullable= false)
	private Boolean disabled= false;

}
