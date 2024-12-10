package com.example.capstone2foodtruck.Service;


import com.example.capstone2foodtruck.ApiResponse.ApiExcepiton;
import com.example.capstone2foodtruck.Model.FoodTruck;
import com.example.capstone2foodtruck.Model.Product;
import com.example.capstone2foodtruck.Repository.FoodTruckRepository;
import com.example.capstone2foodtruck.Repository.OrderRepository;
import com.example.capstone2foodtruck.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodTruckService {

    private final FoodTruckRepository foodTruckRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public List<FoodTruck> getFoodTruck() {
        return foodTruckRepository.findAll();
    }

    public void addFoodTruck(FoodTruck foodTruck) {
        foodTruckRepository.save(foodTruck);
    }

    public void updateFoodTruck(Integer id, FoodTruck foodTruck) {
        FoodTruck oldFoodTruck = foodTruckRepository.findById(id)
                .orElseThrow(() -> new ApiExcepiton("Food truck not found"));

        oldFoodTruck.setName(foodTruck.getName());
        oldFoodTruck.setType(foodTruck.getType());
        oldFoodTruck.setLicenseStatus(foodTruck.getLicenseStatus());
        oldFoodTruck.setStatus(foodTruck.getStatus());

        foodTruckRepository.save(oldFoodTruck);
    }

    public void deleteFoodTruck(Integer id) {
        FoodTruck oldFoodTruck = foodTruckRepository.findById(id)
                .orElseThrow(() -> new ApiExcepiton("Food truck not found"));

        foodTruckRepository.delete(oldFoodTruck);
    }

    public double calculateTotalCost(Integer foodTruckId) {
        return foodTruckRepository.getTotalSalaries(foodTruckId) +
                foodTruckRepository.getTotalProductCosts(foodTruckId) +
                foodTruckRepository.getLicenseFee(foodTruckId);
    }

    public double calculateDailyNetProfit(Integer foodTruckId) {
        Double totalRevenue = orderRepository.getTotalRevenue(foodTruckId);
        if (totalRevenue == null) {
            totalRevenue = 0.0;
        }
        double totalCost = calculateTotalCost(foodTruckId);
        return totalRevenue - totalCost;
    }

    public double suggestProductPrice(int productId, double profitMargin) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ApiExcepiton("Product not found"));
        return product.getCost() + (product.getCost() * profitMargin);
    }

    public double calculateLicenseFee(String foodTruckType) {
        switch (foodTruckType.toLowerCase()) {
            case "food":
                return 100.0;
            case "non food":
                return 150.0;
            default:
                throw new ApiExcepiton("Unknown food truck type");
        }
    }

    public LocalDate suggestMaintenanceDate(LocalDate lastMaintenanceDate) {
        return lastMaintenanceDate.plusMonths(6);
    }
}





