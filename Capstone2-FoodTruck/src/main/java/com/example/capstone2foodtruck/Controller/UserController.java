package com.example.capstone2foodtruck.Controller;

import com.example.capstone2foodtruck.Model.App_User;
import com.example.capstone2foodtruck.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> get(){

        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid App_User appUser, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        userService.addUser(appUser);
        return ResponseEntity.status(HttpStatus.OK).body("User added");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id , @RequestBody @Valid App_User appUser, Errors errors ){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        userService.updateUser(id, appUser);
        return ResponseEntity.status(HttpStatus.OK).body("User update");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){

        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("User delete");
    }

    @PostMapping("/addFavorite/{orderId}")
    public ResponseEntity<String> addFavorite(@PathVariable Integer orderId) {
        userService.addFavorite(orderId);
        return ResponseEntity.status(HttpStatus.OK).body("Order added to favorites.");
    }

    @DeleteMapping("/removeFavorite/{orderId}")
    public ResponseEntity<String> removeFavorite(@PathVariable Integer orderId) {
        userService.removeFavorite(orderId);
        return ResponseEntity.status(HttpStatus.OK).body("Order removed from favorites.");
    }

    @GetMapping("/listFavorite")
    public ResponseEntity<List<Integer>> listFavorites() {
        List<Integer> favorites = userService.listFavorites();
        return ResponseEntity.status(HttpStatus.OK).body(favorites);
    }

}
