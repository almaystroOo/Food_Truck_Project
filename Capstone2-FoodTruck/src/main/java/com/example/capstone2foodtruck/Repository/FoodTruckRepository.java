package com.example.capstone2foodtruck.Repository;

import com.example.capstone2foodtruck.Model.FoodTruck;
import com.example.capstone2foodtruck.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodTruckRepository extends JpaRepository<FoodTruck,Integer> {
   List<FoodTruck> findAll();

  FoodTruck findFoodTruckById(Integer id);


  @Query("SELECT SUM(e.salary) FROM Employee e WHERE e.foodTruckId = ?1")
  Double getTotalSalaries(int foodTruckId);

  @Query("SELECT SUM(p.cost) FROM Product p WHERE p.foodTruckId = ?1")
  Double getTotalProductCosts(int foodTruckId);

  @Query("SELECT l.fee FROM License l WHERE l.foodTruckId = ?1")
  Double getLicenseFee(int foodTruckId);





}
