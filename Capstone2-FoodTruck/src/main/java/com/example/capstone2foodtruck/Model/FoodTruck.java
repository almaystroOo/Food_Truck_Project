package com.example.capstone2foodtruck.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FoodTruck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 20)
    private String type; // "food"

    @Column(nullable = false, length = 20)
    private String licenseStatus; // "effective", "waiting_for_approval"

    @Column(nullable = false, length = 20)
    private String status; // "active", "inactive"

    @OneToMany(mappedBy = "foodTruck", cascade = CascadeType.ALL)
    private List<Employee> employees;

    @OneToMany(mappedBy = "foodTruck", cascade = CascadeType.ALL)
    private List<Truck_License> licens;

    @OneToMany(mappedBy = "foodTruck", cascade = CascadeType.ALL)
    private List<Product> products;

    @OneToMany(mappedBy = "foodTruck", cascade = CascadeType.ALL)
    private List<Fod_Order> fodOrders;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLicenseStatus() {
        return licenseStatus;
    }

    public void setLicenseStatus(String licenseStatus) {
        this.licenseStatus = licenseStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Truck_License> getLicens() {
        return licens;
    }

    public void setLicens(List<Truck_License> licens) {
        this.licens = licens;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Fod_Order> getFodOrders() {
        return fodOrders;
    }

    public void setFodOrders(List<Fod_Order> fodOrders) {
        this.fodOrders = fodOrders;
    }
// Getters and Setters
}
//public class FoodTruck {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @NotEmpty(message = "name should not be empty")
//    @Size(min = 2,max = 20,message = "Must be more than two letters")
//    @Column(columnDefinition = "varchar(20) not null")
//    private String name;
//
//
////    @NotNull(message = "foodTruckId should not be empty")
////    @Positive
////    @Column(columnDefinition = "int not null uniq")
////    private  Integer ownerId;
//
//
//    @NotEmpty(message = "type should should not be empty")
//    @Pattern(regexp = "(food|non food)")
//    @Column(columnDefinition = "varchar(8) not null check(type='food' or type='non food' )")
//    private String type;
//
//    @NotEmpty(message = "licenseStatus should should not be empty")
//    @Pattern(regexp = "(effective|Waiting for approval)")
//    @Column(columnDefinition = "varchar(20) not null check(licenseStatus='effective' or licenseStatus='Waiting for approval')")
//    private  String licenseStatus;
//
//    @NotEmpty(message = "status should should not be empty")
//    @Pattern(regexp = "(active|Inactive)")
//    @Column(columnDefinition = "varchar(8) not null check(status='active' or status='Inactive')")
//    private String status;
//
//
//}
