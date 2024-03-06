package com.zanchenko.alex.diploma.service;

import com.zanchenko.alex.diploma.domain.Facility;
import com.zanchenko.alex.diploma.dto.EventDTO;
import com.zanchenko.alex.diploma.dto.FacilityDTO;

import java.util.List;

public interface FacilityService {

    List<FacilityDTO> getAllFacilities();

    FacilityDTO saveFacility(FacilityDTO facilityDTO);

    FacilityDTO getFacilityById(Long facilityDTO);

    void updateFacility(Long facilityID, FacilityDTO facilityDTO);

    void deleteFacility(Long facilityID);

    List<FacilityDTO> searchFacilities(String query);

    List<EventDTO> findAllEventsByFacility(Long facilityID);

}
