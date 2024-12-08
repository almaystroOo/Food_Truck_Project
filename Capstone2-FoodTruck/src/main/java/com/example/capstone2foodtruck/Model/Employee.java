package com.example.capstone2foodtruck.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name should not be empty")
    @Size(min = 2,max = 20,message = "Must be more than two letters")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotNull(message = "foodTruckId should not be empty")
    @Positive
    @Column(columnDefinition = "int not null ")
    private  Integer foodTruckId;


    @NotEmpty(message = "role should should not be empty")
    @Pattern(regexp = "(Cashier|chief)")
    @Column(columnDefinition = "varchar(9) not null check(role='Cashier' or role='chief')")
    private String role;

    @NotNull(message = "salary should should not be empty")
    @Positive
    @Column(columnDefinition ="int not null")
    private Integer salary;


}
