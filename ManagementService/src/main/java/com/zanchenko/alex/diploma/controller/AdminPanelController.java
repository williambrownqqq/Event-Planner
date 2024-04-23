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
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(adminBoardService.getUsers());
    }

    @GetMapping("/promote/{userID}")
    public void promote(@PathVariable("userID") Long userID) {
        adminBoardService.promote(userID);
    }


    @GetMapping("/demote/{userID}")
    public void demote(@PathVariable("userID") Long userID) {
        adminBoardService.demote(userID);
    }
}
