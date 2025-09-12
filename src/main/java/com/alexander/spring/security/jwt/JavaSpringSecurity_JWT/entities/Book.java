package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String author;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    @Column(unique = true)
    private String isbn;

    @Column(updatable = false)
    private LocalDate registerDate = LocalDate.now();

    @NotBlank
    private String genre;

    @NotNull
    private boolean available = true;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Loan> loans = new ArrayList<>();

}
