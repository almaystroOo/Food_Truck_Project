package com.example.capstone2foodtruck.Service;


import com.example.capstone2foodtruck.ApiResponse.ApiExcepiton;
import com.example.capstone2foodtruck.Model.Product;
import com.example.capstone2foodtruck.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new ApiExcepiton("Product cannot be null");
        }
        productRepository.save(product);
    }

//    public Product findById(Integer productId){
//     if(productRepository.findById(productId).isPresent()){
//         Optional<Product> pr =productRepository.findById(productId);
//
//     }
//        return  product;
//    }

    public void updateProduct(Integer id, Product product) {
        if (product == null) {
            throw new ApiExcepiton("Product cannot be null");
        }

        Product oldProduct = productRepository.findProductsById(id);
        if (oldProduct == null) {
            throw new ApiExcepiton("Product ID not found");
        }

        oldProduct.setFoodTruck(product.getFoodTruck());
        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setCost(product.getCost());
        oldProduct.setAvailability(product.getAvailability());

        productRepository.save(oldProduct);
    }

    public void deleteProduct(Integer id) {
        Product oldProduct = productRepository.findProductsById(id);
        if (oldProduct == null) {
            throw new ApiExcepiton("Product ID not found");
        }
        productRepository.delete(oldProduct);
    }

    public List<Product> suggestMealsByPrice(Double maxPrice) {
        if (maxPrice == null || maxPrice < 0) {
            throw new ApiExcepiton("Invalid price value");
        }
        return productRepository.findByPriceLessThan(maxPrice);
    }

    private int purchaseCount = 0;

    public double purchaseProduct(Integer productId) {
        if (productId == null) {
            throw new ApiExcepiton("Product ID cannot be null");
        }

        Product product = productRepository.findProductsById(productId);
        if (product == null) {
            throw new ApiExcepiton("Product not found");
        }

        purchaseCount++;
        double price = product.getPrice();
        double finalPrice = price;

        if (purchaseCount % 2 == 0) { // Apply discount on every second purchase
            finalPrice = price * 0.9; // 10% discount
        }

        return finalPrice;
    }

    public double calculateAverageProductPrice(Integer foodTruckId) {
        if (foodTruckId == null) {
            throw new ApiExcepiton("Food truck ID cannot be null");
        }

        Double averagePrice = productRepository.getAveragePriceByFoodTruckId(foodTruckId);
        return averagePrice != null ? averagePrice : 0.0;
    }

    public String countAvailableAndUnavailableProducts(Integer foodTruckId) {
        if (foodTruckId == null) {
            throw new ApiExcepiton("Food truck ID cannot be null");
        }

        int availableCount = productRepository.countByFoodTruckIdAndAvailability(foodTruckId, "Available");
        int unavailableCount = productRepository.countByFoodTruckIdAndAvailability(foodTruckId, "Unavailable");

        return "Available: " + availableCount + ", Unavailable: " + unavailableCount;
    }
}

