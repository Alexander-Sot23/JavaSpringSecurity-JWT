package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.repositories;

import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.entities.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book,UUID> {
    Optional<Book> findByIsbn(String isbn);
    List<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    List<Book> findByGenreContainingIgnoreCase(String genre, Pageable pageable);
    boolean existsByIsbn(String isbn);
    List<Book> findByAvailableTrue(Pageable pageable);
    long countByAvailableTrue();
}
