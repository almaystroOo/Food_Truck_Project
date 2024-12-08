package com.example.capstone2foodtruck.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "foodTruckId should not be empty")
    @Positive
    @Column(columnDefinition = "int not null uniq")
    private Integer foodTruckId;

    @NotEmpty(message = "customer Name should not be empty")
    @Size(min = 2,max = 20,message = "Must be more than two letters")
    @Column(columnDefinition = "varchar(20) not null")
    private String customerName;


    @NotNull(message = "total Price should not be empty")
    @Positive
    @Column(columnDefinition = "double not null ")
    private double totalPrice;

    @Column(columnDefinition = " not null ")
    private Date orderDate;

    @NotEmpty(message = "status should not be empty")
    @Size(min = 2,max = 20,message = "Must be more than two letters")
    @Column(columnDefinition = "varchar(11) not null check(orderDate='complete' or orderDate='in progress')")
    private  String status;


}
