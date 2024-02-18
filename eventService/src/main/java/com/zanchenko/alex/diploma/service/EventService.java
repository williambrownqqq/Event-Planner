package com.zanchenko.alex.diploma.service;

import com.zanchenko.alex.diploma.domain.Event;
import com.zanchenko.alex.diploma.dto.EventDTO;

import java.util.List;

public interface EventService{

    List<EventDTO> findAllEvents();

    EventDTO findEventByID(Long EventID);

    EventDTO saveEvent(EventDTO eventDTO);

    void deleteEvent(Long eventID);

    void editEvent(Long eventID, EventDTO eventDTO);

    List<EventDTO> searchEvents(String query);

}
