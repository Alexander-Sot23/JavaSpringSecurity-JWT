package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "my_user")
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Size(min = 2, max = 30)
    private String name;

    @NotBlank
    @Size(min = 2, max = 30)
    private String lastName;

    @Column(unique = true)
    @NotBlank
    @Size(min = 2, max = 30)
    private String userName;

    @Column(unique = true)
    @NotBlank
    @Size(min = 5, max = 45)
    private String email;

    @NotBlank
    private String password;

    private String gender;

    private LocalDate birthDate;

    @Column(updatable = false)
    private LocalDate registerDate = LocalDate.now();

    @NotBlank
    private String roles;

    @OneToMany(mappedBy = "myUser", cascade = CascadeType.ALL)
    private List<Loan> loans = new ArrayList<>();

}
