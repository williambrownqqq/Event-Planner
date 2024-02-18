package com.zanchenko.alex.diploma.mapper;

import com.zanchenko.alex.diploma.domain.Event;
import com.zanchenko.alex.diploma.domain.Task;
import com.zanchenko.alex.diploma.domain.enumeration.EventType;
import com.zanchenko.alex.diploma.dto.EventDTO;
import com.zanchenko.alex.diploma.dto.TaskDTO;

import java.util.List;
import java.util.stream.Collectors;

import static com.zanchenko.alex.diploma.mapper.FacilityMapper.mapToFacility;
import static com.zanchenko.alex.diploma.mapper.FacilityMapper.mapToFacilityDTO;
import static java.util.stream.Collectors.toList;

public class EventMapper {

    public static EventDTO mapToEventDTO(Event event){

        return EventDTO.builder()
                .id(event.getId())
                .eventTitle(event.getEventTitle())
                .eventDescription(event.getEventDescription())
                .eventLocation(event.getEventLocation())
                .photoURL(event.getPhotoURL())
                .facilityDTO(mapToFacilityDTO(event.getFacility()))
                .build();
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

    public static Event mapToEvent(EventDTO eventDTO){
        List<TaskDTO> taskDTOs = eventDTO.getTasks().stream().toList();
        List<Task> tasks = taskDTOs.stream()
                .map(TaskMapper::mapToTask)
                .toList();
        return Event.builder()
                .eventTitle(eventDTO.getEventTitle())
                .eventDescription(eventDTO.getEventDescription())
                .photoURL(eventDTO.getPhotoURL())
                .eventLocation(eventDTO.getEventLocation())
                .facility(mapToFacility(eventDTO.getFacilityDTO()))
                .eventDate(eventDTO.getEventDate())
                .urgency(eventDTO.getUrgency())
                .eventType(eventDTO.getEventType())
                .action(eventDTO.getAction())
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
                .action(eventDTO.getAction())
                .openEventDate(eventDTO.getOpenEventDate())
                .closedEventDate(eventDTO.getClosedEventDate())
                .tasks(tasks)
                .build();

    }


}
