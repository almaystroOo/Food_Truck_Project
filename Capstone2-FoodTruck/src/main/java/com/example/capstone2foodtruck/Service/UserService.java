package com.example.capstone2foodtruck.Service;

import com.example.capstone2foodtruck.ApiResponse.ApiExcepiton;
import com.example.capstone2foodtruck.Model.User;
import com.example.capstone2foodtruck.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUser(){
        return userRepository.findAll();
    }


    public void addUser(User user){
        userRepository.save(user);
    }


    public void updateUser(Integer id,User user){
        User oldUser=userRepository.findUserById(id);

        if(oldUser==null){
            throw new ApiExcepiton("id not found");
        }

        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setPhone(user.getPhone());
        oldUser.setUserType(user.getUserType());

        userRepository.save(oldUser);
    }


    public void deleteUser(Integer id){

        User oldUser=userRepository.findUserById(id);

        if(oldUser==null){
            throw new ApiExcepiton("id not found");
        }

        userRepository.delete(oldUser);

    }


    ArrayList<Integer> favoriteOrders = new ArrayList<>();

    public void addFavorite(Integer orderId) {
        if (favoriteOrders.contains(orderId)) {
            throw new ApiExcepiton("Order already in favorites.");
        }
        favoriteOrders.add(orderId);
    }

    public void removeFavorite(Integer orderId) {
        if (!favoriteOrders.contains(orderId)) {
            throw new ApiExcepiton("Order not found in favorites.");
        }
        favoriteOrders.remove(orderId);
    }

    public List<Integer> listFavorites() {
        return new ArrayList<>(favoriteOrders);
    }


}
