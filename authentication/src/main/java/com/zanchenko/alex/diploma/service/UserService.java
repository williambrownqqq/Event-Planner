package com.zanchenko.alex.diploma.service;

import com.zanchenko.alex.diploma.domain.autentication.User;

public interface UserService {
    User saveUser(User user);

    boolean existByUsername(String username);

    boolean existByEmail(String email);
}
