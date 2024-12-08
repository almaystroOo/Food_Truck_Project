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
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "productId should not be empty")
    @Positive
    @Column(columnDefinition = "int not null")
    private Integer productId;

    @Min(1)
    @Max(5)
    @Column(columnDefinition = "int ")
    private Integer rating;

    @Column(columnDefinition = "varchar(40)")
    private String comment;

}
