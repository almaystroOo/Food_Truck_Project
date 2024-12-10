package com.example.capstone2foodtruck.Controller;

import com.example.capstone2foodtruck.Model.FoodTruck;
import com.example.capstone2foodtruck.Service.FoodTruckService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/FoodTruc")
@RequiredArgsConstructor
public class FoodTruckController {

    private final FoodTruckService foodTruckService;

    @GetMapping("/get")
    public ResponseEntity get() {

        return ResponseEntity.status(HttpStatus.OK).body(foodTruckService.getFoodTruck());
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid FoodTruck foodTruck, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        foodTruckService.addFoodTruck(foodTruck);
        return ResponseEntity.status(HttpStatus.OK).body("FoodTruck added");
    }

 @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody @Valid FoodTruck foodTruck, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        foodTruckService.updateFoodTruck(id, foodTruck);
        return ResponseEntity.status(HttpStatus.OK).body("FoodTruck update");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {

      foodTruckService.deleteFoodTruck(id);
        return ResponseEntity.status(HttpStatus.OK).body("FoodTruck delete");
    }


    @GetMapping("/total-cost/{id}")
    public ResponseEntity<Double> calculateTotalCost(@PathVariable Integer id) {
        double totalCost = foodTruckService.calculateTotalCost(id);
        return ResponseEntity.ok(totalCost);
    }

    @GetMapping("/daily-net-profit/{id}")
    public ResponseEntity<Double> calculateDailyNetProfit(@PathVariable Integer id) {
        double netProfit = foodTruckService.calculateDailyNetProfit(id);
        return ResponseEntity.ok(netProfit);
    }

    @GetMapping("/suggest-product-price/{productId}/{profitMargin}")
    public ResponseEntity<Double> suggestProductPrice(@PathVariable Integer productId, @PathVariable double profitMargin) {
        double suggestedPrice = foodTruckService.suggestProductPrice(productId, profitMargin);
        return ResponseEntity.ok(suggestedPrice);
    }

    @GetMapping("/calculate-license-fee/{foodTruckType}")
    public ResponseEntity<Double> calculateLicenseFee(@PathVariable String foodTruckType) {
        double fee = foodTruckService.calculateLicenseFee(foodTruckType);
        return ResponseEntity.ok(fee);
    }

    @GetMapping("/suggest-maintenance-date/{lastMaintenanceDate}")
    public ResponseEntity<LocalDate> suggestMaintenanceDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate lastMaintenanceDate) {
        LocalDate nextMaintenanceDate = foodTruckService.suggestMaintenanceDate(lastMaintenanceDate);
        return ResponseEntity.ok(nextMaintenanceDate);
    }




}
