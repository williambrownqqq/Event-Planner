package com.zanchenko.alex.diploma.service.impl;

import com.zanchenko.alex.diploma.domain.Event;
import com.zanchenko.alex.diploma.domain.Facility;
import com.zanchenko.alex.diploma.dto.EventDTO;
import com.zanchenko.alex.diploma.dto.FacilityDTO;
import com.zanchenko.alex.diploma.mapper.EventMapper;
import com.zanchenko.alex.diploma.mapper.FacilityMapper;
import com.zanchenko.alex.diploma.repository.EventRepository;
import com.zanchenko.alex.diploma.repository.FacilityRepository;
import com.zanchenko.alex.diploma.repository.TaskRepository;
import com.zanchenko.alex.diploma.service.FacilityService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.zanchenko.alex.diploma.mapper.EventMapper.mapToEventDTO;
import static com.zanchenko.alex.diploma.mapper.FacilityMapper.mapToFacility;
import static com.zanchenko.alex.diploma.mapper.FacilityMapper.mapToFacilityDTO;
import static java.util.stream.Collectors.toList;

@Service
@Transactional
@RequiredArgsConstructor
public class FacilityServiceImpl implements FacilityService {

    private final FacilityRepository facilityRepository;
    private final EventRepository eventRepository;
    private final TaskRepository taskRepository;

    @Override
    public List<FacilityDTO> getAllFacilities() {
        return facilityRepository.findAll().stream()
                .map(FacilityMapper::mapToFacilityDTO)
                .toList();
    }

    @Override
    public FacilityDTO saveFacility(FacilityDTO facilityDTO) {
        facilityRepository.save(mapToFacility(facilityDTO));

        return facilityDTO;
    }

    @Override
    public FacilityDTO getFacilityById(Long facilityID) {
        Facility facility = facilityRepository.findById(facilityID)
                .orElseThrow(() -> new EntityNotFoundException("No facility with id: " + facilityID));

        return mapToFacilityDTO(facility);
    }

    @Override
    public void updateFacility(Long facilityID, FacilityDTO facilityDTO) {
//        Facility facility = facilityRepository.findById(facilityID)
//                .orElseThrow(() -> new EntityNotFoundException("No facility with id: " + facilityID));
//
//        facility.setId(facilityDTO.getId());
//        facilityRepository.save(facility);

        if(!facilityRepository.existsById(facilityID)){
            throw new EntityNotFoundException("No facility with id: " + facilityID);
        }

        Facility facility = mapToFacility(facilityDTO);
        facility.setId(facilityDTO.getId());
        facilityRepository.save(facility);
    }

    @Override
    public void deleteFacility(Long facilityID) {
        if(facilityRepository.existsById(facilityID)){
            Facility facility = facilityRepository.findById(facilityID)
                    .orElseThrow(() -> new EntityNotFoundException("No facility with id: " + facilityID));
            // Delete all tasks associated with events of this facility
            facility.getEvents().forEach(event -> event.getTasks().forEach(taskRepository::delete));

            facility.getEvents().stream()   // Use a stream to delete all associated events
                    .filter(event -> event.getFacility().getId().equals(facilityID))
                    .forEach(eventRepository::delete); // This deletes each event using the eventRepository

            facilityRepository.deleteById(facilityID);   // Now you can safely delete the facility
        } else {
            throw new EntityNotFoundException("No facility with id: " + facilityID);
        }
    }

    @Override
    public List<FacilityDTO> searchFacilities(String query) {
        List<Facility> facilities = facilityRepository.findFacility(query);
        return facilities.stream()
                .map(FacilityMapper::mapToFacilityDTO)
                .toList();
    }

    @Override
    public List<EventDTO> findAllEventsByFacility(Long facilityID) {
        List<Event> events = eventRepository.findAll().stream()
                .filter(event -> event.getFacility().getId().equals(facilityID))
                .toList();
        return mapToEventDTO(events).stream().toList();
    }

}
