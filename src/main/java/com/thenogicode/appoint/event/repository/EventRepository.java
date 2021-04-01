package com.thenogicode.appoint.event.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thenogicode.appoint.event.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long>{

	@Query(value= "SELECT e FROM Event e "
			+ "WHERE e.eventDate >= :startDate AND e.eventDate <= :endDate "
			+ "ORDER BY e.eventDate DESC")
	List<Event>retrieveAllByRange(@Param("startDate") LocalDate startDate, 
			@Param("endDate")LocalDate endDate);
	
}
