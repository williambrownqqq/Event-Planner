package com.zanchenko.alex.diploma.controller.authentication;


import com.zanchenko.alex.diploma.client.EventClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/events")
@RequiredArgsConstructor
public class EventController {

    private final EventClient eventClient;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String getResponse(){
        return eventClient.getEvents().toString();
    }
}
