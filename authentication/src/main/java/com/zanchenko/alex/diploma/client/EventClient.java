package com.zanchenko.alex.diploma.client;

import org.springframework.cloud.openfeign.CollectionFormat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@CollectionFormat(feign.CollectionFormat.CSV)
@FeignClient(name = "event-client", url = "${http.event.baseUrl}")
public interface EventClient {

    @GetMapping("/events")
    ResponseEntity<Map<String, Object>> getEvents();

}