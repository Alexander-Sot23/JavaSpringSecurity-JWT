package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Transient
    private String myUserUsername;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "myuser_id", nullable = false)
    private MyUser myUser;

    @Transient
    private String bookIsbn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(unique = true, updatable = false)
    @NotBlank
    private String loanReference;

    @Column(nullable = false, updatable = false)
    private LocalDate startDate = LocalDate.now();

    @Column(nullable = false)
    private LocalDate dueDate;

    private LocalDate returnDate;

    @Size(max = 300)
    private String notes;

}
