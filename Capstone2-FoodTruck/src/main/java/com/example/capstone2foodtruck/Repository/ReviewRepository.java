package com.example.capstone2foodtruck.Repository;

import com.example.capstone2foodtruck.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {

     Review findReviewById(Integer id);
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.product.foodTruck.id = :foodTruckId")
    Double getAverageRating(@Param("foodTruckId") Integer foodTruckId);
//    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.productId = :productId")
//    Double getAverageRating(Integer productId);

//    void save(Review review);

    List<Review> findAll();

    void delete(Review oldReview);
}
