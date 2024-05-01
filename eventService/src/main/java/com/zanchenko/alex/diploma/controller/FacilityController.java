package com.zanchenko.alex.diploma.controller;

import com.zanchenko.alex.diploma.domain.Facility;
import com.zanchenko.alex.diploma.domain.network.FacilityResponse;
import com.zanchenko.alex.diploma.domain.network.Response;
import com.zanchenko.alex.diploma.dto.EventDTO;
import com.zanchenko.alex.diploma.dto.FacilityDTO;
import com.zanchenko.alex.diploma.service.FacilityService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

//    @PostMapping("/new")
//    public ResponseEntity<Response> createFacility(@Valid @RequestBody FacilityDTO facilityDTO,
//                                                   BindingResult result){
//        Response response = new Response();
//
//        if(result.hasErrors()) {
//            Map<String, String> errors = new HashMap<>();
//            for(FieldError error: result.getFieldErrors()) {
//                errors.put(error.getField(), error.getDefaultMessage());
//            }
//            response.setErrors(errors);
//            return ResponseEntity.badRequest().body(response);
//        }
//        Facility savedFacility = mapToFacility(facilityService.saveFacility(facilityDTO));
////        return ResponseEntity.status(HttpStatus.CREATED).body(savedFacility);
//
//        response.setMessage("Facility has been successfully created!");
//        return ResponseEntity.ok(response);
//
//    }

    @PostMapping("/new")
    public ResponseEntity<FacilityResponse> createFacility(@RequestBody FacilityDTO facilityDTO) {
        // Perform manual validation
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<FacilityDTO>> violations = validator.validate(facilityDTO);

        FacilityResponse response = new FacilityResponse();

        if (!violations.isEmpty()) {
            List<String> errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());

            response.setErrors(errorMessages);
            return ResponseEntity.badRequest().body(response);
            //return ResponseEntity.badRequest().body(errorMessages);
        }

        // If there are no validation errors, proceed with saving the facility
//        Facility savedFacility = mapToFacility(facilityService.saveFacility(facilityDTO));
        FacilityDTO savedFacility = facilityService.saveFacility(facilityDTO);

        response.setMessage("Facility has been successfully created!");
        response.setFacilityDTO(savedFacility);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

//        return ResponseEntity.ok(response);
    }

    @GetMapping("/{facilityID}/edit")
    @ResponseBody
    public FacilityDTO updateFacilityForm(@PathVariable("facilityID") Long facilityID){
        return facilityService.getFacilityById(facilityID);
    }

//    @PutMapping("/{facilityID}/edit")
//    @ResponseBody
//    public ResponseEntity<Response> updateFacility(@PathVariable("facilityID") Long facilityID,
//                                                   @Valid @RequestBody FacilityDTO facilityDTO,
//                                                   BindingResult result){
//        Response response = new Response();
//
//        if(result.hasErrors()) {
//            Map<String, String> errors = new HashMap<>();
//            for (FieldError error: result.getFieldErrors()){
//                errors.put(error.getField(), error.getDefaultMessage());
//            }
//            response.setErrors(errors);
//            return ResponseEntity.badRequest().body(response);
//        }
//        facilityService.updateFacility(facilityID, facilityDTO);
//        response.setMessage("Facility has been successfully updated!");
//        return ResponseEntity.ok(response);
//    }


    @PutMapping("/{facilityID}/edit")
    @ResponseBody
    public ResponseEntity<Response> updateFacility(@PathVariable("facilityID") Long facilityID,
                                                   @RequestBody FacilityDTO facilityDTO,
                                                   BindingResult result){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<FacilityDTO>> violations = validator.validate(facilityDTO);

        Response response = new Response();

        if (!violations.isEmpty()) {
            List<String> errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());

            response.setErrors(errorMessages);
            return ResponseEntity.badRequest().body(response);
            //return ResponseEntity.badRequest().body(errorMessages);
        }
        facilityService.updateFacility(facilityID, facilityDTO);
        response.setMessage("Facility has been successfully updated!");
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{facilityID}/delete")
    public void deleteFacility(@PathVariable Long facilityID){
        facilityService.deleteFacility(facilityID);
    }

    @GetMapping("/search")
    public List<FacilityDTO> searchFacility(@RequestParam(value = "query") String query){
        return facilityService.searchFacilities(query);
    }

}
