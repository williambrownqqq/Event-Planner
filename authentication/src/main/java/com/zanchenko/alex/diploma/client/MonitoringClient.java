package com.zanchenko.alex.diploma.client;

import com.zanchenko.alex.diploma.dto.monitoring.ElectricityGeneration;
import org.springframework.cloud.openfeign.CollectionFormat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@CollectionFormat(feign.CollectionFormat.CSV)
@FeignClient(name = "monitoring-client", url = "${http.monitoring.baseUrl}")
public interface MonitoringClient {

    @GetMapping("/get-data")
    ResponseEntity<List<ElectricityGeneration>> getData();
}
