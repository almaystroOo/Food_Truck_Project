package com.example.capstone2foodtruck.Repository;

import com.example.capstone2foodtruck.Model.Truck_License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LicenseRepository extends JpaRepository<Truck_License,Integer> {

    Truck_License findLicenseById(Integer id);


    List<Truck_License> findByFoodTruckId(Integer foodTruckId);

}
