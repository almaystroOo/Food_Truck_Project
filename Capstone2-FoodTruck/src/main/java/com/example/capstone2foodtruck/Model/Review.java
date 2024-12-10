package com.example.capstone2foodtruck.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;
import jakarta.persistence.*;
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
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(length = 40)
    private String comment;

    @Column
    private int rating;

    // No need for manual getter/setter methods if using Lombok's @Data annotation
}

//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//public class Review {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id", nullable = false)
//    private Product product;
//
//    @Column(length = 40)
//    private String comment;
//
//    @Column
//    private int rating;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Optional<Product> product) {
//        this.product = product;
//    }
//
//    public String getComment() {
//        return comment;
//    }
//
//    public void setComment(String comment) {
//        this.comment = comment;
//    }
//
//    public int getRating() {
//        return rating;
//    }
//
//    public void setRating(int rating) {
//        this.rating = rating;
//    }
//// Getters and Setters
//}

//public class Review {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @NotNull(message = "productId should not be empty")
//    @Positive
//    @Column(columnDefinition = "int not null")
//    private Integer productId;
//
//    @Min(1)
//    @Max(5)
//    @Column(columnDefinition = "int ")
//    private Integer rating;
//
//    @Column(columnDefinition = "varchar(40)")
//    private String comment;
//
//}
