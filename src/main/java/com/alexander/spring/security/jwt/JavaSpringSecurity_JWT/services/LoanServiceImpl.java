package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.services;

import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.dtos.LoanDTO;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.entities.Book;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.entities.Loan;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.entities.MyUser;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.exceptions.BookISBNNotFoundException;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.exceptions.BookNotAvailableException;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.exceptions.LoanReferenceNotFoundException;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.exceptions.UserNameNotFoundException;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.repositories.BookRepository;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.repositories.LoanRepository;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.repositories.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService{

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private MyUserRepository myUserRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Set<LoanDTO> findAll(Pageable pageable) {
        return loanRepository.findAll(pageable).stream()
                .map(LoanDTO::new)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<LoanDTO> findByMyUser(Pageable pageable, String username) {
        return loanRepository.findByMyUser(pageable,myUserRepository.findByUserName(username)
                        .orElseThrow(() -> new UserNameNotFoundException(username))).stream()
                .map(LoanDTO::new)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<LoanDTO> findByBook(Pageable pageable, String isbn) {
        return loanRepository.findByBook(pageable,bookRepository.findByIsbn(isbn)
                        .orElseThrow(() -> new BookISBNNotFoundException(isbn))).stream()
                .map(LoanDTO::new)
                .collect(Collectors.toSet());
    }

    @Override
    public LoanDTO findByLoanReference(String loanReference) {
        return new LoanDTO(loanRepository.findByLoanReference(loanReference)
                .orElseThrow(() -> new LoanReferenceNotFoundException(loanReference)));
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public LoanDTO save(Loan loan) {
        Loan preparedLoan = setMyUserAndBook(loan);
        if (!preparedLoan.getBook().isAvailable()) {
            throw new BookNotAvailableException(preparedLoan.getBook().getTitle());
        }
        if(preparedLoan.getStartDate()==null){
            preparedLoan.setStartDate(LocalDate.now());
        }
        if(preparedLoan.getDueDate().isBefore(preparedLoan.getStartDate())){
            throw new IllegalArgumentException(("La fecha de vencimiento no puede ser anterior a la fecha de inicio."));
        }
        preparedLoan.getBook().setAvailable(false);
        bookRepository.save(preparedLoan.getBook());
        return new LoanDTO(loanRepository.save(preparedLoan));
    }

    @Override
    @Transactional
    public Set<LoanDTO> saveAll(List<Loan> loans) {
        return loans.stream()
                .map(this::save)
                .collect(Collectors.toSet());
    }

    @Override
    public LoanDTO extendLoan(String loanReference, LocalDate newDueDate) {
        Loan loan = loanRepository.findByLoanReference(loanReference)
                .orElseThrow(() -> new LoanReferenceNotFoundException(loanReference));
        loan.setDueDate(newDueDate);
        return new LoanDTO(loanRepository.save(loan));
    }

    @Override
    public void delete(String loanReference) {
        loanRepository.delete(loanRepository.findByLoanReference(loanReference)
                .orElseThrow(() -> new LoanReferenceNotFoundException(loanReference)));
    }

    private Loan setMyUserAndBook(Loan loan){
        MyUser myUser = myUserRepository.findByUserName(loan.getMyUserUsername())
                .orElseThrow(() -> new UserNameNotFoundException(loan.getMyUserUsername()));
        Book book = bookRepository.findByIsbn(loan.getBookIsbn())
                .orElseThrow(() -> new BookISBNNotFoundException(loan.getBookIsbn()));
        loan.setMyUser(myUser);
        loan.setBook(book);
        return loan;
    }
}
