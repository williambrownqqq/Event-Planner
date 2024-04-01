package com.zanchenko.alex.diploma.client;

import com.zanchenko.alex.diploma.domain.events.EventDTO;
import com.zanchenko.alex.diploma.domain.events.FacilityDTO;
import feign.RequestLine;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.CollectionFormat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@CollectionFormat(feign.CollectionFormat.CSV)
@FeignClient(name = "event-client", url = "${http.event.baseUrl}")
public interface EventClient {

    @GetMapping
    ResponseEntity<Map<String, Object>> getEvents();

    @GetMapping("/{eventID}")
    Object getEventDetails(@PathVariable(value = "eventID") Long eventID);

    @PostMapping("/new")
    ResponseEntity<?> createEvent(@Valid @RequestBody EventDTO eventDTO);

    @DeleteMapping("/{eventID}")
    void deleteEvent(@PathVariable(value = "eventID") Long eventID);

    @GetMapping("/search")
    List<Object> searchEvent(@RequestParam(value = "query") String query);
}
