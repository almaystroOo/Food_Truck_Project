package com.example.capstone2foodtruck.Service;

import com.example.capstone2foodtruck.ApiResponse.ApiExcepiton;
import com.example.capstone2foodtruck.Model.App_User;
import com.example.capstone2foodtruck.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<App_User> getUser(){
        return userRepository.findAll();
    }


    public void addUser(App_User appUser){
        userRepository.save(appUser);
    }


    public void updateUser(Integer id, App_User appUser){
        App_User oldAppUser =userRepository.findUserById(id);

        if(oldAppUser ==null){
            throw new ApiExcepiton("id not found");
        }

        oldAppUser.setName(appUser.getName());
        oldAppUser.setEmail(appUser.getEmail());
        oldAppUser.setPassword(appUser.getPassword());
        oldAppUser.setPhone(appUser.getPhone());
        oldAppUser.setUserType(appUser.getUserType());

        userRepository.save(oldAppUser);
    }


    public void deleteUser(Integer id){

        App_User oldAppUser =userRepository.findUserById(id);

        if(oldAppUser ==null){
            throw new ApiExcepiton("id not found");
        }

        userRepository.delete(oldAppUser);

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
