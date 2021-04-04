package com.thenogicode.appoint.event.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thenogicode.appoint.event.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long>{
	
	@Query(value= "SELECT e FROM Event e "
			+ " WHERE e.eventDate = :onDate "
			+ " ORDER BY e.eventDate DESC ")
	List<Event>retrieveAllByDate(@Param("onDate") LocalDate onDate);
	
	@Query(value= "SELECT e FROM Event e "
			+ " WHERE e.eventDate = :onDate AND e.accepted = true "
			+ " ORDER BY e.eventDate DESC ")
	List<Event> retrieveAcceptedByDate(@Param("onDate") LocalDate onDate);
	
	@Query(value= "SELECT e FROM Event e "
			+ " WHERE e.assignedTo.id = :doctorId "
			+ " AND (e.eventDate = :onDate) "
			+ " ORDER BY e.eventDate ASC")
	List<Event>retrieveAllByDateAndDoctorId(@Param("onDate") LocalDate onDate, 
			@Param("doctorId")Long doctorId);
	
	@Query(value= "SELECT e FROM Event e "
			+ " WHERE e.assignedTo.id = :doctorId "
			+ " AND (e.eventDate = :onDate) "
			+ " AND (e.accepted = true) "
			+ " ORDER BY e.eventDate ASC")
	List<Event>retrieveAcceptedByDateAndDoctorId(@Param("onDate") LocalDate onDate, 
			@Param("doctorId")Long doctorId);

	@Query(value= "SELECT e FROM Event e "
			+ " WHERE e.eventDate >= :startDate AND e.eventDate <= :endDate "
			+ " ORDER BY e.eventDate ASC ")
	List<Event>retrieveAllByRange(@Param("startDate") LocalDate startDate, 
			@Param("endDate")LocalDate endDate);
	
	@Query(value= "SELECT e FROM Event e "
			+ " WHERE e.assignedTo.id = :doctorId "
			+ " AND (e.eventDate >= :startDate AND e.eventDate <= :endDate) "
			+ " ORDER BY e.eventDate ASC")
	List<Event>retrieveAllByRangeAndDoctorId(@Param("startDate") LocalDate startDate, 
			@Param("endDate")LocalDate endDate, @Param("doctorId")Long doctorId);

	List<Event> findByOrderByEventDate();

	
	
}
