package com.zanchenko.alex.diploma.controller.authentication.eventSevice;

import com.zanchenko.alex.diploma.client.EventClient;
import com.zanchenko.alex.diploma.client.EventDataClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventDataController {

    private final EventDataClient eventDataClient;

    @GetMapping("/event-state")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    ResponseEntity<List<String>> getEventState(){
        return eventDataClient.getEventState();
    }

    @GetMapping("/urgencies")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    ResponseEntity<List<String>> getUrgency(){
        return eventDataClient.getUrgency();
    }

    @GetMapping("/event-types")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    ResponseEntity<List<String>> getEventType(){
        return eventDataClient.getEventType();
    }

}
