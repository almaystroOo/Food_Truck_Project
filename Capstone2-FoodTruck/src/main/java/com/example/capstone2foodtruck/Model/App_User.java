package com.example.capstone2foodtruck.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class App_User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 20, unique = true)
    private String email;

    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(nullable = false, length = 20)
    private String userType; // "owner" or "employee"

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
// Getters and Setters
}
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @NotEmpty(message = "name should not be empty")
//    @Size(min = 2,max = 20,message = "Must be more than two letters")
//    @Column(columnDefinition = "varchar(20) not null")
//    private String name;
//
//    @Email
//    @NotEmpty(message = "email should not be empty")
//    @Column(columnDefinition = "varchar(20) not null")
//    private String email;
//
//    @NotEmpty(message = "name should not be empty")
//    @Pattern(regexp = " ^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")
//    @Column(columnDefinition = "varchar(20) not null")
//    private String password;
//
//    @NotEmpty(message = "phone should not be empty")
//    @Pattern(regexp = "\"^05\\\\d{8}$")
//    @Column(columnDefinition = "varchar(10) not null")
//    private String phone;
//
//
//    @NotEmpty(message = "userType should not be empty")
//    @Pattern(regexp = "(owner|employee)")
//    @Column(columnDefinition = "varchar(10) not null check(userType='owner' or userType='employee' ")
//    private String userType;
//
//}

