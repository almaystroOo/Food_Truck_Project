package com.example.capstone2foodtruck.Repository;

import com.example.capstone2foodtruck.Model.Fod_Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Fod_Order,Integer> {

   Fod_Order findOrderById(Integer id);

    @Query("SELECT SUM(o.totalPrice) FROM Fod_Order o WHERE o.foodTruck.id = :foodTruckId")
    Double getTotalRevenue(@Param("foodTruckId") Integer foodTruckId);

//    @Query("SELECT SUM(o.totalPrice) FROM Fod_Order o WHERE o.foodTruckId = ?1")
//    Double getTotalRevenue(Integer foodTruckId);


    @Query("""
            SELECT o FROM Fod_Order o WHERE o.foodTruck = ?1 AND o.status = 'complete' AND DATE(o.orderDate) = ?2""")
    List<Fod_Order> findCompletedOrdersByDate(Integer foodTruckId, LocalDate date);



}
