package com.zanchenko.alex.diploma.client;

import org.springframework.cloud.openfeign.CollectionFormat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@CollectionFormat(feign.CollectionFormat.CSV)
@FeignClient(name = "event-data-client", url = "${http.event.baseUrl}")
public interface EventDataClient {

    @GetMapping("/event-state")
    ResponseEntity<List<String>> getEventState();

    @GetMapping("/urgencies")
    ResponseEntity<List<String>> getUrgency();

    @GetMapping("/event-types")
    ResponseEntity<List<String>> getEventType();

}
