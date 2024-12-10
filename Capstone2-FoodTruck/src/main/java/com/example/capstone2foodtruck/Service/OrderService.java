package com.example.capstone2foodtruck.Service;

import com.example.capstone2foodtruck.ApiResponse.ApiExcepiton;
import com.example.capstone2foodtruck.Model.Fod_Order;
import com.example.capstone2foodtruck.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Fod_Order> getOrder() {
        return orderRepository.findAll();
    }

    public void addOrder(Fod_Order fodOrder) {
        if (fodOrder == null) {
            throw new ApiExcepiton("Order cannot be null");
        }
        orderRepository.save(fodOrder);
    }

    public void updateOrder(Integer id, Fod_Order fodOrder) {
        if (fodOrder == null) {
            throw new ApiExcepiton("Order cannot be null");
        }

        Fod_Order oldFodOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ApiExcepiton("Order not found"));

        oldFodOrder.setFoodTruck(fodOrder.getFoodTruck());
        oldFodOrder.setCustomerName(fodOrder.getCustomerName());
        oldFodOrder.setTotalPrice(fodOrder.getTotalPrice());
        oldFodOrder.setOrderDate(fodOrder.getOrderDate());
        oldFodOrder.setStatus(fodOrder.getStatus());

        orderRepository.save(oldFodOrder);
    }

    public void deleteOrder(Integer id) {
        Fod_Order oldFodOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ApiExcepiton("Order not found"));

        orderRepository.delete(oldFodOrder);
    }

    public int calculateExpectedWaitingTime(Integer orderId) {
        Fod_Order fodOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new ApiExcepiton("Order not found"));

        // Add actual logic for calculating waiting time
        return 15; // Placeholder value
    }

    public double calculatePriceAfterDiscount(Double originalPrice, Double discountPercentage) {
        if (originalPrice == null || discountPercentage == null) {
            throw new ApiExcepiton("Price or discount percentage cannot be null");
        }
        if (originalPrice < 0 || discountPercentage < 0) {
            throw new ApiExcepiton("Price or discount percentage cannot be negative");
        }
        return originalPrice - (originalPrice * (discountPercentage / 100));
    }

    public int calculateLoyaltyPoints(Double totalSpent) {
        if (totalSpent == null || totalSpent < 0) {
            throw new ApiExcepiton("Total spent cannot be null or negative");
        }
        return (int) (totalSpent / 10);
    }

    public List<Fod_Order> getCompletedOrdersByDate(Integer foodTruckId, LocalDate date) {
        if (foodTruckId == null || date == null) {
            throw new ApiExcepiton("Food truck ID and date cannot be null");
        }
        return orderRepository.findCompletedOrdersByDate(foodTruckId, date);
    }
}
