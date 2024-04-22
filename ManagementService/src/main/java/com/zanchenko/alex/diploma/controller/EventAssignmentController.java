package com.zanchenko.alex.diploma.controller;


import com.zanchenko.alex.diploma.domain.network.Response;
import com.zanchenko.alex.diploma.service.EventAssignmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    private final EventAssignmentService assignmentService;

    @PostMapping("/{eventID}/assign")
    public ResponseEntity<Response> assignExecutorsToEvent(@PathVariable Long eventID,
                                                           @RequestBody List<Long> userIDs,
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

        assignmentService.assignExecutors(userIDs, eventID);
        response.setMessage("Executors has been successfully added!");
        return ResponseEntity.ok(response);
    }
    @PostMapping("/{eventID}/self-assign")
    public ResponseEntity<Response> selfAssignToEvent(@PathVariable Long eventID,
                                                           @RequestBody Long userID,
                                                           BindingResult result) {
        Response response = new Response();
        if(result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            response.setErrors(errors);
            return ResponseEntity.badRequest().body(response);
        }

        assignmentService.selfAssignExecute(userID, eventID);
        response.setMessage("Self assign has been successfully done!");
        return ResponseEntity.ok(response);
    }
}
