package com.example.capstone2foodtruck.Controller;


import com.example.capstone2foodtruck.Model.Order;
import com.example.capstone2foodtruck.Model.Product;
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
    public ResponseEntity get() {

        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrder());
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid Order order, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        orderService.addOrder(order);
        return ResponseEntity.status(HttpStatus.OK).body("Order added");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody @Valid Order order, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        orderService.updateOrder(id, order);
        return ResponseEntity.status(HttpStatus.OK).body("Order update");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {

        orderService.deleteOrder(id);
        return ResponseEntity.status(HttpStatus.ok).body("Order delete");
    }

    @GetMapping("/expected-waiting-time/{orderId}")
    public ResponseEntity<Integer> calculateExpectedWaitingTime(@PathVariable Integer orderId) {
        int waitingTime = orderService.calculateExpectedWaitingTime(orderId);
        return ResponseEntity.ok(waitingTime);
    }


    @GetMapping("/price-after-discount/{originalPrice}/{discountPercentage}")
    public ResponseEntity<Double> calculatePriceAfterDiscount(@PathVariable Double originalPrice, @PathVariable Double discountPercentage) {
        double discountedPrice = orderService.calculatePriceAfterDiscount(originalPrice, discountPercentage);
        return ResponseEntity.ok(discountedPrice);
    }

    @GetMapping("/loyalty-points/{totalSpent}")
    public ResponseEntity<Integer> calculateLoyaltyPoints(@PathVariable Double totalSpent) {
        int loyaltyPoints = orderService.calculateLoyaltyPoints(totalSpent);
        return ResponseEntity.ok(loyaltyPoints);
    }


    @GetMapping("/food-truck/{id}/completed-orders")
    public ResponseEntity<List<Order>> getCompletedOrdersByDate(
            @PathVariable Integer id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Order> orders = orderService.getCompletedOrdersByDate(id, date);
        return ResponseEntity.ok(orders);
    }

}
