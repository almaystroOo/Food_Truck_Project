package com.example.capstone2foodtruck.Controller;

import com.example.capstone2foodtruck.Model.Product;
import com.example.capstone2foodtruck.Model.Review;
import com.example.capstone2foodtruck.Service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/Review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;


    @GetMapping("/get")
    public ResponseEntity get(){

        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getReview());
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid Review review, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        reviewService.addReview(review);
        return ResponseEntity.status(HttpStatus.OK).body("review added");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id , @RequestBody @Valid Review review, Errors errors ){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        reviewService.updateReview(id, review);
        return ResponseEntity.status(HttpStatus.OK).body("Review update");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id){

        reviewService.deleteReview(id);
        return ResponseEntity.status(HttpStatus.ok).body("Review delete");
    }

    @PostMapping("/rate/{productId}/{rating}")
    public ResponseEntity<String> rateMeal(@PathVariable Integer productId, @PathVariable Integer rating) {
        reviewService.rateMeal(productId, rating);
        return ResponseEntity.ok("Meal rated successfully");
    }

    @GetMapping("/average/{productId}")
    public ResponseEntity<Double> getAverageRating(@PathVariable Integer productId) {
        Double averageRating = reviewService.getAverageRating(productId);
        return ResponseEntity.ok(averageRating);
    }

}
