package com.zanchenko.alex.diploma.service.impl;

import com.zanchenko.alex.diploma.domain.autentication.ERole;
import com.zanchenko.alex.diploma.domain.autentication.Role;
import com.zanchenko.alex.diploma.domain.autentication.User;
import com.zanchenko.alex.diploma.dto.UserDTO;
import com.zanchenko.alex.diploma.mapper.UserMapper;
import com.zanchenko.alex.diploma.repository.UserRepository;
import com.zanchenko.alex.diploma.service.AdminBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
    public void promote(Long userID) {

        User user = userRepository.findById(userID).get();
        Set<Role> roles = user.getRoles();
        List<ERole> eroles = roles.stream()
                .map(Role::getName)
                .toList();

        if(eroles.contains(ERole.ROLE_USER) && eroles.contains(ERole.ROLE_MODERATOR) && !user.getRoles().contains(ERole.ROLE_ADMIN)){
            userRepository.promoteUserToAdmin(userID, 3L); // promote to admin
        }
        else if(eroles.contains(ERole.ROLE_USER) && !eroles.contains(ERole.ROLE_MODERATOR)){
            userRepository.promoteUserToModerator(userID, 2L); // promote to moderator
        }

    }


    @Override
    public void demote(Long userID) {

        User user = userRepository.findById(userID).get();
        Set<Role> roles = user.getRoles();
        List<ERole> eroles = roles.stream()
                .map(Role::getName)
                .toList();

        if(eroles.contains(ERole.ROLE_USER) && eroles.contains(ERole.ROLE_ADMIN)){
            userRepository.demoteUserToModerator(userID, 3L);  // demote back to moderator
        }
        else if(eroles.contains(ERole.ROLE_USER) && eroles.contains(ERole.ROLE_MODERATOR)){
            userRepository.demoteUserToUser(userID, 2L); // demote back to user
        }
    }

}
