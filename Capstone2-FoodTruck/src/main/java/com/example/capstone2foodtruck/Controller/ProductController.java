package com.example.capstone2foodtruck.Controller;


import com.example.capstone2foodtruck.Model.Product;
import com.example.capstone2foodtruck.Model.User;
import com.example.capstone2foodtruck.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity get(){

        return ResponseEntity.status(HttpStatus.OK).body(productService.getProduct());
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid Product product, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.OK).body("product added");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id , @RequestBody @Valid Product product, Errors errors ){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        productService.updateProduct(id, product);
        return ResponseEntity.status(HttpStatus.OK).body("product update");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id){

        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("product delete");
    }

    @GetMapping("/suggest-meals/{maxPrice}")
    public ResponseEntity<List<Product>> suggestMealsByPrice(@PathVariable Double maxPrice) {
        List<Product> suggestedMeals = productService.suggestMealsByPrice(maxPrice);
        return ResponseEntity.ok(suggestedMeals);
    }



    @GetMapping("/purchaseProduct/{productId}")
    public ResponseEntity<Double> purchaseProduct(@PathVariable Integer productId) {
        double purchaseProduct = productService.purchaseProduct(productId);
        return ResponseEntity.ok(purchaseProduct);
    }

    @GetMapping("/average-price/{foodTruckId}")
    public ResponseEntity<Double> calculateAverageProductPrice(@PathVariable Integer foodTruckId) {
        double averagePrice = productService.calculateAverageProductPrice(foodTruckId);
        return ResponseEntity.ok(averagePrice);
    }

    @GetMapping("/food-truck/{id}/product-count")
    public ResponseEntity<String> countAvailableAndUnavailableProducts(@PathVariable Integer id) {
        String productCounts = productService.countAvailableAndUnavailableProducts(id);
        return ResponseEntity.ok(productCounts);
    }


}
