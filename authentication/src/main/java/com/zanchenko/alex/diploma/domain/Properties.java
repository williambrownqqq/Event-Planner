package com.zanchenko.alex.diploma.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * Class that contains properties from application.yml for getting data
 */
@Component
@Data
@RequiredArgsConstructor
public class Properties {
    @Value("${application.spring.host}")
    private String hostname;
}
