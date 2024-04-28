package com.zanchenko.alex.diploma.controller;


import com.zanchenko.alex.diploma.domain.network.Response;
import com.zanchenko.alex.diploma.dto.AssignExecutorsDTO;
import com.zanchenko.alex.diploma.service.EventAssignmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
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

        private final EventAssignmentService assignmentService;

        @PutMapping("/{eventID}/assign")
        public ResponseEntity<Response> assignExecutorsToEvent(@PathVariable("eventID") Long eventID,
                                                               @RequestBody AssignExecutorsDTO dto){
            List<Long> userIDs = dto.getUserIDs();
            Response response = new Response();
            assignmentService.assignExecutors(userIDs, eventID);
            response.setMessage("Executors has been successfully added!");
            return ResponseEntity.ok(response);
        }
        @PutMapping("/{eventID}/self-assign/{userID}")
        public ResponseEntity<Response> selfAssignToEvent(@PathVariable("eventID") Long eventID,
                                                          @PathVariable("userID") Long userID) {
            Response response = new Response();
            assignmentService.selfAssignExecute(userID, eventID);
            response.setMessage("Self assign has been successfully done!");
            return ResponseEntity.ok(response);
        }

        @PutMapping("/{eventID}/self-unassign/{userID}")
        public ResponseEntity<Response> selfUnAssignToEvent(@PathVariable("eventID") Long eventID,
                                                            @PathVariable("userID") Long userID) {
            Response response = new Response();
            assignmentService.selfUnAssignExecute(userID, eventID);
            response.setMessage("Self unassign has been successfully done!");
            return ResponseEntity.ok(response);
        }
    }
