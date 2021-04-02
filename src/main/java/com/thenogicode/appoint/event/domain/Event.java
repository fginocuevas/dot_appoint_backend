package com.thenogicode.appoint.event.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.thenogicode.appoint.appuser.domain.DoctorAppUser;
import com.thenogicode.appoint.appuser.domain.SchedulerAppUser;
import com.thenogicode.appoint.core.domain.AbstractPersistableCustom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "event")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event extends AbstractPersistableCustom<Long>{
	
	@Column(name = "event_date", nullable= false)
	private LocalDate eventDate;
	
	@Column(name = "start_time", nullable= false)
	private LocalTime startTime;
	
	@Column(name = "end_time", nullable= false)
	private LocalTime endTime;
	
	@ManyToOne
	@JoinColumn(name="assigned_to", nullable= false)
	private DoctorAppUser assignedTo;
	
	@Column(name = "creation_date_time", nullable= false)
	private LocalDateTime creationDateTime;
	
	@ManyToOne
	@JoinColumn(name="created_by", nullable= false)
	private SchedulerAppUser createdBy;
	
	@Builder.Default
	@Column(name = "is_accepted", nullable= false, columnDefinition = "BOOLEAN DEFAULT FALSE")
	private boolean accepted = false;
	
	@Column(name = "accepted_date_time")
	private LocalDateTime acceptedDateTime;
	
}
