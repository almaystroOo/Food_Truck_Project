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
public class FoodTruck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name should not be empty")
    @Size(min = 2,max = 20,message = "Must be more than two letters")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;


//    @NotNull(message = "foodTruckId should not be empty")
//    @Positive
//    @Column(columnDefinition = "int not null uniq")
//    private  Integer ownerId;


    @NotEmpty(message = "type should should not be empty")
    @Pattern(regexp = "(food|non food)")
    @Column(columnDefinition = "varchar(8) not null check(type='food' or type='non food' )")
    private String type;

    @NotEmpty(message = "licenseStatus should should not be empty")
    @Pattern(regexp = "(effective|Waiting for approval)")
    @Column(columnDefinition = "varchar(20) not null check(licenseStatus='effective' or licenseStatus='Waiting for approval')")
    private  String licenseStatus;

    @NotEmpty(message = "status should should not be empty")
    @Pattern(regexp = "(active|Inactive)")
    @Column(columnDefinition = "varchar(8) not null check(status='active' or status='Inactive')")
    private String status;


}
