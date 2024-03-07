package com.zanchenko.alex.diploma.repository;

import com.zanchenko.alex.diploma.models.ERole;
import com.zanchenko.alex.diploma.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
