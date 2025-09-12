package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.models;

import jakarta.validation.constraints.NotBlank;

public record LoginFormM(@NotBlank String username,@NotBlank String password) {
}
