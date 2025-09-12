package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.services;

import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.dtos.LoanDTO;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.entities.Loan;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface LoanService {
    Set<LoanDTO> findAll(Pageable pageable);
    Set<LoanDTO> findByMyUser(Pageable pageable,String username);
    Set<LoanDTO> findByBook(Pageable pageable, String isbn);
    LoanDTO findByLoanReference(String loanReference);
    LoanDTO save(Loan loan);
    Set<LoanDTO> saveAll(List<Loan> loans);
    LoanDTO extendLoan(String loanReference, LocalDate newDueDate);
    void delete(String loanReference);
}
