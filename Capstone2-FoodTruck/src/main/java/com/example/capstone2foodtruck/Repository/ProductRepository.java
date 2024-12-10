package com.example.capstone2foodtruck.Repository;

import com.example.capstone2foodtruck.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

     Product findProductsById(Integer id);

     List<Product> findByPriceLessThan(Double maxPrice);

     @Query("SELECT AVG(p.price) FROM Product p WHERE p.foodTruck.id = :foodTruckId")
     Double getAveragePriceByFoodTruckId(@Param("foodTruckId") Integer foodTruckId);

//     @Query("SELECT AVG(p.price) FROM Product p WHERE p.foodTruckId = ?1")
//     Double getAveragePriceByFoodTruckId(Integer foodTruckId);

     int countByFoodTruckIdAndAvailability(Integer foodTruckId, String availability);
}
