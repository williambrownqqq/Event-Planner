package com.zanchenko.alex.diploma.controller.authentication.eventSevice;

import com.zanchenko.alex.diploma.client.FacilityClient;
import com.zanchenko.alex.diploma.domain.events.EventDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @DeleteMapping("/{facilityID}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public void deleteFacility(@PathVariable Long facilityID){
        facilityClient.deleteFacility(facilityID);
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
