package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.dtos;

import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.entities.Loan;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.entities.MyUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyUserDTO {

    private UUID id;
    private String name;
    private String lastName;
    private String userName;
    private String email;
    private String gender;
    private LocalDate birthDate;
    private LocalDate registerDate;
    private List<Loan> loan;

    public MyUserDTO(MyUser myUser){
        this.id = myUser.getId();
        this.name = myUser.getName();
        this.lastName = myUser.getLastName();
        this.userName = myUser.getUserName();
        this.email = myUser.getEmail();
        this.gender = myUser.getGender();
        this.birthDate = myUser.getBirthDate();
        this.registerDate = myUser.getRegisterDate();
        this.loan = myUser.getLoans();
    }
}
