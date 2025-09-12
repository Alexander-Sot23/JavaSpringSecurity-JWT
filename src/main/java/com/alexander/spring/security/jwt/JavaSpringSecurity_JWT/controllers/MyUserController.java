package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.controllers;

import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.entities.MyUser;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.models.PasswordUpdateRequestM;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.services.MyUserService;
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
@RequestMapping("/api/user")
public class MyUserController {

    @Autowired
    private MyUserService myUserService;

    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(defaultValue = "0") int pageNum,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "userName") String sort){
        return ResponseEntity.ok(myUserService.findAll(PageRequest.of(pageNum,pageSize, Sort.by(sort))));
    }

    @GetMapping("/user-name/{username}")
    public ResponseEntity<?> findByUserName(@PathVariable String username){
        return ResponseEntity.ok(myUserService.findByUserName(username));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email){
        return ResponseEntity.ok(myUserService.findByEmail(email));
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody MyUser myUser){
        return ResponseEntity.status(HttpStatus.CREATED).body(myUserService.save(myUser));
    }

    @PostMapping("/bulk")
    public ResponseEntity<?> saveAll(@Valid @RequestBody List<MyUser> myUsers){
        return ResponseEntity.status(HttpStatus.CREATED).body(myUserService.saveAll(myUsers));
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> update(
            @PathVariable String username,
            @Valid @RequestBody MyUser myUser){
        return ResponseEntity.ok(myUserService.update(username,myUser));
    }

    @PutMapping("/update-password/{username}")
    public ResponseEntity<?> updatePassword(
            @PathVariable String username,
            @Valid @RequestBody PasswordUpdateRequestM newPassword){
        return ResponseEntity.ok(myUserService.updatePassword(username,newPassword));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> delete(@PathVariable String username){
        myUserService.delete(username);
        return ResponseEntity.ok(new HashMap<>().put("message","El usuario: " + username + ", fue eliminado exitosamenete."));
    }

}
