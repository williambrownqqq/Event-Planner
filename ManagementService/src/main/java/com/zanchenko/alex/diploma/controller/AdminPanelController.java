package com.zanchenko.alex.diploma.controller;

import com.zanchenko.alex.diploma.dto.UserDTO;
import com.zanchenko.alex.diploma.service.AdminBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/admin-board")
@RequiredArgsConstructor
public class AdminPanelController {

    private final AdminBoardService adminBoardService;

    @GetMapping("/get-users")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> getUsers(){
        return ResponseEntity.ok(adminBoardService.getUsers());
    }

    @PostMapping("/promote-to-moderator/{userID}")
    public void promoteToModerator(@PathVariable("userID") Long userID){
        adminBoardService.promoteToModerator(userID);
    }

    @PostMapping("/promote-to-admin/{userID}")
    public void promoteToAdmin(@PathVariable("userID") Long userID){
        adminBoardService.promoteToAdmin(userID);
    }

    @DeleteMapping("/demote-to-user/{userID}")
    public void demoteToUser(@PathVariable("userID") Long userID){
        adminBoardService.demoteToUser(userID);
    }

    @DeleteMapping("/demote-to-moderator/{userID}")
    public void demoteToModerator(@PathVariable("userID") Long userID){
        adminBoardService.demoteToModerator(userID);
    }
}
