package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.services;

import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.dtos.BookDTO;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.entities.Book;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.exceptions.BookExistsISBNException;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.exceptions.BookISBNNotFoundException;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.repositories.BookRepository;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public Set<BookDTO> findALl(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(BookDTO::new)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<BookDTO> findAllByAvailable(Pageable pageable) {
        return bookRepository.findByAvailableTrue(pageable).stream()
                .map(BookDTO::new)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<BookDTO> findByTitle(Pageable pageable, String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title, pageable).stream()
                .map(BookDTO::new)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<BookDTO> findByGenre(Pageable pageable, String genre) {
        return bookRepository.findByGenreContainingIgnoreCase(genre, pageable).stream()
                .map(BookDTO::new)
                .collect(Collectors.toSet());
    }

    @Override
    public BookDTO findByIsbn(String isbn) {
        return new BookDTO(bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookISBNNotFoundException(isbn)));
    }

    @Override
    public long countByAvailableTrue() {
        return bookRepository.countByAvailableTrue();
    }

    @Override
    public BookDTO save(Book book) {
        if(bookRepository.existsByIsbn(book.getIsbn())){
            throw new BookExistsISBNException(book.getIsbn());
        }
        return new BookDTO(bookRepository.save(book));
    }

    @Override
    public Set<BookDTO> saveALl(List<Book> books) {
        return books.stream()
                .map(this::save)
                .collect(Collectors.toSet());
    }

    @Override
    public BookDTO update(String isbn, Book book) {
        Book bookDB = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookISBNNotFoundException(isbn));
        bookDB.setAuthor(book.getAuthor());
        bookDB.setTitle(book.getTitle());
        bookDB.setDescription(book.getDescription());
        bookDB.setGenre(book.getGenre());

        return new BookDTO(bookRepository.save(bookDB));
    }

    @Override
    public BookDTO updateAvailable(String isbn, boolean status) {
        Book bookDB = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookISBNNotFoundException(isbn));
        bookDB.setAvailable(status);
        return new BookDTO(bookRepository.save(bookDB));
    }

    @Override
    public void delete(String isbn) {
        bookRepository.delete(bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookISBNNotFoundException(isbn)));
    }
}
