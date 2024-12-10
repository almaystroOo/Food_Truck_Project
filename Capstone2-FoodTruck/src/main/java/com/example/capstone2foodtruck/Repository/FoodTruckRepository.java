package com.example.capstone2foodtruck.Repository;

import com.example.capstone2foodtruck.Model.FoodTruck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodTruckRepository extends JpaRepository<FoodTruck,Integer> {
   List<FoodTruck> findAll();

  FoodTruck findFoodTruckById(Integer id);


//  Double getTotalSalaries(int foodTruckId);

  @Query("SELECT SUM(p.cost) FROM Product p WHERE p.foodTruck.id = :foodTruckId")
  Double getTotalProductCosts(int foodTruckId);

  @Query("SELECT l.fee FROM Truck_License l WHERE l.foodTruck.id =:foodTruckId")
  Double getLicenseFee(int foodTruckId);

  @Query("SELECT SUM(e.salary) FROM Employee e WHERE e.foodTruck.id = :foodTruckId")
  Double getTotalSalaries(@Param("foodTruckId") int foodTruckId);






}
