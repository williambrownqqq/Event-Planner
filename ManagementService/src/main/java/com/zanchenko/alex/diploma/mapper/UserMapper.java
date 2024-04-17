package com.zanchenko.alex.diploma.mapper;

import com.zanchenko.alex.diploma.domain.autentication.User;
import com.zanchenko.alex.diploma.dto.UserDTO;

public class UserMapper {

    public static UserDTO mapToUserDTO(User user){

        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles())
                .build();
    }
}
