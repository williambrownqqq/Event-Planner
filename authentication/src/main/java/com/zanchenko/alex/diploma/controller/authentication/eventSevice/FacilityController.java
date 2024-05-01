package com.zanchenko.alex.diploma.controller.authentication.eventSevice;

import com.zanchenko.alex.diploma.client.FacilityClient;
import com.zanchenko.alex.diploma.domain.events.EventDTO;
import com.zanchenko.alex.diploma.domain.events.FacilityDTO;
import com.zanchenko.alex.diploma.domain.network.FacilityResponse;
import com.zanchenko.alex.diploma.domain.network.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/facilities")
@RequiredArgsConstructor
public class FacilityController {

    private final FacilityClient facilityClient;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> getResponse(){
        return facilityClient.getFacilities();
    }

    @GetMapping("/{facilityID}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Object getFacilityDetails(@PathVariable("facilityID") Long facilityID){
        return facilityClient.getFacilityDetails(facilityID);
    }

    @GetMapping("/{facilityID}/events")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<EventDTO> getFacilityEvents(@PathVariable("facilityID") Long facilityID){
        return facilityClient.getFacilityEvents(facilityID);
    }

    @PostMapping("/new")
    public ResponseEntity<FacilityResponse> createFacility(@Valid @RequestBody FacilityDTO facilityDTO,
                                                           BindingResult result){
        FacilityResponse response = new FacilityResponse();

        if(result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error: result.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            response.setErrors(errors);
            return ResponseEntity.badRequest().body(response);
        }
        return facilityClient.createFacility(facilityDTO);
    }

    @GetMapping("/{facilityID}/edit")
    @ResponseBody
    public Object updateFacilityForm(@PathVariable("facilityID") Long facilityID){
        return facilityClient.updateFacilityForm(facilityID);
    }

    @PutMapping("/{facilityID}/edit")
    public ResponseEntity<Response> updateFacility(@PathVariable("facilityID") Long facilityID,
                                                   @Valid @RequestBody FacilityDTO facilityDTO,
                                                   BindingResult result){
        Response response = new Response();

        if(result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error: result.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            response.setErrors(errors);
            return ResponseEntity.badRequest().body(response);
        }
        return facilityClient.updateFacility(facilityID, facilityDTO);
    }

    @DeleteMapping("/{facilityID}/delete")
    @ResponseBody
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteFacility(@PathVariable Long facilityID) {
        try {
            facilityClient.deleteFacility(facilityID);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete facility: " + e.getMessage());
        }
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Object> searchFacilities(@RequestParam(value = "query") String query){
        return facilityClient.searchFacility(query);
    }

    //    @GetMapping
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
//    public String getFacilities(){
//        String url = "http:localhost:8082/facilities";
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(url) // by default get request
//                .build();
//        try (Response response = client.newCall(request).execute()){
//            if (!response.isSuccessful()) {
//                throw new IOException("Unexpected response code: " + response);
//            }
//            return response.body().string();
//        } catch (IOException e){
//            throw new RuntimeException(e);
//        }
//    }


}
