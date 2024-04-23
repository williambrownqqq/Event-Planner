package com.zanchenko.alex.diploma.client;


import com.zanchenko.alex.diploma.domain.events.EventDTO;
import com.zanchenko.alex.diploma.domain.network.Response;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.CollectionFormat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Map;

@CollectionFormat(feign.CollectionFormat.CSV)
@FeignClient(name = "management-client", url = "${http.management.baseUrl}")
public interface ManagementClient {

    @GetMapping("/get-users")
    ResponseEntity<List<Object>> getUsers();

    @GetMapping("/promote/{userID}")
    ResponseEntity<Object> promoteUser(@PathVariable("userID") Long userId);

    @GetMapping("/demote/{userID}")
    ResponseEntity<Object> demoteUser(@PathVariable("userID") Long userID);

}
