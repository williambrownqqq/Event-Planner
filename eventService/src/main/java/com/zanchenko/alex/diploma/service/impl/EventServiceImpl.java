package com.zanchenko.alex.diploma.service.impl;

import com.zanchenko.alex.diploma.domain.Event;
import com.zanchenko.alex.diploma.domain.Facility;
import com.zanchenko.alex.diploma.domain.Task;
import com.zanchenko.alex.diploma.dto.EventDTO;
import com.zanchenko.alex.diploma.exception.BadRequestException;
import com.zanchenko.alex.diploma.mapper.EventMapper;
import com.zanchenko.alex.diploma.mapper.TaskMapper;
import com.zanchenko.alex.diploma.repository.EventRepository;
import com.zanchenko.alex.diploma.repository.TaskRepository;
import com.zanchenko.alex.diploma.service.EventService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.zanchenko.alex.diploma.mapper.EventMapper.mapToEvent;
import static com.zanchenko.alex.diploma.mapper.EventMapper.mapToEventDTO;
import static com.zanchenko.alex.diploma.mapper.TaskMapper.mapToTask;

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
    @Transactional
    public Event saveEvent(EventDTO eventDTO) {
        Event event = mapToEvent(eventDTO);
        eventRepository.save(event);

        List<Task> tasks = event.getTasks();
        List<Task> savedTasks = tasks.stream()
                .peek(task -> task.setEvent(event))
                .map(taskRepository::save)
                .toList();
        return event;
    }

    @Override
    public EventDTO getEventByID(Long eventID) {
        Event event = eventRepository.findById(eventID)
                .orElseThrow(() -> new EntityNotFoundException("No event with id: " + eventID));
        return mapToEventDTO(event);
    }

    @Override
    public void updateEvent(Long eventID, EventDTO eventDTO) {
        if(!eventRepository.existsById(eventID)){
            throw new EntityNotFoundException("No facility with id: " + eventID);
        }

        Event event = mapToEvent(eventDTO); // map event without tasks, id
        event.setId(eventDTO.getId()); // set id to event

        List<Task> tasks = eventDTO.getTasks().stream() // map all tasks but without event
//                .map(TaskMapper::mapToTask)
                .map(taskDTO -> {
                    Task task = mapToTask(taskDTO);
                    task.setDeadline(event.getClosedEventDate());
                    task.setEvent(event);
                    return task;
                })
                .toList();

        event.setTasks(tasks);
        eventRepository.save(event);
        taskRepository.deleteByEventId(eventID);
        taskRepository.saveAll(tasks);

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
