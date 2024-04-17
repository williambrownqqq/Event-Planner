package com.zanchenko.alex.diploma.service;

import com.zanchenko.alex.diploma.domain.autentication.User;
import com.zanchenko.alex.diploma.dto.UserDTO;

import java.util.List;

public interface AdminBoardService {

    List<UserDTO> getUsers();

    void promoteToModerator(Long userID);

    void promoteToAdmin(Long userID);

    void demoteToUser(Long userID);

    void demoteToModerator(Long userID);
}
