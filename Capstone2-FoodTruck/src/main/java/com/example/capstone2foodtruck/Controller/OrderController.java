package com.example.capstone2foodtruck.Controller;


import com.example.capstone2foodtruck.Model.Fod_Order;
import com.example.capstone2foodtruck.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/get")
    public ResponseEntity<?> get() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrder());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid Fod_Order fodOrder, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        orderService.addOrder(fodOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body("Order added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid Fod_Order fodOrder, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        orderService.updateOrder(id, fodOrder);
        return ResponseEntity.status(HttpStatus.OK).body("Order updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return ResponseEntity.status(HttpStatus.OK).body("Order deleted");
    }

    @GetMapping("/expected-waiting-time/{orderId}")
    public ResponseEntity<?> calculateExpectedWaitingTime(@PathVariable Integer orderId) {
        int waitingTime = orderService.calculateExpectedWaitingTime(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(waitingTime);
    }

    @GetMapping("/price-after-discount/{originalPrice}/{discountPercentage}")
    public ResponseEntity<?> calculatePriceAfterDiscount(
            @PathVariable Double originalPrice,
            @PathVariable Double discountPercentage) {
        if (originalPrice == null || discountPercentage == null || originalPrice < 0 || discountPercentage < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input values");
        }
        double discountedPrice = orderService.calculatePriceAfterDiscount(originalPrice, discountPercentage);
        return ResponseEntity.status(HttpStatus.OK).body(discountedPrice);
    }

    @GetMapping("/loyalty-points/{totalSpent}")
    public ResponseEntity<?> calculateLoyaltyPoints(@PathVariable Double totalSpent) {
        if (totalSpent == null || totalSpent < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid totalSpent value");
        }
        int loyaltyPoints = orderService.calculateLoyaltyPoints(totalSpent);
        return ResponseEntity.status(HttpStatus.OK).body(loyaltyPoints);
    }

    @GetMapping("/food-truck/{id}/completed-orders")
    public ResponseEntity<?> getCompletedOrdersByDate(
            @PathVariable Integer id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        if (date == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Date parameter is required");
        }
        List<Fod_Order> fodOrders = orderService.getCompletedOrdersByDate(id, date);
        return ResponseEntity.status(HttpStatus.OK).body(fodOrders);
    }
}
