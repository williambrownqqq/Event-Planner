package com.zanchenko.alex.diploma.service.impl;

import com.zanchenko.alex.diploma.dto.EventDTO;
import com.zanchenko.alex.diploma.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Override
    public List<EventDTO> findAllEvents() {
        return null;
    }

    @Override
    public EventDTO findEventByID(Long EventID) {
        return null;
    }

    @Override
    public EventDTO saveEvent(EventDTO eventDTO) {
        return null;
    }

    @Override
    public void deleteEvent(Long eventID) {

    }

    @Override
    public void editEvent(Long eventID, EventDTO eventDTO) {

    }

    @Override
    public List<EventDTO> searchEvents(String query) {
        return null;
    }
}
