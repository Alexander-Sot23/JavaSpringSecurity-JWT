package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.repositories;

import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.entities.Book;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.entities.Loan;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.entities.MyUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoanRepository extends JpaRepository<Loan,UUID> {
    List<Loan> findByMyUser(Pageable pageable, MyUser myUser);
    List<Loan> findByBook(Pageable pageable, Book book);
    Optional<Loan> findByLoanReference(String loanReference);
}
