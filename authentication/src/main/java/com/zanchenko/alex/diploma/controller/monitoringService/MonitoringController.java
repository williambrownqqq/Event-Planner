package com.zanchenko.alex.diploma.controller.monitoringService;

import com.zanchenko.alex.diploma.client.AssignClient;
import com.zanchenko.alex.diploma.client.MonitoringClient;
import com.zanchenko.alex.diploma.dto.monitoring.ElectricityGeneration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/monitoring")
@RequiredArgsConstructor
public class MonitoringController {

    private final MonitoringClient monitoringClient;
    @GetMapping("/get-data")
    ResponseEntity<List<ElectricityGeneration>> getData(){
        return monitoringClient.getData();
    }

}
