package com.zanchenko.alex.diploma.service;

import com.zanchenko.alex.diploma.domain.Event;
import com.zanchenko.alex.diploma.dto.EventDTO;

import java.util.List;

public interface EventService{

    List<EventDTO> getAllEvents();

    EventDTO saveEvent(EventDTO eventDTO);

    EventDTO getEventByID(Long EventID);

    void updateEvent(Long eventID, EventDTO eventDTO);

    void deleteEvent(Long eventID);

    List<EventDTO> searchEvents(String query);

}
