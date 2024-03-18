package com.zanchenko.alex.diploma.controller.authentication.eventSevice;


import com.zanchenko.alex.diploma.client.EventClient;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
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

    @DeleteMapping("/{facilityID}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public void deleteEvent(@PathVariable Long eventID){
        eventClient.deleteEvent(eventID);
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
