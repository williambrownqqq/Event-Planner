package com.zanchenko.alex.diploma.controller;

import com.zanchenko.alex.diploma.dto.EventDTO;
import com.zanchenko.alex.diploma.service.EventService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

@RestController
@CrossOrigin
@RequestMapping("/events")
public class EventController {

    private EventService eventService;
    private final Logger logger = LoggerFactory.getLogger(EventController.class);
    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getAllEvents(){
        List<EventDTO> eventDTOS = eventService.findAllEvents();
//        List<Event> events = eventDTOS.stream()
//                .map(EventMapper::mapToEvent)
//                .toList();
        Map<String, Object> response = new HashMap<>();
        response.put("events", eventDTOS);
      return ResponseEntity.ok(response);
    }

    @GetMapping("/{eventID}")
    public EventDTO getEventDetails(@PathVariable("eventID") Long eventID){
        return eventService.findEventByID(eventID);
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
        EventDTO savedEventDTO = eventService.saveEvent(eventDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEventDTO);
    }

    @GetMapping("/{eventID}/edit")
    @ResponseBody
    public EventDTO updateEventForm(@PathVariable("eventID") Long eventID){
        return eventService.findEventByID(eventID);
    }

    @PutMapping("/{eventID}/edit")
    public ResponseEntity<?> editEventDetails(@PathVariable("eventID") Long eventID,
                                 @Valid @RequestBody EventDTO eventDTO,
                                 BindingResult result){
        if(result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        eventService.editEvent(eventID, eventDTO);
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
