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
    private int id;

    @Column(nullable = false, length = 20)
    private String name;

    @ManyToOne
    @JoinColumn(name = "food_truck_id", nullable = false)
    private FoodTruck foodTruck;

    @Column(nullable = false, length = 20)
    private String role; // "cashier" or "chef"

    @Column(nullable = false)
    private double salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public FoodTruck getFoodTruck() {
        return foodTruck;
    }

    public void setFoodTruck(FoodTruck foodTruck) {
        this.foodTruck = foodTruck;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFoodTruckId(int id) {

    }
// Getters and Setters
}
//public class Employee {
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
//    @NotNull(message = "foodTruckId should not be empty")
//    @Positive
//    @Column(columnDefinition = "int not null ")
//    private  Integer foodTruckId;
//
//
//    @NotEmpty(message = "role should should not be empty")
//    @Pattern(regexp = "(Cashier|chief)")
//    @Column(columnDefinition = "varchar(9) not null check(role='Cashier' or role='chief')")
//    private String role;
//
//    @NotNull(message = "salary should should not be empty")
//    @Positive
//    @Column(columnDefinition ="int not null")
//    private Integer salary;
//
//
//    @ManyToOne
//    @JoinColumn(name = "food_truck_id")
//    private FoodTruck foodTruck;
//
//}
