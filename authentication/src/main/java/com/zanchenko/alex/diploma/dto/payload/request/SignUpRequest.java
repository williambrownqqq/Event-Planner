package com.zanchenko.alex.diploma.dto.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpRequest {
    @NotBlank
    @Size(min = 3, max = 50)
    String username;
    @NotBlank
    @Size(max = 50)
    @Email
    String email;

    Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    String password;




}
