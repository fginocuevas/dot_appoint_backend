package com.thenogicode.appoint.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thenogicode.appoint.event.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long>{

}
