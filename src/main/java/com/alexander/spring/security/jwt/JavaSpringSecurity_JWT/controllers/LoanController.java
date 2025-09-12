package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.controllers;

import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.entities.Loan;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.services.LoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/loan")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(defaultValue = "0") int pageNum,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "loanReference") String sort){
        return ResponseEntity.ok(loanService.findAll(PageRequest.of(pageNum,pageSize, Sort.by(sort))));
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?> findByMyUser(
            @PathVariable String username,
            @RequestParam(defaultValue = "0") int pageNum,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "loanReference") String sort){
        return ResponseEntity.ok(loanService.findByMyUser(PageRequest.of(pageNum,pageSize,Sort.by(sort)),username));
    }

    @GetMapping("/book/{isbn}")
    public ResponseEntity<?> findByBook(
            @PathVariable String isbn,
            @RequestParam(defaultValue = "0") int pageNum,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "loanReference") String sort){
        return ResponseEntity.ok(loanService.findByBook(PageRequest.of(pageNum,pageSize,Sort.by(sort)),isbn));
    }

    @GetMapping("/{loanReference}")
    public ResponseEntity<?> findByLoanReference(@PathVariable String loanReference){
        return ResponseEntity.ok(loanService.findByLoanReference(loanReference));
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Loan loan){
        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.save(loan));
    }

    @PostMapping("/bulk")
    public ResponseEntity<?> saveAll(@Valid @RequestBody List<Loan> loans){
        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.saveAll(loans));
    }

    @PutMapping("/{loanReference}")
    public ResponseEntity<?> extendLoan(@PathVariable String loanReference, @RequestBody LocalDate newDate){
        return ResponseEntity.ok(loanService.extendLoan(loanReference,newDate));
    }

    @DeleteMapping("/{loanReference}")
    public ResponseEntity<?> delete(@PathVariable String loanReference){
        loanService.delete(loanReference);
        return ResponseEntity.ok(new HashMap<>().put("message","El prestamo con referencia: " + loanReference + ", fue eliminado exitosamente."));
    }
}
