package com.zanchenko.alex.diploma.mapper;

import com.zanchenko.alex.diploma.domain.Event;
import com.zanchenko.alex.diploma.domain.Facility;
import com.zanchenko.alex.diploma.dto.EventDTO;
import com.zanchenko.alex.diploma.dto.FacilityDTO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.zanchenko.alex.diploma.mapper.EventMapper.mapTOEventForTask;

public class FacilityMapper {

    public static FacilityDTO mapToFacilityDTO(Facility facility){
        Set<Event> events = new HashSet<>(facility.getEvents());
        Set<EventDTO> eventDTOs = events.stream()
                .map(EventMapper::mapToEventDTOforFacility)
                .collect(Collectors.toSet());
        return FacilityDTO.builder()
                .id(facility.getId())
                .facilityTitle(facility.getFacilityTitle())
                .photoURL(facility.getPhotoURL())
                .description(facility.getDescription())
                .events(eventDTOs)
                .build();
    }

    public static Facility mapToFacility(FacilityDTO facilityDTO){
        List<EventDTO> eventDTOs = facilityDTO.getEvents().stream().toList();
        Set<Event> events = eventDTOs.stream()
                .map(EventMapper::mapTOEventForTask)
                .collect(Collectors.toSet());
        return Facility.builder()
                .facilityTitle(facilityDTO.getFacilityTitle())
                .photoURL(facilityDTO.getPhotoURL())
                .description(facilityDTO.getDescription())
                .events(events)
                .build();
    }
}
