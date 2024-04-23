package com.zanchenko.alex.diploma.controller.authentication.managementService;


import com.zanchenko.alex.diploma.client.ManagementClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin-board")
@RequiredArgsConstructor
public class ManagementController {

    private final ManagementClient managementClient;

    @GetMapping("/get-users")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Object>> getUsers(){
        return managementClient.getUsers();
    }

    @GetMapping("/promote/{userID}") // post and delete throw error: https://stackoverflow.com/questions/26881296/spring-security-oauth2-full-authentication-is-required-to-access-this-resource
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Object> promoteUser(@PathVariable("userID") Long userId){
        return managementClient.promoteUser(userId);
    }

    @GetMapping("/demote/{userID}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Object> demoteUser(@PathVariable("userID") Long userId){
        return managementClient.demoteUser(userId);
    }
}
