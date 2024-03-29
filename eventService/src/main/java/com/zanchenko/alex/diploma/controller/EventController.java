package com.zanchenko.alex.diploma.controller;

import com.zanchenko.alex.diploma.domain.Event;
import com.zanchenko.alex.diploma.dto.EventDTO;
import com.zanchenko.alex.diploma.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

import static com.zanchenko.alex.diploma.mapper.EventMapper.mapToEvent;

@Slf4j
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllEvents(){
        List<EventDTO> events = eventService.getAllEvents();
        Map<String, Object> response = new HashMap<>();
        response.put("events", events);
      return ResponseEntity.ok(response);
    }

    @GetMapping("/{eventID}")
    public EventDTO getEventDetails(@PathVariable("eventID") Long eventID){
        return eventService.getEventByID(eventID);
    }

    @PostMapping("/new")
    public ResponseEntity<?> createEvent(@Valid @RequestBody EventDTO eventDTO,
                                                           BindingResult result){
        if(result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            for(FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        Event savedEvent = mapToEvent(eventService.saveEvent(eventDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent);
    }

    @GetMapping("/{eventID}/edit")
    @ResponseBody
    public EventDTO updateEventForm(@PathVariable("eventID") Long eventID){
        return eventService.getEventByID(eventID);
    }

    @PutMapping("/{eventID}/edit")
    public ResponseEntity<?> updateEventDetails(@PathVariable("eventID") Long eventID,
                                 @Valid @RequestBody EventDTO eventDTO,
                                 BindingResult result){
        if(result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        eventService.updateEvent(eventID, eventDTO);
        return ResponseEntity.ok("Event has been successfully updated");
    }

    @DeleteMapping("/{eventID}/delete")
    public void deleteEvent(@PathVariable Long eventID){
        eventService.deleteEvent(eventID);
    }

    @GetMapping("/search")
    public List<EventDTO> searchEvent(@RequestParam(value = "query") String query){
        return eventService.searchEvents(query);
    }

}
