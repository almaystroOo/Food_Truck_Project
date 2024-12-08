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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "food Truck Id should not be empty")
    @Column(columnDefinition = "varchar(6) not null uniq")
    private Integer foodTruckId;

    @NotEmpty(message = "name should not be empty")
    @Size(min = 2,max = 20,message = "Must be more than two letters")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @Positive
    @NotNull(message = "price should not be empty")
    @Column(columnDefinition = "double not null")
    private double price;

    @Positive
    @NotNull(message = "price should not be empty")
    @Column(columnDefinition = "double not null")
    private double cost;



    @NotEmpty(message = "Availability should not be empty")
    @Column(columnDefinition = "varchar(12) not null check(Availability='Available' or Availability='unavailable' ")
    private String Availability;


}
