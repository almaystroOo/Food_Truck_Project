package com.example.capstone2foodtruck.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fod_Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "food_truck_id", nullable = false)
    private FoodTruck foodTruck;

    private String customerName;
    private Double totalPrice;
    private LocalDate orderDate;
    private String status;
}
//
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Positive;
//import jakarta.validation.constraints.Size;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.Date;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//public class Order {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @ManyToOne
//    @JoinColumn(name = "food_truck_id", nullable = false)
//    private FoodTruck foodTruck;
//
//    @Column(nullable = false, length = 20)
//    private String customerName;
//
//    @Column(nullable = false)
//    private double totalPrice;
//
//    @Temporal(TemporalType.DATE)
//    @Column(nullable = false)
//    private Date orderDate;
//
//    @Column(nullable = false, length = 20)
//    private String status; // "complete", "in_progress"
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public FoodTruck getFoodTruck() {
//        return foodTruck;
//    }
//
//    public void setFoodTruck(FoodTruck foodTruck) {
//        this.foodTruck = foodTruck;
//    }
//
//    public String getCustomerName() {
//        return customerName;
//    }
//
//    public void setCustomerName(String customerName) {
//        this.customerName = customerName;
//    }
//
//    public double getTotalPrice() {
//        return totalPrice;
//    }
//
//    public void setTotalPrice(double totalPrice) {
//        this.totalPrice = totalPrice;
//    }
//
//    public Date getOrderDate() {
//        return orderDate;
//    }
//
//    public void setOrderDate(Date orderDate) {
//        this.orderDate = orderDate;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//// Getters and Setters
//}

//public class Order {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @NotNull(message = "foodTruckId should not be empty")
//    @Positive
//    @Column(columnDefinition = "int not null uniq")
//    private Integer foodTruckId;
//
//    @NotEmpty(message = "customer Name should not be empty")
//    @Size(min = 2,max = 20,message = "Must be more than two letters")
//    @Column(columnDefinition = "varchar(20) not null")
//    private String customerName;
//
//
//    @NotNull(message = "total Price should not be empty")
//    @Positive
//    @Column(columnDefinition = "double not null ")
//    private double totalPrice;
//
//    @Column(columnDefinition = " not null ")
//    private Date orderDate;
//
//    @NotEmpty(message = "status should not be empty")
//    @Size(min = 2,max = 20,message = "Must be more than two letters")
//    @Column(columnDefinition = "varchar(11) not null check(orderDate='complete' or orderDate='in progress')")
//    private  String status;
//
//
//}
