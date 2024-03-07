package com.zanchenko.alex.diploma.service;


import com.zanchenko.alex.diploma.domain.autentication.ERole;
import com.zanchenko.alex.diploma.domain.autentication.Role;
import com.zanchenko.alex.diploma.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> findByRole(ERole eRole) {
        return roleRepository.findByName(eRole);
    }
}