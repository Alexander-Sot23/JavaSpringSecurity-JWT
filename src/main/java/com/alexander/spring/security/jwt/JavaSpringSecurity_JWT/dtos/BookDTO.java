package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.dtos;

import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.entities.Book;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.entities.Loan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private UUID id;
    private String author;
    private String title;
    private String description;
    private String isbn;
    private LocalDate registerDate;
    private String genre;
    private boolean available;
    private List<Loan> loan;

    public BookDTO(Book book){
        this.id = book.getId();
        this.author = book.getAuthor();
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.isbn = book.getIsbn();
        this.registerDate = book.getRegisterDate();
        this.genre = book.getGenre();
        this.available = book.isAvailable();
        this.loan = book.getLoans();
    }

}
