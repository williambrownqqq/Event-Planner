package com.zanchenko.alexey.authentication.repository;

import com.zanchenko.alexey.authentication.models.ERole;
import com.zanchenko.alexey.authentication.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
