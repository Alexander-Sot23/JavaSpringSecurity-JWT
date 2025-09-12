package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.repositories;

import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.entities.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MyUserRepository extends JpaRepository<MyUser,UUID> {
    Optional<MyUser> findByUserName(String userName);
    Optional<MyUser> findByEmail(String email);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
}
