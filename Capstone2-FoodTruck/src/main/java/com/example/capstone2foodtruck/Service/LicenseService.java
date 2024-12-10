package com.example.capstone2foodtruck.Service;


import com.example.capstone2foodtruck.ApiResponse.ApiExcepiton;
import com.example.capstone2foodtruck.Model.FoodTruck;
import com.example.capstone2foodtruck.Model.Truck_License;
import com.example.capstone2foodtruck.Repository.FoodTruckRepository;
import com.example.capstone2foodtruck.Repository.LicenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LicenseService {

    private final LicenseRepository licenseRepository;
    private final FoodTruckRepository foodTruckRepository;

    public List<Truck_License> getLicense() {
        return licenseRepository.findAll();
    }

    public void addLicense(Truck_License truckLicense, Integer foodTruckId) {
        FoodTruck foodTruck = foodTruckRepository.findById(foodTruckId)
                .orElseThrow(() -> new ApiExcepiton("Food truck not found"));
        truckLicense.setFoodTruck(foodTruck);
        licenseRepository.save(truckLicense);
    }

    public void updateLicense(Integer id, Truck_License truckLicense) {
        Truck_License oldTruckLicense = licenseRepository.findById(id)
                .orElseThrow(() -> new ApiExcepiton("License not found"));

        oldTruckLicense.setFoodTruck(truckLicense.getFoodTruck());
        oldTruckLicense.setIssueDate(truckLicense.getIssueDate());
        oldTruckLicense.setExpiryDate(truckLicense.getExpiryDate());
        oldTruckLicense.setStatus(truckLicense.getStatus());
        oldTruckLicense.setFee(truckLicense.getFee());

        licenseRepository.save(oldTruckLicense);
    }

    public void deleteLicense(Integer id) {
        Truck_License oldTruckLicense = licenseRepository.findById(id)
                .orElseThrow(() -> new ApiExcepiton("License not found"));

        licenseRepository.delete(oldTruckLicense);
    }

    public Integer calculateExpirationPeriod(Integer foodTruckId) {
        List<Truck_License> licens = licenseRepository.findByFoodTruckId(foodTruckId);
        return licens.stream()
                .mapToInt(truckLicense -> (int) ChronoUnit.DAYS.between(LocalDate.now(), (Temporal) truckLicense.getExpiryDate()))
                .sum();
    }
}