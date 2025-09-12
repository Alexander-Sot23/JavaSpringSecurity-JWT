package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.dtos;

import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.entities.Book;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.entities.Loan;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.entities.MyUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanDTO {

    private UUID id;
    private MyUser myUser;
    private Book book;
    private String loanReference;
    private LocalDate startDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String notes;

    public LoanDTO(Loan loan){
        this.id = loan.getId();
        this.myUser = loan.getMyUser();
        this.book = loan.getBook();
        this.loanReference = loan.getLoanReference();
        this.startDate = loan.getStartDate();
        this.dueDate = loan.getDueDate();
        this.returnDate = loan.getReturnDate();
        this.notes = loan.getNotes();
    }

}
