package com.example.capstone2foodtruck.Repository;

import com.example.capstone2foodtruck.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

   Order findOrderById(Integer id);



    @Query("SELECT SUM(o.totalPrice) FROM Order o WHERE o.foodTruckId = ?1")
    Double getTotalRevenue(Integer foodTruckId);



}
