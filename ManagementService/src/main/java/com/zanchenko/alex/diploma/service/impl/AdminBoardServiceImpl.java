package com.zanchenko.alex.diploma.service.impl;

import com.zanchenko.alex.diploma.dto.UserDTO;
import com.zanchenko.alex.diploma.mapper.UserMapper;
import com.zanchenko.alex.diploma.repository.UserRepository;
import com.zanchenko.alex.diploma.service.AdminBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminBoardServiceImpl implements AdminBoardService {

    private final UserRepository userRepository;
    @Override
    public List<UserDTO> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::mapToUserDTO)
                .toList();
    }

    @Override
    public void promoteToModerator(Long userID) {
        userRepository.promoteUserToModerator(userID, 2L);
    }

    @Override
    public void promoteToAdmin(Long userID) {
        userRepository.promoteUserToAdmin(userID, 3L);
    }

    @Override
    public void demoteToUser(Long userID) {
        userRepository.demoteUserToUser(userID, 2L);
    }

    @Override
    public void demoteToModerator(Long userID) {
        userRepository.demoteUserToModerator(userID, 3L);
    }
}
