package com.example.capstone2foodtruck.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name should not be empty")
    @Size(min = 2,max = 20,message = "Must be more than two letters")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @Email
    @NotEmpty(message = "email should not be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String email;

    @NotEmpty(message = "name should not be empty")
    @Pattern(regexp = " ^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;

    @NotEmpty(message = "phone should not be empty")
    @Pattern(regexp = "\"^05\\\\d{8}$")
    @Column(columnDefinition = "varchar(10) not null")
    private String phone;


    @NotEmpty(message = "userType should not be empty")
    @Pattern(regexp = "(owner|employee)")
    @Column(columnDefinition = "varchar(10) not null check(userType='owner' or userType='employee' ")
    private String userType;

}
