package com.zanchenko.alex.diploma.service;

import com.zanchenko.alex.diploma.domain.autentication.ERole;
import com.zanchenko.alex.diploma.domain.autentication.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Optional<Role> findByRole(ERole eRole);
}