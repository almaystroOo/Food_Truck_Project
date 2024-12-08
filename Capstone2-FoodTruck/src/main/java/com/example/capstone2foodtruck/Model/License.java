package com.example.capstone2foodtruck.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class License {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "foodTruckId should not be empty")
    @Positive
    @Column(columnDefinition = "int not null uniq")
    private Integer foodTruckId;

    @Column(columnDefinition = " not null ")
    private LocalDate issuedDate;

    @Column(columnDefinition = " not null ")
    private LocalDate expiryDate;

    @NotEmpty(message = "status should should not be empty")
    @Pattern(regexp = "(Issuing|rejected|approved)")
    @Column(columnDefinition = "varchar(8) not null check(status='Issuing' or status='rejected' or status='approved')")
    private String status;

    @NotNull(message = "fee should not be empty")
    @Positive
    @Column(columnDefinition = "double not null ")
    private double fee;




}
