package com.zanchenko.alex.diploma.controller.authentication.eventSevice;


import com.github.dockerjava.api.model.Bind;
import com.zanchenko.alex.diploma.client.EventClient;
import com.zanchenko.alex.diploma.domain.events.EventDTO;
import com.zanchenko.alex.diploma.domain.events.FacilityDTO;
import com.zanchenko.alex.diploma.domain.network.EventResponse;
import com.zanchenko.alex.diploma.domain.network.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventClient eventClient;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> getEvents(){
        return eventClient.getEvents();
    }
    @GetMapping("/{eventID}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Object getEventDetails(@PathVariable("eventID") Long eventID){
        return eventClient.getEventDetails(eventID);
    }

    @PostMapping("/new")
    public ResponseEntity<EventResponse> createEvent(@Valid @RequestBody EventDTO eventDTO,
                                                     BindingResult result){
        EventResponse response = new EventResponse();

        if(result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error: result.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            response.setErrors(errors);
            return ResponseEntity.badRequest().body(response);
        }
        return eventClient.createEvent(eventDTO);
    }

    @GetMapping("/{eventID}/edit")
    @ResponseBody
    public Object updateEventForm(@PathVariable("eventID") Long eventID){
        return eventClient.updateEventForm(eventID);
    }

    @PutMapping("/{eventID}/edit")
    public ResponseEntity<Response> updateEvent(@PathVariable("eventID") Long eventID,
                                                @Valid @RequestBody EventDTO eventDTO,
                                                BindingResult result){
        Response response = new Response();

        if(result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error: result.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            response.setErrors(errors);
            return ResponseEntity.badRequest().body(response);
        }
        return eventClient.updateEvent(eventID, eventDTO);
    }

    @DeleteMapping("/{eventID}/delete")
    @ResponseBody
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteEvent(@PathVariable Long eventID) {
        try {
            eventClient.deleteEvent(eventID);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete event: " + e.getMessage());
        }
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Object> searchEvents(@RequestParam(value = "query") String query){
        return eventClient.searchEvent(query);
    }

//    @GetMapping // work
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
//    public String getResponse(){
//        String url = "http:localhost:8082/events";
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(url) // by default get request
//                .build();
//        try (Response response = client.newCall(request).execute()){
//            if (!response.isSuccessful()) {
//                throw new IOException("Unexpected response code: " + response);
//            }
//            return response.body().string();
//        } catch (IOException e){
//            throw new RuntimeException(e);
//        }
//
//       // return "Event Content."; // work
//    }

}
