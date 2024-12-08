package com.example.capstone2foodtruck.Repository;

import com.example.capstone2foodtruck.Model.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LicenseRepository extends JpaRepository<License,Integer> {

    License findLicenseById(Integer id);


    List<License> findByFoodTruckId(Integer foodTruckId);

}
