package com.zanchenko.alex.diploma.controller;

import com.zanchenko.alex.diploma.dto.ElectricityGeneration;
import com.zanchenko.alex.diploma.service.MonitoringService;
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
    private final MonitoringService monitoringService;

    @GetMapping("/get-data")
    public ResponseEntity<List<ElectricityGeneration>> getData() {
        List<ElectricityGeneration> electricityGenerations = monitoringService.getData();

        return ResponseEntity.ok(electricityGenerations);
    }

}
