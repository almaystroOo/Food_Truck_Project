package com.example.capstone2foodtruck.Repository;

import com.example.capstone2foodtruck.Model.App_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<App_User,Integer> {

   App_User findUserById(Integer id);










}
