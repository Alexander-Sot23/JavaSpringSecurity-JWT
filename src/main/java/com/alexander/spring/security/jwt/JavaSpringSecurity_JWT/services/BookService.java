package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.services;

import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.dtos.BookDTO;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.entities.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface BookService {
    Set<BookDTO> findALl(Pageable pageable);
    Set<BookDTO> findAllByAvailable(Pageable pageable);
    Set<BookDTO> findByTitle(Pageable pageable, String title);
    Set<BookDTO> findByGenre(Pageable pageable, String genre);
    BookDTO findByIsbn(String isbn);
    long countByAvailableTrue();
    BookDTO save(Book book);
    Set<BookDTO> saveALl(List<Book> books);
    BookDTO update(String isbn,Book book);
    BookDTO updateAvailable(String isbn, boolean status);
    void delete(String isbn);
}
