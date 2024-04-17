package com.zanchenko.alex.diploma.repository;

import com.zanchenko.alex.diploma.domain.autentication.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user_roles ( role_id, user_id) SELECT :roleId, :userId  WHERE NOT EXISTS (SELECT 1 FROM user_roles WHERE user_id = :userId AND role_id = :roleId)", nativeQuery = true)
    int promoteUserToModerator(@Param("userId") Long userId,@Param("roleId") Long roleId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user_roles ( role_id, user_id) SELECT :roleId, :userId  WHERE NOT EXISTS (SELECT 1 FROM user_roles WHERE user_id = :userId AND role_id = :roleId)", nativeQuery = true)
    int promoteUserToAdmin(@Param("userId") Long userId,@Param("roleId") Long roleId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM user_roles WHERE user_id = :userId AND role_id = :roleId", nativeQuery = true)
    void demoteUserToUser(@Param("userId") Long userId,@Param("roleId") Long roleId);
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM user_roles WHERE user_id = :userId AND role_id = :roleId", nativeQuery = true)
    void demoteUserToModerator(@Param("userId") Long userId,@Param("roleId") Long roleId);
}
