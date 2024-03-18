package com.zanchenko.alex.diploma.client;

import com.zanchenko.alex.diploma.domain.events.EventDTO;
import com.zanchenko.alex.diploma.domain.events.FacilityDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@FeignClient(name = "facility-client", url = "${http.facility.baseUrl}")
public interface FacilityClient {

    @GetMapping
    ResponseEntity<Map<String, Object>> getFacilities();

    @GetMapping("/{facilityID}")
    Object getFacilityDetails(@PathVariable(value = "facilityID") Long facilityID);

//    @PostMapping("/new")
//    ResponseEntity<?> createFacility(@Valid @RequestBody FacilityDTO facilityDTO, BindingResult result);

//    @GetMapping("/{facilityID}/events")
//    List<Object> getEventsByFacility(@PathVariable("facilityID") Long facilityID);
//
//    @GetMapping("/{facilityID}/edit")
//    Object updateFacilityForm(@PathVariable("facilityID") Long facilityID);
//
//    @PutMapping("/{facilityID}/edit")
//    ResponseEntity<?> updateFacility(@PathVariable("facilityID") Long facilityID,
//                                            @Valid @RequestBody Object facilityDTO,
//                                            BindingResult result);
//
    @DeleteMapping("/{facilityID}")
    void deleteFacility(@PathVariable(value = "facilityID") Long facilityID);

    @GetMapping("/search")
    List<Object> searchFacility(@RequestParam(value = "query") String query);

    @GetMapping("/{facilityID}/events")
    List<EventDTO> getFacilityEvents(@PathVariable("facilityID") Long facilityID);

}
