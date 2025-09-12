package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.controllers;

import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.entities.Book;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(defaultValue = "0") int pageNum,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "title") String sort){
        return ResponseEntity.ok(bookService.findALl(PageRequest.of(pageNum,pageSize,Sort.by(sort))));
    }

    @GetMapping("/available")
    public ResponseEntity<?> findByAvailable(
            @RequestParam(defaultValue = "0") int pageNum,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "title") String sort){
        return ResponseEntity.ok(bookService.findAllByAvailable(PageRequest.of(pageNum,pageSize,Sort.by(sort))));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<?> findByTitle(
            @PathVariable String title,
            @RequestParam(defaultValue = "0") int pageNum,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "title") String sort){
        return ResponseEntity.ok(bookService.findByTitle(PageRequest.of(pageNum,pageSize,Sort.by(sort)),title));
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<?> findByGenre(
            @PathVariable String genre,
            @RequestParam(defaultValue = "0") int pageNum,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "title") String sort){
        return ResponseEntity.ok(bookService.findByGenre(PageRequest.of(pageNum,pageSize,Sort.by(sort)),genre));
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<?> findByIsbn(@PathVariable String isbn){
        return ResponseEntity.ok(bookService.findByIsbn(isbn));
    }

    @GetMapping("/count-available")
    public ResponseEntity<?> findAvaliableBooks(){
        return ResponseEntity.ok(bookService.countByAvailableTrue());
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Book book){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(book));
    }

    @PostMapping("/bulk")
    public ResponseEntity<?> saveAll(@Valid @RequestBody List<Book> books){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveALl(books));
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<?> update(@PathVariable String isbn, @Valid @RequestBody Book book){
        return ResponseEntity.ok(bookService.update(isbn,book));
    }

    @PutMapping("/available/{isbn}/{available}")
    public ResponseEntity<?> updateAvailable(@PathVariable String isbn, @PathVariable boolean available){
        return ResponseEntity.ok(bookService.updateAvailable(isbn,available));
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<?> delete(@PathVariable String isbn){
        bookService.delete(isbn);
        return ResponseEntity.ok(new HashMap<>().put("message","Libro con el ISBN: " + isbn + ", fue eliminado con exito."));
    }
}
