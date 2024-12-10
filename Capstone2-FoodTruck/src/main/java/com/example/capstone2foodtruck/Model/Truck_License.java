package com.example.capstone2foodtruck.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Truck_License {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "food_truck_id", nullable = false)
    private FoodTruck foodTruck;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date issueDate;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date expiryDate;

    @Column(nullable = false, length = 20)
    private String status; // "pending", "rejected", "approved"

    @Column(nullable = false)
    private double fee;

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

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExpiryDate() {
        return  expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
// Getters and Setters
}

//public class License {
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
//    @Column(columnDefinition = " not null ")
//    private LocalDate issuedDate;
//
//    @Column(columnDefinition = " not null ")
//    private LocalDate expiryDate;
//
//    @NotEmpty(message = "status should should not be empty")
//    @Pattern(regexp = "(Issuing|rejected|approved)")
//    @Column(columnDefinition = "varchar(8) not null check(status='Issuing' or status='rejected' or status='approved')")
//    private String status;
//
//    @NotNull(message = "fee should not be empty")
//    @Positive
//    @Column(columnDefinition = "double not null ")
//    private double fee;
//
//
//
//
//}
