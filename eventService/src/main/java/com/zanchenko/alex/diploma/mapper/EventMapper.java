package com.zanchenko.alex.diploma.mapper;

import com.zanchenko.alex.diploma.domain.Event;
import com.zanchenko.alex.diploma.domain.Facility;
import com.zanchenko.alex.diploma.domain.Task;
import com.zanchenko.alex.diploma.domain.enumeration.EventType;
import com.zanchenko.alex.diploma.dto.EventDTO;
import com.zanchenko.alex.diploma.dto.TaskDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.zanchenko.alex.diploma.mapper.FacilityMapper.mapToFacility;
import static com.zanchenko.alex.diploma.mapper.FacilityMapper.mapToFacilityDTO;
import static com.zanchenko.alex.diploma.mapper.TaskMapper.mapToTaskDTO;
import static java.util.stream.Collectors.toList;

public class EventMapper {

    public static EventDTO mapToEventDTO(Event event){

        return EventDTO.builder()
                .id(event.getId())
                .eventTitle(event.getEventTitle())
                .photoURL(event.getPhotoURL())
                .eventDescription(event.getEventDescription())
                .eventLocation(event.getEventLocation())
                .facilityDTO(mapToFacilityDTO(event.getFacility()))
                .eventDate(event.getEventDate())
                .urgency(event.getUrgency())
                .eventType(event.getEventType())
                .eventState(event.getEventState())
                .openEventDate(event.getOpenEventDate())
                .closedEventDate(event.getClosedEventDate())
                .tasks(mapToTaskDTO(event.getTasks()))
                .build();
    }

    public static List<EventDTO> mapToEventDTO(List<Event> events){
        return events.stream()
                .map(EventMapper::mapToEventDTO)
                .toList();
    }

    public static EventDTO mapToEventDTOforFacility(Event event){

        return EventDTO.builder()
                .id(event.getId())
                .eventTitle(event.getEventTitle())
                .eventDescription(event.getEventDescription())
                .eventLocation(event.getEventLocation())
                .photoURL(event.getPhotoURL())
                .build();
    }

    public static EventDTO mapToEventDTOforTask(Event event){

        return EventDTO.builder()
                .id(event.getId())
                .eventTitle(event.getEventTitle())
                .eventDescription(event.getEventDescription())
                .eventLocation(event.getEventLocation())
                .photoURL(event.getPhotoURL())
                .build();
    }

    public static Event mapToEvent(EventDTO eventDTO){
        List<Task> tasks = new ArrayList<>();
        if (eventDTO.getTasks() != null) {
            tasks = eventDTO.getTasks().stream()
                    .map(TaskMapper::mapToTask)
                    .toList();
        }
        Facility facility = mapToFacility(eventDTO.getFacilityDTO());
        facility.setId(eventDTO.getFacilityDTO().getId());
//        List<TaskDTO> taskDTOs = eventDTO.getTasks().stream().toList();
//        List<Task> tasks = taskDTOs.stream()
//                .map(TaskMapper::mapToTask)
//                .toList();
        return Event.builder()
                .eventTitle(eventDTO.getEventTitle())
                .eventDescription(eventDTO.getEventDescription())
                .photoURL(eventDTO.getPhotoURL())
                .eventLocation(eventDTO.getEventLocation())
                .facility(facility)
                .eventDate(eventDTO.getEventDate())
                .urgency(eventDTO.getUrgency())
                .eventType(eventDTO.getEventType())
                .eventState(eventDTO.getEventState())
                .openEventDate(eventDTO.getOpenEventDate())
                .closedEventDate(eventDTO.getClosedEventDate())
                .tasks(tasks)
                .build();

    }

    public static Event mapTOEventForTask(EventDTO eventDTO){
        List<TaskDTO> taskDTOs = eventDTO.getTasks().stream().toList();
        List<Task> tasks = taskDTOs.stream()
                .map(TaskMapper::mapToTask)
                .toList();
        return Event.builder()
                .eventTitle(eventDTO.getEventTitle())
                .eventDescription(eventDTO.getEventDescription())
                .photoURL(eventDTO.getPhotoURL())
                .eventLocation(eventDTO.getEventLocation())
                .eventDate(eventDTO.getEventDate())
                .urgency(eventDTO.getUrgency())
                .eventType(eventDTO.getEventType())
                .eventState(eventDTO.getEventState())
                .openEventDate(eventDTO.getOpenEventDate())
                .closedEventDate(eventDTO.getClosedEventDate())
                .tasks(tasks)
                .build();

    }


}
