package com.zanchenko.alex.diploma.controller.authentication;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/events")
@PropertySource({
        "classpath:spring-host.properties"
})
public class EventController {
    @Value("${application.spring.host}")
    private String hostname;
    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String getResponse(){
        String url = hostname + "/events";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url) // by default get request
                .build();
        try (Response response = client.newCall(request).execute()){
            return response.body().string();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
