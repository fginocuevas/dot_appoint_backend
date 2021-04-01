package com.thenogicode.appoint.appuser.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.thenogicode.appoint.event.domain.Event;

import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("2")
@Getter
@Setter
public class DoctorAppUser extends AppUser{
	
	@OneToMany(cascade = CascadeType.ALL, 
			mappedBy = "assignedTo", 
			orphanRemoval = true, 
			fetch=FetchType.LAZY)
	private List<Event> events;
	
}
