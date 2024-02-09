package com.zanchenko.alex.diploma.repository;

import com.zanchenko.alex.diploma.domain.autentication.ERole;
import com.zanchenko.alex.diploma.domain.autentication.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
