package com.zanchenko.alex.diploma.controller;

import com.zanchenko.alex.diploma.domain.enumeration.EventState;
import com.zanchenko.alex.diploma.domain.enumeration.EventType;
import com.zanchenko.alex.diploma.domain.enumeration.Urgency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Validated
@CrossOrigin
@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventDataController {

    @GetMapping("/event-state")
    public ResponseEntity<List<String>> getEventState(){
        return ResponseEntity.ok(Arrays.stream(EventState.values())
                .map(EventState::getDisplayName)
                .toList());
    }


    @GetMapping("/urgencies")
    public ResponseEntity<List<String>> getUrgencies(){
        return ResponseEntity.ok(Arrays.stream(Urgency.values())
                .map(Urgency::getDisplayName)
                .toList());
    }

    @GetMapping("/event-types")
    public ResponseEntity<List<String>> getEventType(){
        return ResponseEntity.ok(Arrays.stream(EventType.values())
                .map(EventType::getDisplayName)
                .toList());
    }
}
