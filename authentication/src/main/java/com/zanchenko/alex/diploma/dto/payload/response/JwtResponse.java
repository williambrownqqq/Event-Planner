package com.zanchenko.alex.diploma.dto.payload.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
//@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtResponse {
    String token;
    String type ="Bearer";
    Long id;
    String username;
    String email;
    List<String> roles;

    public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
