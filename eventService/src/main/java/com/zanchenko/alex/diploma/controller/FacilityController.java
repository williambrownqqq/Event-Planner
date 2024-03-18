package com.zanchenko.alex.diploma.controller;

import com.zanchenko.alex.diploma.domain.Facility;
import com.zanchenko.alex.diploma.dto.EventDTO;
import com.zanchenko.alex.diploma.dto.FacilityDTO;
import com.zanchenko.alex.diploma.service.FacilityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zanchenko.alex.diploma.mapper.FacilityMapper.mapToFacility;


@Slf4j
@Validated
@CrossOrigin
@RestController
@RequestMapping("/facilities")
@RequiredArgsConstructor
public class FacilityController {

    private final FacilityService facilityService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllFacilities(){
        List<FacilityDTO> facilities = facilityService.getAllFacilities();
        Map<String, Object> response = new HashMap<>();
        response.put("facilities", facilities);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{facilityID}")
    public FacilityDTO getFacilityDetails(@PathVariable("facilityID") Long facilityID){
        return facilityService.getFacilityById(facilityID);
    }

    @GetMapping("/{facilityID}/events")
    public List<EventDTO> getEventsByFacility(@PathVariable("facilityID") Long facilityID){
        return facilityService.findAllEventsByFacility(facilityID);
    }

    @PostMapping("/new")
    public ResponseEntity<?> createFacility(@Valid @RequestBody FacilityDTO facilityDTO,
                                            BindingResult result){
        if(result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for(FieldError error: result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        Facility savedFacility = mapToFacility(facilityService.saveFacility(facilityDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFacility);

    }




    @GetMapping("/{facilityID}/edit")
    @ResponseBody
    public FacilityDTO updateFacilityForm(@PathVariable("facilityID") Long facilityID){
        return facilityService.getFacilityById(facilityID);
    }

    @PutMapping("/{facilityID}/edit")
    @ResponseBody
    public ResponseEntity<?> updateFacility(@PathVariable("facilityID") Long facilityID,
                                            @Valid @RequestBody FacilityDTO facilityDTO,
                                            BindingResult result){
        if(result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error: result.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        facilityService.updateFacility(facilityID, facilityDTO);
        return ResponseEntity.ok("Facility has been successfully updated!");
    }

    @DeleteMapping("/{facilityID}")
    public void deleteFacility(@PathVariable Long facilityID){
        facilityService.deleteFacility(facilityID);
    }

    @GetMapping("/search")
    public List<FacilityDTO> searchFacility(@RequestParam(value = "query") String query){
        return facilityService.searchFacilities(query);
    }
}
