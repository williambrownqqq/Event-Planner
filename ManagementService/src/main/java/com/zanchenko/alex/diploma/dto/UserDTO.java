package com.zanchenko.alex.diploma.dto;

import com.zanchenko.alex.diploma.domain.autentication.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    Long id;
    String username;
    String email;
    Set<Role> roles;
}
