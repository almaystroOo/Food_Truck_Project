package com.example.capstone2foodtruck.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "food_truck_id", nullable = false)
    private FoodTruck foodTruck;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private double cost;

    @Column(nullable = false, length = 10)
    private String availability; // "Available", "Unavailable"

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> reviews;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FoodTruck getFoodTruck() {
        return foodTruck;
    }

    public void setFoodTruck(FoodTruck foodTruck) {
        this.foodTruck = foodTruck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
// Getters and Setters
}
//public class Product {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @NotNull(message = "food Truck Id should not be empty")
//    @Column(columnDefinition = "varchar(6) not null uniq")
//    private Integer foodTruckId;
//
//    @NotEmpty(message = "name should not be empty")
//    @Size(min = 2,max = 20,message = "Must be more than two letters")
//    @Column(columnDefinition = "varchar(20) not null")
//    private String name;
//
//    @Positive
//    @NotNull(message = "price should not be empty")
//    @Column(columnDefinition = "double not null")
//    private double price;
//
//    @Positive
//    @NotNull(message = "price should not be empty")
//    @Column(columnDefinition = "double not null")
//    private double cost;
//
//
//
//    @NotEmpty(message = "Availability should not be empty")
//    @Column(columnDefinition = "varchar(12) not null check(Availability='Available' or Availability='unavailable' ")
//    private String Availability;
//
//
//}
