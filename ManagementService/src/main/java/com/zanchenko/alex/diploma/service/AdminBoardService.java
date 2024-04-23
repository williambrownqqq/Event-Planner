package com.zanchenko.alex.diploma.service;

import com.zanchenko.alex.diploma.domain.autentication.User;
import com.zanchenko.alex.diploma.dto.UserDTO;

import java.util.List;

public interface AdminBoardService {

    List<UserDTO> getUsers();

    void promote(Long userID);

    void demote(Long userID);

}
