package com.zanchenko.alex.diploma.client;

import com.zanchenko.alex.diploma.domain.network.Response;
import com.zanchenko.alex.diploma.dto.AssignExecutorsDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.CollectionFormat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@CollectionFormat(feign.CollectionFormat.CSV)
@FeignClient(name = "assign-client", url = "${http.assignment.baseUrl}")
public interface AssignClient {

    @PutMapping(path = "/{eventID}/assign", consumes = "application/json")
    ResponseEntity<Response> assignExecutorsToEvent(@PathVariable("eventID") Long eventID,
                                                    AssignExecutorsDTO dto); // i did here DTO instead of just List<Long>
    // because it will be type mismatch because spring can't understand what is the object is there
    // i got rid off @RequestBody annotation because it doesn't work with it.
    @PutMapping(path = "/{eventID}/self-assign/{userID}")
    ResponseEntity<Response> selfAssignToEvent(@PathVariable("eventID") Long eventID,
                                               @PathVariable("userID") Long userID);

    @PutMapping(path = "/{eventID}/self-unassign/{userID}")
    ResponseEntity<Response> selfUnAssignToEvent(@PathVariable("eventID") Long eventID,
                                                 @PathVariable("userID") Long userID);

}
