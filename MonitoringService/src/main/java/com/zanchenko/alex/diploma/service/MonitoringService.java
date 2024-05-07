package com.zanchenko.alex.diploma.service;

import com.zanchenko.alex.diploma.dto.ElectricityGeneration;

import java.util.List;

public interface MonitoringService {

    List<ElectricityGeneration> getData();
}
