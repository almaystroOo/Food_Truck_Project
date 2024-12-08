package com.example.capstone2foodtruck.Service;

import com.example.capstone2foodtruck.ApiResponse.ApiExcepiton;
import com.example.capstone2foodtruck.Model.Order;
import com.example.capstone2foodtruck.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;


    public List<Order> getOrder(){
        return orderRepository.findAll();
    }


    public void addOrder(Order order){
        orderRepository.save(order);
    }


    public void updateOrder(Integer id,Order order){
        Order oldOrder=orderRepository.findOrderById(id);

        if(oldOrder==null){
            throw new ApiExcepiton("id not found");
        }

        oldOrder.setFoodTruckId(order.getFoodTruckId());
        oldOrder.setCustomerName(order.getCustomerName());
        oldOrder.setTotalPrice(order.getTotalPrice());
        oldOrder.setOrderDate(order.getOrderDate());
        oldOrder.setStatus(order.getStatus());
        orderRepository.save(oldOrder);
    }


    public void deleteOrder(Integer id){

        Order oldOrder=orderRepository.findOrderById(id);

        if(oldOrder==null){
            throw new ApiExcepiton("id not found");
        }

        orderRepository.delete(oldOrder);

    }

    public int calculateExpectedWaitingTime(Integer orderId) {
        Order order = orderRepository.findOrderById(orderId);
        if (order == null) {
            throw new ApiExcepiton("Order not found");
        }

        return 15;
    }

    public double calculatePriceAfterDiscount(Double originalPrice, Double discountPercentage) {
        return originalPrice - (originalPrice * (discountPercentage / 100));
    }


    public int calculateLoyaltyPoints(Double totalSpent) {
        return (int) (totalSpent / 10);
    }
}
