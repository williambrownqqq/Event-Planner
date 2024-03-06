package com.zanchenko.alex.diploma.service.impl;

import com.zanchenko.alex.diploma.domain.Event;
import com.zanchenko.alex.diploma.dto.EventDTO;
import com.zanchenko.alex.diploma.exception.BadRequestException;
import com.zanchenko.alex.diploma.mapper.EventMapper;
import com.zanchenko.alex.diploma.repository.EventRepository;
import com.zanchenko.alex.diploma.repository.TaskRepository;
import com.zanchenko.alex.diploma.service.EventService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.zanchenko.alex.diploma.mapper.EventMapper.mapToEvent;
import static com.zanchenko.alex.diploma.mapper.EventMapper.mapToEventDTO;

@Service
@Transactional
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final TaskRepository taskRepository;

    @Override
    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(EventMapper::mapToEventDTO)
                .toList();
    }

    @Override
    public EventDTO saveEvent(EventDTO eventDTO) {
        eventRepository.save(mapToEvent(eventDTO));
        return eventDTO;
    }

    @Override
    public EventDTO getEventByID(Long eventID) {
        Event event = eventRepository.findById(eventID)
                .orElseThrow(() -> new EntityNotFoundException("No event with id: " + eventID));
        return mapToEventDTO(event);
    }

    @Override
    public void updateEvent(Long eventID, EventDTO eventDTO) {
        if(eventRepository.existsById(eventID)){
            throw new BadRequestException("No team with id: " + eventID);
        }
        Event event = mapToEvent(eventDTO);
        event.setId(eventDTO.getId());
        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long eventID) {
        taskRepository.deleteByEventId(eventID);
        eventRepository.deleteById(eventID);
    }

    @Override
    public List<EventDTO> searchEvents(String query) {
        List<Event> events = eventRepository.findEvent(query);
        return events.stream()
                .map(EventMapper::mapToEventDTO)
                .toList();
    }
}
