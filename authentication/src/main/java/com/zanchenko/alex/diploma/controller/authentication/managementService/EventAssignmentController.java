package com.zanchenko.alex.diploma.controller.authentication.managementService;


import com.zanchenko.alex.diploma.client.AssignClient;
import com.zanchenko.alex.diploma.domain.network.Response;
import com.zanchenko.alex.diploma.dto.AssignExecutorsDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Validated
@Transactional
@RestController
@RequestMapping("/assign-event")
@RequiredArgsConstructor
public class EventAssignmentController {

    private final AssignClient assignClient;

    @PutMapping(path = "/{eventID}/assign")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response> assignExecutorsToEvent(@PathVariable("eventID") Long eventID,
                                                           @RequestBody AssignExecutorsDTO dto,
                                                           BindingResult result){
        Response response = new Response();
        if(result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            response.setErrors(errors);
            return ResponseEntity.badRequest().body(response);
        }
        return assignClient.assignExecutorsToEvent(eventID, dto);
    }
    @PutMapping(path = "/{eventID}/self-assign/{userID}")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Response> selfAssignToEvent(@PathVariable("eventID") Long eventID,
                                                      @PathVariable("userID") Long userID) {
//        Response response = new Response();
//        if(result.hasErrors()){
//            Map<String, String> errors = new HashMap<>();
//            for (FieldError error : result.getFieldErrors()){
//                errors.put(error.getField(), error.getDefaultMessage());
//            }
//            response.setErrors(errors);
//            return ResponseEntity.badRequest().body(response);
//        }
        return assignClient.selfAssignToEvent(eventID, userID);
    }

    @PutMapping(path = "/{eventID}/self-unassign/{userID}")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Response> selfUnAssignToEvent(@PathVariable("eventID") Long eventID,
                                                        @PathVariable("userID") Long userID) {
//        Response response = new Response();
//        if(result.hasErrors()){
//            Map<String, String> errors = new HashMap<>();
//            for (FieldError error : result.getFieldErrors()){
//                errors.put(error.getField(), error.getDefaultMessage());
//            }
//            response.setErrors(errors);
//            return ResponseEntity.badRequest().body(response);
//        }
        return assignClient.selfUnAssignToEvent(eventID, userID);
    }
}
