package com.zanchenko.alex.diploma.repository;


import com.zanchenko.alex.diploma.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE e.eventTitle LIKE %:event_title%")
    List<Event> findEvent(@Param("event_title") String title);

}
