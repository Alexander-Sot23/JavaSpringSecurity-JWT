package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PasswordUpdateRequestM(
        @NotBlank String oldPassword,
        @NotBlank @Size(min = 4) String newPassword) {
}
